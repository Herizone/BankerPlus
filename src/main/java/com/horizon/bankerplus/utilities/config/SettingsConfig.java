package com.horizon.bankerplus.utilities.config;

import com.horizon.bankerplus.BankerPlus;

public class SettingsConfig extends Config {

    public SettingsConfig() {
        super(BankerPlus.getInstance(), "settings.yml");
    }

}
