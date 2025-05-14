
package com.acme.anvil.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnvilInvokeService implements AnvilInvokeBean {

    private final AnvilInvokeRepository anvilInvokeRepository;

    @Autowired
    public AnvilInvokeService(AnvilInvokeRepository anvilInvokeRepository) {
        this.anvilInvokeRepository = anvilInvokeRepository;
    }

    @Override
    @Transactional
    public void addInvoked() {
        log.info("Adding invoked count");
        anvilInvokeRepository.incrementInvokedCount();
    }

    @Override
    public int getInvoked() {
        log.info("Getting invoked count");
        return anvilInvokeRepository.getInvokedCount();
    }
}



package com.acme.anvil.management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnvilInvokeRepository extends JpaRepository<AnvilInvokeEntity, Long> {
    
    void incrementInvokedCount();

    int getInvokedCount();
}



package com.acme.anvil.management;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnvilInvokeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private int invokedCount;

    // Getters and Setters
    public int getInvokedCount() {
        return invokedCount;
    }

    public void setInvokedCount(int invokedCount) {
        this.invokedCount = invokedCount;
    }
}



package com.acme.anvil.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnvilInvokeInitializer {

    private final AnvilInvokeRepository anvilInvokeRepository;

    @Autowired
    public AnvilInvokeInitializer(AnvilInvokeRepository anvilInvokeRepository) {
        this.anvilInvokeRepository = anvilInvokeRepository;
        initialize();
    }

    private void initialize() {
        // Initialization logic if needed
    }
}



package com.acme.anvil.management;

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

    @PostMapping("/invoke/add")
    public void addInvoked() {
        anvilInvokeService.addInvoked();
    }

    @GetMapping("/invoke/count")
    public int getInvoked() {
        return anvilInvokeService.getInvoked();
    }
}
