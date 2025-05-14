
package com.acme.anvil.management;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnvilInvokeBean {

    private int invokedCount = 0;

    @Transactional
    public void addInvoked() {
        invokedCount++;
        log.info("Invoked count incremented to: {}", invokedCount);
    }

    public int getInvoked() {
        return invokedCount;
    }
}
