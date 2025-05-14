
package com.acme.anvil.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupService {

    @Transactional
    public ItemLookupLocal create() {
        // TODO: Implement the creation logic
        return new ItemLookupLocal();
    }
}
