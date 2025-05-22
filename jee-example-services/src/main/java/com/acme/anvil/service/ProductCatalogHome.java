
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCatalogHome {

    @Transactional
    public ProductCatalog create() {
        // TODO: Implement the creation logic for ProductCatalog
        return new ProductCatalog();
    }
}
