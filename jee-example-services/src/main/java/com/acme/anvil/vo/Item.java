
package com.acme.anvil.vo;

import org.springframework.stereotype.Component;

@Component
public class Item {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
