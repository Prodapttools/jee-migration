
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
import javax.management.NotCompliantMBeanException;
import java.util.Hashtable;

@Component
public class AnvilWebStartupListener implements ApplicationRunner {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef"; 
    private static final Logger log = LoggerFactory.getLogger(AnvilWebStartupListener.class);
    
    private final MBeanServer mBeanServer;

    public AnvilWebStartupListener(MBeanServer mBeanServer) {
        this.mBeanServer = mBeanServer;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting Server Startup Class with properties: ");
        
        Hashtable<String, String> properties = new Hashtable<>();
        // Populate properties if needed
        
        for (String key : properties.keySet()) {
            log.info("Key[{}] = Value[{}]", key, properties.get(key));
        }
        
        registerMBean();
    }

    private void registerMBean() {
        log.info("Registering MBeans.");
        
        try {
            mBeanServer.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            log.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException e) {
            log.error("Exception while registering MBean[{}]: {}", MBEAN_NAME, e.getMessage());
        }
    }
}
