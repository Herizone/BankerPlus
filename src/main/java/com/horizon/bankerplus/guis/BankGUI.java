package com.horizon.bankerplus.guis;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.Replacement;
import com.horizon.bankerplus.utilities.items.Items;
import com.yakovliam.yakocoreapi.gui.DynGui;
import org.bukkit.entity.Player;

public class BankGUI extends DynGui {

    public BankGUI(Player player) {
        super("Bank - " + player.getName(), 3);

        setItemInteraction(11, Items.DEPOSIT.parse(), (p, e) -> {
            new DepositGUI(player).open(player);
        });

        setItemInteraction(13, Items.WITHDRAW.parse(), (p, e) -> {
            new WithdrawGUI(player).open(player);
        });

        setItem(15, Items.BALANCE.parse(new Replacement("{BANK_BALANCE}", "" + BankerPlus.getInstance().getBalanceManger().getBalance(player.getUniqueId())), new Replacement("{VAULT_BALANCE}", "" + BankerPlus.getEconomy().getBalance(player))));
    }

}
