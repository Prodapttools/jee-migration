
package com.acme.anvil.management;

import org.springframework.stereotype.Component;

@Component
public class AnvilInvokeBeanImpl {

    private int invoked = 0;

    public void addInvoked() {
        invoked++;
    }

    public int getInvoked() {
        return invoked;
    }
}
