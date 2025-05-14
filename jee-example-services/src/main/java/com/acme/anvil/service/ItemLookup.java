
package com.acme.anvil.service;

import com.acme.anvil.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupService implements ItemLookup {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemLookupService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
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



package com.acme.anvil.controller;

import com.acme.anvil.service.ItemLookup;
import com.acme.anvil.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemLookupController {

    private final ItemLookup itemLookup;

    @Autowired
    public ItemLookupController(ItemLookup itemLookup) {
        this.itemLookup = itemLookup;
    }

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable long id) {
        return itemLookup.lookupItem(id);
    }
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
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



package com.acme.anvil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemLookup {

    public static void main(String[] args) {
        SpringApplication.run(ItemLookup.class, args);
    }
}
