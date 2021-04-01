package in.zuul.apigateway.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import in.zuul.apigateway.TenantInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(-1)
public class CustomeGatewayFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        String tenantId = request.getParameter("tenantId");
        if (!StringUtils.isEmpty(tenantId)) {
            log.info(":::::TenantId is {}", tenantId);
            TenantInfo.setEcoSystemId(tenantId);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


}
