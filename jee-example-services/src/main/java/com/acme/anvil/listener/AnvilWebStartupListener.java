
package com.acme.anvil.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Component
public class AnvilWebStartupListener implements CommandLineRunner {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef"; 
    private static final Logger log = LoggerFactory.getLogger(AnvilWebStartupListener.class);

    @Autowired
    private MBeanServer mBeanServer;

    @Override
    public void run(String... args) throws Exception {
        startup("AnvilWebStartupListener", null);
    }

    public String startup(String name, Hashtable<?, ?> ht) {
        log.info("Starting Server Startup Class: " + name + " with properties: ");

        if (ht != null) {
            for (Object key : ht.keySet()) {
                log.info("Key[" + key + "] = Value[" + ht.get(key) + "]");
            }
        }

        registerMBean();
        return "Completed Startup Class: " + name;
    }

    private MBeanServer getMBeanServer() throws NamingException {
        Context context = new InitialContext();
        return (MBeanServer) context.lookup("java:comp/jmx/runtime");
    }

    private void registerMBean() {
        log.info("Registering MBeans.");

        try {
            mBeanServer.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            log.info("Registered MBean[" + MBEAN_NAME + "]");
        } catch (Exception e) {
            log.error("Exception while registering MBean[" + MBEAN_NAME + "]", e);
        }
    }
}
