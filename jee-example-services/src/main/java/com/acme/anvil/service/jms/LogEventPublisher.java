
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
