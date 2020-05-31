package com.horizon.bankerplus.utilities.config;

import com.horizon.bankerplus.BankerPlus;
import org.bukkit.command.ConsoleCommandSender;

import java.util.UUID;

public class UsersConfig extends Config {

    public UsersConfig() {
        super(BankerPlus.getInstance(), "users.yml");
    }

}
