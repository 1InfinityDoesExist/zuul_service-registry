package in.zuul.apigateway.config;

import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GatewayConfig {

    /**
     * Before returning the response you can do some manipulation ".*" means any call of that
     * service
     * 
     * In addition to just creating routes, RouteLocatorBuilder allows you to add predicates and
     * filters to your routes so you can route handle based on certain conditions as well as alter
     * the request/response as you see fit.
     * 
     * lb: - is enough lb stands for load balancer
     * 
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(p -> p.path("/department/**")
                .filters(f -> f
                    .circuitBreaker(c -> c.setName("deptCircuitBreaker")
                        .setFallbackUri("forward:/departmentServiceFallBack"))
                    .requestRateLimiter().configure(c -> c.setRateLimiter(redisRateLimiter())))
                .uri("lb://department-service"))
            .route(p -> p.path("/user/**")
                .filters(f -> f
                    .circuitBreaker(c -> c.setName("userCircuitBreaker")
                        .setFallbackUri("forward:/userServiceFallBack"))
                    .requestRateLimiter().configure(c -> c.setRateLimiter(redisRateLimiter())))
                .uri("lb://USER-SERVICE"))
            .route(r -> r.query("tenantId")
                .filters(f -> f.circuitBreaker(
                    c -> c.setName("tenantFallBack").setFallbackUri("forward:/deptCircuitBreaker")))
                .uri("lb://department-service"))
            .build();
    }

    /**
     * Mandatory
     * 
     * 10 means in 1sec how many request must be executed without drop
     * 
     * 20 means in 1sec what is the max number of api call a user can request.
     * 
     * @return
     */
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        log.info(":::::Redis Rate Limiter:::::");
        return new RedisRateLimiter(1, 3); // capacity
    }

    /**
     * Mandatory
     * 
     * @return
     */
    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just("1");
    }

    /**
     * wait for 2 second , if not response redirect to fallback url.
     * 
     * @return
     */
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory
            .configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(
                    TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
                .build());
    }


}
