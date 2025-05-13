
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
@RequestMapping("/items")
public class ItemController {

    private final ItemLookup itemLookup;

    @Autowired
    public ItemController(ItemLookup itemLookup) {
        this.itemLookup = itemLookup;
    }

    @GetMapping("/{id}")
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
    private String description;

    // Getters and Setters
}


yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourdb
    username: yourusername
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver
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
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
