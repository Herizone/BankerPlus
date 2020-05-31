package com.horizon.bankerplus.utilities.config;

import com.horizon.bankerplus.BankerPlus;

public class MessagesConfig extends Config {

    public MessagesConfig() {
        super(BankerPlus.getInstance(), "messages.yml");
    }

}
