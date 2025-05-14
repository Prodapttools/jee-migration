
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
