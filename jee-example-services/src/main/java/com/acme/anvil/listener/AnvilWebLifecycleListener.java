
package com.acme.anvil.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.stereotype.Component;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Component
public class AnvilLifecycleListener {

    private static final Logger LOG = LoggerFactory.getLogger(AnvilLifecycleListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanApplicationLifecycleListener";

    @Autowired
    private MBeanExporter mBeanExporter;

    @PostConstruct
    public void postStart() {
        LOG.info("Application started");
        registerMBean();
    }

    @PreDestroy
    public void preStop() {
        LOG.info("Application stopping");
        unregisterMBean();
    }

    private void registerMBean() {
        LOG.info("Registering MBeans.");
        try {
            mBeanExporter.registerManagedResource(new AnvilInvokeBeanImpl(), MBEAN_NAME);
            LOG.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            LOG.error("Exception while registering MBean[{}]", MBEAN_NAME, e);
        }
    }

    private void unregisterMBean() {
        LOG.info("Unregistering MBeans.");
        try {
            mBeanExporter.unregisterManagedResource(MBEAN_NAME);
            LOG.info("Unregistered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            LOG.error("Exception while unregistering MBean[{}]", MBEAN_NAME, e);
        }
    }
}
