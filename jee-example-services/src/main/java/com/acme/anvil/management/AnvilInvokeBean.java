
package com.acme.anvil.management;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnvilInvokeService {

    private int invokedCount = 0;

    @Transactional
    public void addInvoked() {
        invokedCount++;
    }

    public int getInvoked() {
        return invokedCount;
    }
}



package com.acme.anvil.controller;

import com.acme.anvil.management.AnvilInvokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnvilInvokeController {

    private final AnvilInvokeService anvilInvokeService;

    @Autowired
    public AnvilInvokeController(AnvilInvokeService anvilInvokeService) {
        this.anvilInvokeService = anvilInvokeService;
    }

    @PostMapping("/invoke")
    public void invoke() {
        anvilInvokeService.addInvoked();
    }

    @GetMapping("/invokedCount")
    public int getInvokedCount() {
        return anvilInvokeService.getInvoked();
    }
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
