
package com.acme.anvil;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.anvil.service.ItemLookupService;

@RestController
@RequestMapping("/anvil")
public class AnvilWebServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AnvilWebServlet.class);

    @Autowired
    private ItemLookupService itemLookupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("id");
        if (StringUtils.isNotBlank(itemId)) {
            Long id = Long.parseLong(itemId);
            itemLookupService.lookupItem(id);
        }
    }
}

@Component
class ApplicationLifecycleListener implements ServletContextListener {

    @PostConstruct
    public void init() {
        // Initialization logic
    }

    @PreDestroy
    public void destroy() {
        // Cleanup logic
    }
}

@Service
public class ItemLookupService {

    @Transactional
    public void lookupItem(Long id) {
        // Business logic for item lookup
    }
}
