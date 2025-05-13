
package com.acme.anvil.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import java.lang.management.ManagementFactory;

@Component
public class AnvilStartupListener {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBean";
    private static final Logger log = LoggerFactory.getLogger(AnvilStartupListener.class);

    @PostConstruct
    public void init() {
        log.info("Starting Anvil Startup Listener");
        registerMBean();
    }

    private void registerMBean() {
        log.info("Registering MBeans.");

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            server.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            log.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            log.error("Exception while registering MBean[{}]: {}", MBEAN_NAME, e.getMessage());
        }
    }
}
