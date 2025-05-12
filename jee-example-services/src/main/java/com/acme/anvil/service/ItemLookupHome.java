
package com.acme.anvil.service;

import org.springframework.stereotype.Service;

@Service
public class ItemLookupService {
    public ItemLookup create() {
        return new ItemLookup();
    }
}
