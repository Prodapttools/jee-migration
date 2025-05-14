
package com.acme.anvil.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductCatalogLocalHome {

    private final ProductCatalogLocal productCatalogLocal;

    @Autowired
    public ProductCatalogLocalHome(ProductCatalogLocal productCatalogLocal) {
        this.productCatalogLocal = productCatalogLocal;
    }

    @Transactional
    public ProductCatalogLocal create() {
        return productCatalogLocal.create();
    }
}
