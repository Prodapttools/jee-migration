
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupService {

    @Transactional
    public ItemLookup create() {
        // TODO: Implement the create logic
        return new ItemLookup();
    }
}
