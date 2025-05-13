
package com.acme.anvil.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

import com.acme.anvil.vo.LogEvent;

@Service
public class LogEventSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(LogEventSubscriber.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");

    @JmsListener(destination = "jms/LogEventQueue")
    public void onMessage(LogEvent event) {
        if (event != null) {
            LOG.info("Log Event [{}] : {}", SDF.format(event.getDate()), event.getMessage());
        }
    }
}


### Additional Configuration

In your `application.properties` or `application.yml`, you will need to configure the JMS settings, for example:

properties
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
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
</dependency>
