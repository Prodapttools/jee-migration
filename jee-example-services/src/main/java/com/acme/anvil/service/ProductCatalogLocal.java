
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCatalogService {

    @Transactional
    public void populateCatalog() {
        // Business logic for populating the catalog
    }
}
