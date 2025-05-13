
package com.acme.anvil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProductCatalogService {

    @Transactional
    public void populateCatalog() {
        log.info("Do something.");
    }
}
