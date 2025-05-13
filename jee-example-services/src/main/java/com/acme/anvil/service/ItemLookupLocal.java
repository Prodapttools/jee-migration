
package com.acme.anvil.service;

import com.acme.anvil.vo.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemLookupService {
    Item lookupItem(long id);
}



package com.acme.anvil.service.impl;

import com.acme.anvil.service.ItemLookupService;
import com.acme.anvil.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemLookupServiceImpl implements ItemLookupService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemLookupServiceImpl(ItemRepository itemRepository) {
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

import com.acme.anvil.service.ItemLookupService;
import com.acme.anvil.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemLookupService itemLookupService;

    @Autowired
    public ItemController(ItemLookupService itemLookupService) {
        this.itemLookupService = itemLookupService;
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        return itemLookupService.lookupItem(id);
    }
}


yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true


xml
<!-- pom.xml -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
    </dependency>
</dependencies>
