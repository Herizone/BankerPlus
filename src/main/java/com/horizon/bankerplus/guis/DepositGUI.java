package com.horizon.bankerplus.guis;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.AmountSender;
import com.horizon.bankerplus.utilities.Replacement;
import com.horizon.bankerplus.utilities.chat.Messages;
import com.horizon.bankerplus.utilities.items.Items;
import com.yakovliam.yakocoreapi.gui.DynGui;
import org.bukkit.entity.Player;

public class DepositGUI extends DynGui {

    public DepositGUI(Player player) {
        super("Deposit - " + player.getName(), 3);
        double vaultBalance = BankerPlus.getEconomy().getBalance(player);
        setItemInteraction(10, Items.DEPOSIT_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "10"), new Replacement("{AMOUNT}", "" + (vaultBalance * 0.1))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().deposit(player, (vaultBalance * 0.1)));
            p.closeInventory();
        });
        setItemInteraction(11, Items.DEPOSIT_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "50"), new Replacement("{AMOUNT}", "" + (vaultBalance * 0.5))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().deposit(player, (vaultBalance * 0.5)));
            p.closeInventory();
        });
        setItemInteraction(12, Items.DEPOSIT_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "100"), new Replacement("{AMOUNT}", "" + (vaultBalance * 1))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().deposit(player, (vaultBalance * 1)));
            p.closeInventory();
        });
        setItemInteraction(14, Items.DEPOSIT_AMOUNT.parse(), (p, e) -> {
            p.closeInventory();
            p.sendMessage(Messages.ENTER_AMOUNT.getMessage());
            BankerPlus.getInstance().getAmountSenders().add(new AmountSender(player, true));
        });
        setItem(16, Items.BALANCE.parse(new Replacement("{BANK_BALANCE}", "" + BankerPlus.getInstance().getBalanceManger().getBalance(player.getUniqueId())), new Replacement("{VAULT_BALANCE}", "" + BankerPlus.getEconomy().getBalance(player))));
        setItemInteraction(22, Items.BACK.parse(), (p, e) -> {
            new BankGUI(p).open(p);
        });
    }

}
