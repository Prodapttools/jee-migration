
package com.acme.anvil.service;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.anvil.service.jms.LogEventPublisher;
import com.acme.anvil.vo.Item;
import com.acme.anvil.vo.LogEvent;

@Slf4j
@Service
public class ItemLookupService {

    @Autowired
    private LogEventPublisher logEventPublisher;

    public Item lookupItem(long id) {
        log.info("Calling lookupItem.");

        // Stubbed out.
        Item item = new Item();
        item.setId(id);

        final LogEvent le = new LogEvent(new Date(), "Returning Item: " + id);
        logEventPublisher.publishLogEvent(le);

        return item;
    }
}
