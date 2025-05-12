
package com.acme.anvil.service.jms;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acme.anvil.vo.LogEvent;

@Service
public class LogEventPublisher {

    private static final Logger LOG = Logger.getLogger(LogEventPublisher.class);
    private static final String QUEUE_JNDI_NAME = "jms/LogEventQueue";
    private static final String QUEUE_FACTORY_JNDI_NAME = "jms/LogEventQueue";

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        // Initialization logic if needed
    }

    @PreDestroy
    public void cleanup() {
        // Cleanup logic if needed
    }

    @Transactional
    public void publishLogEvent(final LogEvent log) {
        try {
            jmsTemplate.send(QUEUE_JNDI_NAME, new MessageCreator() {
                @Override
                public ObjectMessage createMessage(javax.jms.Session session) throws JMSException {
                    ObjectMessage logMsg = session.createObjectMessage(log);
                    return logMsg;
                }
            });
        } catch (JMSException e) {
            LOG.error("Exception sending message.", e);
        }
    }

    private static Context getContext() throws NamingException {
        Properties environment = new Properties();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        environment.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        return new InitialContext(environment);
    }

    private static Queue getQueue(Context context) throws NamingException {
        return (Queue) context.lookup(QUEUE_JNDI_NAME);
    }

    private static QueueSession getQueueSession(Context context) throws JMSException, NamingException {
        QueueConnectionFactory cf = (QueueConnectionFactory) context.lookup(QUEUE_FACTORY_JNDI_NAME);
        QueueConnection connection = cf.createQueueConnection();
        return (QueueSession) connection.createSession(false, 1);
    }
}
