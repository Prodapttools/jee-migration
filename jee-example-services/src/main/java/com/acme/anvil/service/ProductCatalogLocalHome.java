
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCatalogLocalHome {

    @Transactional
    public ProductCatalogLocal create() {
        // TODO: Implement the creation logic for ProductCatalogLocal
        return new ProductCatalogLocal();
    }
}
