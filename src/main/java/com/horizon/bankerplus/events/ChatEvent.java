package com.horizon.bankerplus.events;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.AmountSender;
import com.horizon.bankerplus.utilities.Replacement;
import com.horizon.bankerplus.utilities.chat.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        for (AmountSender sender : BankerPlus.getInstance().getAmountSenders()) {
            if (sender.getPlayer().getUniqueId().equals(event.getPlayer().getUniqueId())) {
                event.setCancelled(true);
                if (sender.isType()) {
                    try {
                        event.getPlayer().sendMessage(BankerPlus.getInstance().getBalanceManger().deposit(event.getPlayer(), Double.parseDouble(event.getMessage())));
                    } catch (Exception ignored) {
                        event.getPlayer().sendMessage(Messages.INVALID_VALUE.getMessage(new Replacement("{VALUE}", event.getMessage())));
                    }
                } else {
                    try {
                        event.getPlayer().sendMessage(BankerPlus.getInstance().getBalanceManger().withdraw(event.getPlayer(), Double.parseDouble(event.getMessage())));
                    } catch (Exception ignored) {
                        event.getPlayer().sendMessage(Messages.INVALID_VALUE.getMessage(new Replacement("{VALUE}", event.getMessage())));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHit(PlayerInteractEvent event) {
        for (AmountSender sender : BankerPlus.getInstance().getAmountSenders()) {
            if (BankerPlus.getInstance().getAmountSenders() == null) {
                return;
            }
            if (sender == null) {
                continue;
            }
            if (sender.getPlayer().getUniqueId().equals(event.getPlayer().getUniqueId())) {
                if (sender.isType()) {
                    event.getPlayer().sendMessage(Messages.CANCELLED_ACTION.getMessage(new Replacement("{ACTION}", "deposit")));
                } else {
                    event.getPlayer().sendMessage(Messages.CANCELLED_ACTION.getMessage(new Replacement("{ACTION}", "withdraw")));
                }
                BankerPlus.getInstance().getAmountSenders().remove(sender);
            }
        }
    }
}
