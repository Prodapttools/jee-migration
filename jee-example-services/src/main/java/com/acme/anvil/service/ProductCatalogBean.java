
package com.acme.anvil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCatalogService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCatalogService.class);

    @Transactional
    public void populateCatalog() {
        LOG.info("Do something.");
    }

    public void onRemove() {
        LOG.info("Called Remove.");
    }

    public void onActivate() {
        LOG.info("Called Activate");
    }

    public void onPassivate() {
        LOG.info("Called Passivate");
    }
}
