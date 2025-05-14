
package com.acme.anvil.service.jms;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.acme.anvil.vo.LogEvent;

@Service
public class LogEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(LogEventPublisher.class);
    private static final String QUEUE_NAME = "jms/LogEventQueue";

    private final JmsTemplate jmsTemplate;

    @Autowired
    public LogEventPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void publishLogEvent(LogEvent log) {
        try {
            jmsTemplate.convertAndSend(QUEUE_NAME, log);
        } catch (JMSException e) {
            LOG.error("Exception sending message.", e);
        }
    }
}
