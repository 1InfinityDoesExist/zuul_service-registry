package zuularch.department.service.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseInterceptors extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        boolean isAdmin = request.isUserInRole("OMNI_CONSUMER");
        boolean isAdmin1 = request.isUserInRole("SUPER_ADMIN");
        log.info(":::::::::******:::::::---> " + isAdmin);
        log.info("::::::-----*******---------->" + isAdmin1);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("::::authoritis " + authentication.getAuthorities());
        log.info("::::::::::::::Prinicpal::: {}", authentication.getPrincipal());
        log.info(":::::::::::::::::{}", authentication.getName());
        if (authentication.isAuthenticated()) {
            log.info("::::::::::::I am authenticated::::::::::::::::");
        }

        return true;
    }

}
