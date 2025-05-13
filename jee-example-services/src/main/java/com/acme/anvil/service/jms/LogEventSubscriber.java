
package com.acme.anvil.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import com.acme.anvil.vo.LogEvent;

@Component
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
