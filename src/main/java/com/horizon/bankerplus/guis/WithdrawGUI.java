package com.horizon.bankerplus.guis;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.AmountSender;
import com.horizon.bankerplus.utilities.Replacement;
import com.horizon.bankerplus.utilities.chat.Messages;
import com.horizon.bankerplus.utilities.items.Items;
import com.yakovliam.yakocoreapi.gui.DynGui;
import org.bukkit.entity.Player;

public class WithdrawGUI extends DynGui {

    public WithdrawGUI(Player player) {
        super("Withdraw - " + player.getName(), 3);
        double bankBalance = BankerPlus.getInstance().getBalanceManger().getBalance(player.getUniqueId());
        setItemInteraction(10, Items.WITHDRAW_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "10"), new Replacement("{AMOUNT}", "" + (bankBalance * 0.1))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().withdraw(player, (bankBalance * 0.1)));
            p.closeInventory();
        });
        setItemInteraction(11, Items.WITHDRAW_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "50"), new Replacement("{AMOUNT}", "" + (bankBalance * 0.5))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().withdraw(player, (bankBalance * 0.5)));
            p.closeInventory();
        });
        setItemInteraction(12, Items.WITHDRAW_PERCENTAGE.parse(new Replacement("{PERCENTAGE}", "100"), new Replacement("{AMOUNT}", "" + (bankBalance * 1))), (p, e) -> {
            p.sendMessage(BankerPlus.getInstance().getBalanceManger().withdraw(player, (bankBalance * 1)));
            p.closeInventory();
        });
        setItemInteraction(14, Items.WITHDRAW_AMOUNT.parse(), (p, e) -> {
            p.closeInventory();
            p.sendMessage(Messages.ENTER_AMOUNT.getMessage());
            BankerPlus.getInstance().getAmountSenders().add(new AmountSender(player, false));
        });
        setItem(16, Items.BALANCE.parse(new Replacement("{BANK_BALANCE}", "" + BankerPlus.getInstance().getBalanceManger().getBalance(player.getUniqueId())), new Replacement("{VAULT_BALANCE}", "" + BankerPlus.getEconomy().getBalance(player))));
    }
}
