
package com.acme.anvil.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MalformedObjectNameException;
import java.lang.management.ManagementFactory;

@Component
public class AnvilWebStartupListener implements ApplicationRunner {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef";
    private static final Logger log = LoggerFactory.getLogger(AnvilWebStartupListener.class);

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting Server Startup Class with properties: ");
        for (String arg : args.getSourceArgs()) {
            log.info("Argument: {}", arg);
        }
        registerMBean();
    }

    private void registerMBean() {
        log.info("Registering MBeans.");

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            server.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            log.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | MalformedObjectNameException e) {
            log.error("Exception while registering MBean[{}]: {}", MBEAN_NAME, e.getMessage());
        }
    }
}
