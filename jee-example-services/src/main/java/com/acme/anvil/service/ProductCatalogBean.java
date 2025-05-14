
package com.acme.anvil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogBean {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCatalogBean.class);

    public void populateCatalog() {
        LOG.info("Do something.");
    }
}
