To migrate the provided `AnvilWebLifecycleListener` from a WebLogic-based Java EE application to a Spring Boot application, we will replace WebLogic-specific components with Spring Boot equivalents. This includes removing the dependency on JNDI for MBean registration and using Spring's built-in capabilities for lifecycle management and logging.

Here's how the refactored code would look:

### Refactored Code: `AnvilLifecycleListener.java`


package com.acme.anvil.listener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Configuration
public class AnvilLifecycleListener {

    private static final Logger LOG = LoggerFactory.getLogger(AnvilLifecycleListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanApplicationLifecycleListener";

    @Autowired
    private MBeanServer mBeanServer; // Autowire MBeanServer bean

    @PostConstruct
    public void postStart() {
        LOG.info("Application started.");
        registerMBean();
    }

    @PreDestroy
    public void preStop() {
        LOG.info("Application is stopping.");
        unregisterMBean();
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

    private void unregisterMBean() {
        LOG.info("Unregistering MBeans.");
        try {
            mBeanServer.unregisterMBean(new ObjectName(MBEAN_NAME));
            LOG.info("Unregistered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            LOG.error("Exception while unregistering MBean[{}]", MBEAN_NAME, e);
        }
    }

    @Bean
    public MBeanServer mBeanServer() {
        return java.lang.management.ManagementFactory.getPlatformMBeanServer(); // Get the platform MBean server
    }
}


### Key Changes and Enhancements:

1. **Spring Annotations**: 
   - Replaced the WebLogic lifecycle methods with Spring's `@PostConstruct` and `@PreDestroy` annotations to handle application startup and shutdown events.

2. **Logging**: 
   - Switched from `log4j` to SLF4J with `LoggerFactory`, which is the recommended logging approach in Spring Boot.

3. **MBeanServer**: 
   - Removed the JNDI lookup for the MBean server. Instead, we use the platform MBean server provided by the Java Management API, which is automatically available in a Spring Boot application.

4. **Configuration Class**: 
   - The listener is now a Spring `@Configuration` class, which allows it to be managed by the Spring context.

5. **Error Handling**: 
   - Enhanced error logging to include the exception stack trace for better debugging.

### Additional Considerations:

- **Dependencies**: Ensure that the `spring-boot-starter` and `spring-boot-starter-actuator` dependencies are included in your `pom.xml` to support MBean registration and management.
  
- **Testing**: Implement unit tests to verify the behavior of the `AnvilLifecycleListener` class, especially the MBean registration and unregistration logic.

- **Application Properties**: If there are any specific configurations needed for the application, they should be moved to `application.properties` or `application.yml`.

This refactored code maintains the original logic while adhering to Spring Boot conventions, enhancing clarity and maintainability.