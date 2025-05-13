
package com.acme.anvil.service.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.QueueRequestor;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.anvil.vo.LogEvent;

@Service
public class LogEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(LogEventPublisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    private static final String QUEUE_NAME = "jms/LogEventQueue";

    @Transactional
    public void publishLogEvent(LogEvent log) {
        try {
            jmsTemplate.send(QUEUE_NAME, new MessageCreator() {
                @Override
                public ObjectMessage createMessage(Session session) throws JMSException {
                    ObjectMessage logMsg = session.createObjectMessage(log);
                    return logMsg;
                }
            });
        } catch (JMSException e) {
            LOG.error("Exception sending message.", e);
        }
    }
}


### Additional Configuration

In your `application.properties` or `application.yml`, you will need to configure the JMS connection factory and other related properties:

properties
# application.properties
spring.jms.pub-sub-domain=false
spring.jms.template.default-destination=jms/LogEventQueue
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin


Make sure to include the necessary dependencies in your `pom.xml` for Spring JMS and ActiveMQ (or your chosen JMS provider):

xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
