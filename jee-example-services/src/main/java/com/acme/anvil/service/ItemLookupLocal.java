
package com.acme.anvil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.anvil.repository.ItemRepository;
import com.acme.anvil.vo.Item;

@Service
public class ItemLookupLocal {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemLookupLocal(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public Item lookupItem(long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
