
package com.acme.anvil.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Component
public class AnvilWebStartupListener implements ApplicationRunner {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef"; 
    private static final Logger log = LoggerFactory.getLogger(AnvilWebStartupListener.class);

    @Override
    public void run(ApplicationArguments args) {
        log.info("Starting Server Startup Class with properties: ");
        registerMBean();
    }

    private MBeanServer getMBeanServer() throws NamingException {
        Context context = new InitialContext();
        return (MBeanServer) context.lookup("java:comp/jmx/runtime");
    }

    private void registerMBean() {
        log.info("Registering MBeans.");
        MBeanServer server;
        try {
            server = getMBeanServer();
            server.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            log.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            log.error("Exception while registering MBean[{}]", MBEAN_NAME, e);
        }
    }
}
