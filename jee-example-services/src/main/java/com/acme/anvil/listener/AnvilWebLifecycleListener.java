
package com.acme.anvil.listener;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Component
public class AnvilWebLifecycleListener implements ServletContextListener {

    private static Logger LOG = Logger.getLogger(AnvilWebLifecycleListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanApplicationLifecycleListener";

    @Autowired
    private MBeanServer mBeanServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String appName = sce.getServletContext().getServletContextName();
        LOG.info("Before Start Application[" + appName + "]");
        LOG.info("After Start Application[" + appName + "]");
        registerMBean();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String appName = sce.getServletContext().getServletContextName();
        LOG.info("Before Stop Application[" + appName + "]");
        unregisterMBean();
        LOG.info("After Stop Application[" + appName + "]");
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
