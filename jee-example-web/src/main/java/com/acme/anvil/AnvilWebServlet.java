
package com.acme.anvil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if (itemId != null && !itemId.trim().isEmpty()) {
            try {
                Long id = Long.parseLong(itemId);
                itemLookupService.lookupItem(id);
            } catch (NumberFormatException e) {
                LOG.error("Invalid item ID format.", e);
            }
        }
    }
}
