package com.horizon.bankerplus.utilities;

import lombok.Getter;
import org.bukkit.entity.Player;

public class AmountSender {

    @Getter
    private final Player player;

    @Getter
    private final boolean type;

    public AmountSender(Player player, boolean type) {
        this.player = player;
        this.type = type;
    }
}
