
package com.acme.anvil.management;

import org.springframework.stereotype.Service;

@Service
public class AnvilInvokeBean {

    private int invokedCount = 0;

    public void addInvoked() {
        invokedCount++;
    }

    public int getInvoked() {
        return invokedCount;
    }
}
