
package com.acme.anvil;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Component
public class AuthenticateFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticateFilter.class);

    @Override
    public void destroy() {
        logger.debug("AuthenticateFilter destroy.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        logger.debug("AuthenticateFilter doFilter.");
        if (req.getAttribute("cancelSession") != null) {
            logger.info("Cancelled session due to session timeout.");
            request.getSession().invalidate(); // Invalidate session directly
        } else if (session != null) {
            Date fiveMinutesAgo = DateUtils.addMinutes(new Date(), -5);
            // Check that the time the session was last accessed was after 5 minutes ago.
            Date timeLastAccessed = new Date(session.getLastAccessedTime());

            if (timeLastAccessed.before(fiveMinutesAgo)) {
                session.invalidate();
                // Make the user log back in.
                request.getSession().invalidate(); // Invalidate session directly
            }
        }

        chain.doFilter(req, resp); // Continue the filter chain
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.debug("AuthenticateFilter init.");
    }
}
