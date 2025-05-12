
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
    private MBeanServer mBeanServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        registerMBean();
    }

    private void registerMBean() {
        try {
            mBeanServer.registerMBean(new AnvilInvokeBeanImpl(), new ObjectName(MBEAN_NAME));
            System.out.println("Registered MBean[" + MBEAN_NAME + "]");
        } catch (Exception e) {
            System.err.println("Exception while registering MBean[" + MBEAN_NAME + "]");
            e.printStackTrace();
        }
    }

    @Bean
    public MBeanExporter mBeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setBeans(Collections.singletonMap(MBEAN_NAME, new AnvilInvokeBeanImpl()));
        return exporter;
    }
}
