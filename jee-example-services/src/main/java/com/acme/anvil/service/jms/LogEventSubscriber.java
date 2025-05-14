
package com.acme.anvil.service.jms;

import java.text.SimpleDateFormat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.acme.anvil.vo.LogEvent;

@Component
public class LogEventSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(LogEventSubscriber.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");

    @JmsListener(destination = "jms/LogEventQueue")
    public void onMessage(Message msg) {
        if (msg instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) msg;
            try {
                Object obj = om.getObject();
                
                if (obj instanceof LogEvent) {
                    LogEvent event = (LogEvent) obj;
                    LOG.info("Log Event [{}] : {}", SDF.format(event.getDate()), event.getMessage());
                }
            } catch (JMSException e) {
                LOG.error("Exception reading message.", e);
            }
        }
    }
}
