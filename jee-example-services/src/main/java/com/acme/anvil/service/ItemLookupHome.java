
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupHome {

    @Transactional
    public ItemLookup create() {
        // TODO: Implement the creation logic for ItemLookup
        return new ItemLookup();
    }
}
