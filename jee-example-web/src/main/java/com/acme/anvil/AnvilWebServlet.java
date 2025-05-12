
package com.acme.anvil;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.anvil.service.ItemLookupService;

@RestController
public class AnvilWebServlet {

    private static final Logger LOG = Logger.getLogger(AnvilWebServlet.class);

    @Autowired
    private ItemLookupService itemLookupService;

    @GetMapping("/lookup")
    public void doGet(@RequestParam(value = "id", required = false) String itemId) throws IOException {
        if (StringUtils.isNotBlank(itemId)) {
            try {
                Long id = Long.parseLong(itemId);
                itemLookupService.lookupItem(id);
            } catch (NumberFormatException e) {
                LOG.error("Invalid item ID format.", e);
            } catch (Exception e) {
                LOG.error("Exception during item lookup.", e);
            }
        }
    }
}
