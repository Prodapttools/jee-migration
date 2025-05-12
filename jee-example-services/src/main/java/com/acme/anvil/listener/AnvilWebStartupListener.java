
package com.acme.anvil.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;

@Component
public class AnvilWebStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef"; 

    @Autowired
    private MBeanExporter mBeanExporter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registerMBean();
    }

    private void registerMBean() {
        try {
            mBeanExporter.registerManagedResource(new AnvilInvokeBeanImpl(), MBEAN_NAME);
            System.out.println("Registered MBean[" + MBEAN_NAME + "]");
        } catch (Exception e) {
            System.err.println("Exception while registering MBean[" + MBEAN_NAME + "]");
        }
    }

    @Bean
    public MBeanExporter mBeanExporter(MBeanServer mBeanServer) {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setMBeanServer(mBeanServer);
        return exporter;
    }
}
