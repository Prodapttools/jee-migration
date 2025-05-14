
package com.acme.anvil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.acme.anvil.service.ItemLookupService;

@RestController
public class AnvilWebServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AnvilWebServlet.class);

    private final ItemLookupService itemLookupService;

    @Autowired
    public AnvilWebServlet(ItemLookupService itemLookupService) {
        this.itemLookupService = itemLookupService;
    }

    @GetMapping("/lookup")
    public void lookupItem(@RequestParam("id") String itemId) {
        if (StringUtils.isNotBlank(itemId)) {
            try {
                Long id = Long.parseLong(itemId);
                itemLookupService.lookupItem(id);
            } catch (NumberFormatException e) {
                LOG.error("Invalid item ID format: {}", itemId, e);
            }
        } else {
            LOG.warn("Item ID is blank or null.");
        }
    }
}
