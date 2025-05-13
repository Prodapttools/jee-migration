
package com.acme.anvil.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Component
public class AuthenticateFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        logger.debug("AuthenticateFilter doFilter.");

        if (request.getAttribute("cancelSession") != null) {
            logger.info("Cancelled session due to session timeout.");
            if (session != null) {
                session.invalidate();
            }
        } else if (session != null) {
            Date fiveMinutesAgo = new Date(System.currentTimeMillis() - 5 * 60 * 1000);
            Date timeLastAccessed = new Date(session.getLastAccessedTime());

            if (timeLastAccessed.before(fiveMinutesAgo)) {
                session.invalidate();
                logger.info("Session invalidated due to inactivity. User must log back in.");
            }
        }

        filterChain.doFilter(request, response);
    }
}
