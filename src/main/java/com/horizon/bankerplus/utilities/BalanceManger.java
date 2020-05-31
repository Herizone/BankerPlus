package com.horizon.bankerplus.utilities;

import com.horizon.bankerplus.BankerPlus;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BalanceManger {

    public double getBalance(UUID uuid) {
        if (BankerPlus.getInstance().getUsersConfig().getConfig().contains("users." + uuid)) {
            return BankerPlus.getInstance().getUsersConfig().getConfig().getDouble("users." + uuid);
        } else {
            return 0;
        }
    }

    public String deposit(Player player, double amount) {
        if (amount <= BankerPlus.getEconomy().getBalance(player)) {
            if (BankerPlus.getInstance().getUsersConfig().getConfig().contains("users." + player.getUniqueId())) {
                BankerPlus.getInstance().getUsersConfig().set("users." + player.getUniqueId(), BankerPlus.getInstance().getUsersConfig().getConfig().getDouble("users." + player.getUniqueId()) + amount);
                BankerPlus.getEconomy().withdrawPlayer(player, amount);
            } else {
                BankerPlus.getInstance().getUsersConfig().set("users." + player.getUniqueId(), amount);
                BankerPlus.getEconomy().withdrawPlayer(player, amount);
            }
            return Messages.DEPOSIT.getMessage(new Replacement("{AMOUNT}", "" + amount), new Replacement("{BALANCE}", "" + getBalance(player.getUniqueId())));
        }
        return Messages.DEPOSIT_FAILURE.getMessage(new Replacement("{AMOUNT}", "" + amount), new Replacement("{BALANCE}", "" + BankerPlus.getEconomy().getBalance(player)));
    }

    public String withdraw(Player player, double amount) {
        if (amount <= getBalance(player.getUniqueId())) {
            if (BankerPlus.getInstance().getUsersConfig().getConfig().contains("users." + player.getUniqueId())) {
                BankerPlus.getInstance().getUsersConfig().set("users." + player.getUniqueId(), BankerPlus.getInstance().getUsersConfig().getConfig().getDouble("users." + player.getUniqueId()) - amount);
                BankerPlus.getEconomy().depositPlayer(player, amount);
            } else {
                BankerPlus.getInstance().getUsersConfig().set("users." + player.getUniqueId(), amount);
                BankerPlus.getEconomy().depositPlayer(player, amount);
            }
            return Messages.WITHDRAW.getMessage(new Replacement("{AMOUNT}", "" + amount), new Replacement("{BALANCE}", "" + getBalance(player.getUniqueId())));
        }
        return Messages.WITHDRAW_FAILURE.getMessage(new Replacement("{AMOUNT}", "" + amount), new Replacement("{BALANCE}", "" + getBalance(player.getUniqueId())));
    }

    public void interestAll() {
        if (BankerPlus.getInstance().getUsersConfig().getConfig().contains("users")) {
            for (String uuid : BankerPlus.getInstance().getUsersConfig().getConfig().getConfigurationSection("users").getKeys(false)) {
                double startAmount = BankerPlus.getInstance().getUsersConfig().getConfig().getDouble("users." + uuid);
                double percentage = BankerPlus.getInstance().getSettingsConfig().getConfig().getDouble("interest-rate");
                double endAmount = (startAmount * (percentage / 100)) + startAmount;
                BankerPlus.getInstance().getUsersConfig().set("users." + uuid, "" + endAmount);
            }
        }
    }
}
