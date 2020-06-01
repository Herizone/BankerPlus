package com.horizon.bankerplus.utilities.chat;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.Replacement;
import org.bukkit.ChatColor;

public enum Messages {

    PREFIX(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("prefix")),
    NO_PERMISSION(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("no-permission")),
    MUST_BE_PLAYER(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("must-be-player")),
    DEPOSIT(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("deposit")),
    DEPOSIT_FAILURE(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("deposit-failure")),
    WITHDRAW(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("withdraw")),
    WITHDRAW_FAILURE(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("withdraw-failure")),
    ENTER_AMOUNT(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("enter-amount")),
    INVALID_VALUE(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("invalid-value")),
    CANCELLED_ACTION(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("cancelled-action"));

    String message;

    Messages(String message) {
        this.message = BankerPlus.getInstance().getMessagesConfig().getConfig().getString("prefix") +  message;
    }

    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getMessage(Replacement... replacements) {
        for (Replacement replacement : replacements) {
            message = message.replace(replacement.getThen(), replacement.getNow());
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
