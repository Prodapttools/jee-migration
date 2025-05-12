
package com.acme.anvil.management;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.acme.anvil.management:type=AnvilInvokeBean")
public class AnvilInvokeBeanImpl implements AnvilInvokeBean {

    private int invoked = 0;

    @ManagedOperation
    public void addInvoked() {
        invoked++;
    }

    @ManagedOperation
    public int getInvoked() {
        return invoked;
    }
}
