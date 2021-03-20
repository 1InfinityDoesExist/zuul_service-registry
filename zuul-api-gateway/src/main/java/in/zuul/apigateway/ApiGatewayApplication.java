package in.zuul.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
///import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient // if you use discovery client it will not appear in eureka dashboard
@SpringBootApplication
@EnableHystrix
//@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
