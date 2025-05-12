
package com.acme.anvil;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public void destroy() {
        logger.debug("LoginFilter destroy.");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        //...
        
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        logger.debug("LoginFilter init.");
    }
}
