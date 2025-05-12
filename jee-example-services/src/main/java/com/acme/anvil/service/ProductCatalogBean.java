
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

    public void remove() {
        LOG.info("Called Remove.");
    }

    public void activate() {
        LOG.info("Called Activate");
    }

    public void passivate() {
        LOG.info("Called Passivate");
    }
}
