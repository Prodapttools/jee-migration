
package com.acme.anvil.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acme.anvil.service.jms.LogEventPublisher;
import com.acme.anvil.vo.Item;
import com.acme.anvil.vo.LogEvent;

@Service
public class ItemLookupBean {

    private static final Logger LOG = LoggerFactory.getLogger(ItemLookupBean.class);
    
    public Item lookupItem(long id) {
        LOG.info("Calling lookupItem.");
        
        //stubbed out.
        Item item = new Item();
        item.setId(id);
        
        final LogEvent le = new LogEvent(new Date(), "Returning Item: " + id); 
        LogEventPublisher.publishLogEvent(le);
        
        return item;
    }
}
