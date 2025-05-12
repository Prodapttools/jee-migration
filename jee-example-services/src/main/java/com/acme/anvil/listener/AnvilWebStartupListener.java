To convert the provided WebLogic-based Java code into a modern Spring Boot application, we will replace WebLogic-specific components with Spring Boot equivalents, refactor the code for clarity and maintainability, and ensure that it adheres to Spring Boot best practices. Below is the refactored code along with explanations of the changes made.

### Refactored Code: `AnvilStartupListener.java`


package com.acme.anvil.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.stereotype.Component;

import com.acme.anvil.management.AnvilInvokeBeanImpl;

@Component
public class AnvilStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(AnvilStartupListener.class);
    private static final String MBEAN_NAME = "com.acme:Name=anvil,Type=com.acme.anvil.management.AnvilInvokeBeanT3StartupDef";

    @Autowired
    private MBeanExporter mBeanExporter;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Starting Server Startup Class with properties: ");
        // You can add any additional properties you want to log here

        registerMBean();
    }

    private void registerMBean() {
        log.info("Registering MBeans.");
        try {
            AnvilInvokeBeanImpl anvilInvokeBean = new AnvilInvokeBeanImpl();
            mBeanExporter.registerManagedResource(anvilInvokeBean, MBEAN_NAME);
            log.info("Registered MBean[{}]", MBEAN_NAME);
        } catch (Exception e) {
            log.error("Exception while registering MBean[{}]", MBEAN_NAME, e);
        }
    }
}


### Key Changes and Explanations:

1. **Class Annotations**:
   - The class is annotated with `@Component`, making it a Spring-managed bean. This allows Spring to automatically detect and register it during component scanning.

2. **Event Listener**:
   - The class implements `ApplicationListener<ContextRefreshedEvent>`, which allows it to listen for the Spring application context's refresh event. This is a suitable replacement for the WebLogic startup listener.

3. **Logging**:
   - The logging mechanism has been updated to use SLF4J with `LoggerFactory`, which is the standard logging approach in Spring Boot applications.

4. **MBean Registration**:
   - Instead of manually looking up the MBean server using JNDI, we use Spring's `MBeanExporter` to register the MBean. This simplifies the code and removes the need for JNDI lookups.
   - The `MBeanExporter` is automatically configured by Spring Boot if you include the necessary dependencies (e.g., `spring-boot-starter-jmx`).

5. **Dependency Injection**:
   - The `MBeanExporter` is injected using `@Autowired`, which is the preferred method of dependency injection in Spring.

6. **Error Handling**:
   - The error handling in the `registerMBean` method has been improved to log the exception stack trace, providing better insight into any issues that arise during MBean registration.

### Additional Configuration

To ensure that the application is set up correctly, you will need to add the following dependencies to your `pom.xml`:

xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jmx</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
</dependency>


### Conclusion

The refactored code adheres to Spring Boot conventions and best practices, replacing WebLogic-specific components with Spring Boot equivalents. This migration not only modernizes the application but also enhances its maintainability and clarity. Further steps may include configuring data sources, transaction management, and other application-specific settings in `application.properties` or `application.yml`.