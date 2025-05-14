
package com.acme.anvil.management;

import org.springframework.stereotype.Component;

@Component
public class AnvilInvokeBeanImpl implements AnvilInvokeBean {

    private int invoked = 0;

    @Override
    public void addInvoked() {
        invoked++;
    }

    @Override
    public int getInvoked() {
        return invoked;
    }
}
