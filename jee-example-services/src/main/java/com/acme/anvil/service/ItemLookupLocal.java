
package com.acme.anvil.service;

import com.acme.anvil.vo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupService {

    // Assuming there's a repository for data access
    private final ItemRepository itemRepository;

    public ItemLookupService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public Item lookupItem(long id) {
        return itemRepository.findById(id).orElse(null);
    }
}



package com.acme.anvil.repository;

import com.acme.anvil.vo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}



package com.acme.anvil.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    private long id;
    private String name;

    // Getters and Setters
}



package com.acme.anvil.controller;

import com.acme.anvil.service.ItemLookupService;
import com.acme.anvil.vo.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemLookupController {

    private final ItemLookupService itemLookupService;

    public ItemLookupController(ItemLookupService itemLookupService) {
        this.itemLookupService = itemLookupService;
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable long id) {
        return itemLookupService.lookupItem(id);
    }
}
