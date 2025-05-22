
package com.acme.anvil.listener;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Component
public class AnvilWebLifecycleListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(AnvilWebLifecycleListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanApplicationLifecycleListener";

    @Autowired
    private MBeanServer mBeanServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("Application Context Refreshed");
        registerMBean();
    }

    private void registerMBean() {
        LOG.info("Registering MBeans.");
        try {
            mBeanServer.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            LOG.info("Registered MBean[" + MBEAN_NAME + "]");
        } catch (Exception e) {
            LOG.error("Exception while registering MBean[" + MBEAN_NAME + "]", e);
        }
    }

    private void unregisterMBean() {
        LOG.info("Unregistering MBeans.");
        try {
            mBeanServer.unregisterMBean(new ObjectName(MBEAN_NAME));
            LOG.info("Unregistered MBean[" + MBEAN_NAME + "]");
        } catch (Exception e) {
            LOG.error("Exception while unregistering MBean[" + MBEAN_NAME + "]", e);
        }
    }
}
