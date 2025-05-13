
package com.acme.anvil.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.anvil.vo.LogEvent;

@Service
public class LogEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(LogEventPublisher.class);

    private final JmsTemplate jmsTemplate;

    @Autowired
    public LogEventPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Transactional
    public void publishLogEvent(LogEvent log) {
        try {
            jmsTemplate.convertAndSend("jms/LogEventQueue", log);
        } catch (Exception e) {
            LOG.error("Exception sending message.", e);
        }
    }
}


### Additional Configuration

In your `application.properties` or `application.yml`, you will need to configure the JMS settings:

properties
# Example configuration for ActiveMQ
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin


Make sure to include the necessary dependencies in your `pom.xml`:

xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
</dependency>
