package com.horizon.bankerplus.utilities;

import com.horizon.bankerplus.BankerPlus;

public enum Messages {

    PREFIX(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("prefix")),
    NO_PERMISSION(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("no-permission")),
    DEPOSIT(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("deposit")),
    DEPOSIT_FAILURE(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("deposit-failure")),
    WITHDRAW(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("withdraw")),
    WITHDRAW_FAILURE(BankerPlus.getInstance().getMessagesConfig().getConfig().getString("withdraw-failure"));

    String message;

    Messages(String message) {
        this.message = BankerPlus.getInstance().getMessagesConfig().getConfig().getString("prefix") +  message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Replacement... replacements) {
        for (Replacement replacement : replacements) {
            message = message.replace(replacement.getThen(), replacement.getNow());
        }
        return message;
    }
}
