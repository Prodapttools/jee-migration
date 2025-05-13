
package com.acme.anvil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductCatalogService {

    public void populateCatalog() {
        log.info("Do something.");
    }
}
