
package com.acme.anvil.listener;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Configuration
public class AnvilWebLifecycleListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(AnvilWebLifecycleListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanApplicationLifecycleListener";

    @Autowired
    private MBeanServer mBeanServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("Application context refreshed.");
        registerMBean();
    }

    @Bean
    public MBeanServer mBeanServer() {
        // Create and return the MBeanServer instance
        // This is a placeholder; actual implementation may vary based on your environment
        return java.lang.management.ManagementFactory.getPlatformMBeanServer();
    }

    private void registerMBean() {
        LOG.info("Registering MBeans.");
        try {
            mBeanServer.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            LOG.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            LOG.error("Exception while registering MBean[{}]", MBEAN_NAME, e);
        }
    }

    @EventListener
    public void handleContextStopped(ContextStoppedEvent event) {
        LOG.info("Application context stopped.");
        unregisterMBean();
    }

    private void unregisterMBean() {
        LOG.info("Unregistering MBeans.");
        try {
            mBeanServer.unregisterMBean(new ObjectName(MBEAN_NAME));
            LOG.info("Unregistered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            LOG.error("Exception while unregistering MBean[{}]", MBEAN_NAME, e);
        }
    }
}
