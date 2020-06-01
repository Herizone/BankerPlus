package com.horizon.bankerplus.utilities.items;

import com.horizon.bankerplus.BankerPlus;
import com.horizon.bankerplus.utilities.Replacement;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum Items {

    DEPOSIT(new ItemBuilder(XMaterial.CHEST.parseMaterial(), 1).setName("&aDeposit").setLore("&eClick to deposit.").toItemStack()),
    DEPOSIT_PERCENTAGE(new ItemBuilder(XMaterial.CHEST.parseMaterial(), 1).setName("&aDeposit {PERCENTAGE}% (&6${AMOUNT}&a)").setLore("&eClick to deposit.").toItemStack()),
    DEPOSIT_AMOUNT(new ItemBuilder(XMaterial.CHEST.parseMaterial(), 1).setName("&aDeposit Amount").setLore("&eClick to enter amount.").toItemStack()),
    WITHDRAW(new ItemBuilder(XMaterial.DROPPER.parseMaterial(), 1).setName("&aWithdraw").setLore("&eClick to withdraw.").toItemStack()),
    WITHDRAW_PERCENTAGE(new ItemBuilder(XMaterial.DROPPER.parseMaterial(), 1).setName("&aWithdraw {PERCENTAGE}% (&6${AMOUNT}&a)").setLore("&eClick to withdraw.").toItemStack()),
    WITHDRAW_AMOUNT(new ItemBuilder(XMaterial.DROPPER.parseMaterial(), 1).setName("&aWithdraw Amount").setLore("&eClick to enter amount.").toItemStack()),
    BALANCE(new ItemBuilder(XMaterial.PAPER.parseMaterial() ,1).setName("&aBank Balance (&6{BANK_BALANCE}&a)").setLore("&aVault Balance (&6{VAULT_BALANCE}&a)").toItemStack()),
    BACK(new ItemBuilder(XMaterial.BARRIER.parseMaterial(), 1).setName("&cGo Back").setLore("&eClick to return to the menu.").toItemStack());

    private ItemStack itemStack;

    Items(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack parse() {
        return itemStack;
    }

    public ItemStack parse(Replacement... replacements) {
        ItemStack item = new ItemStack(itemStack.getType(), itemStack.getAmount());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(BankerPlus.getInstance().colorize(itemStack.getItemMeta().getDisplayName()));
        ArrayList<String> lore = new ArrayList<>();
        for (Replacement replacement : replacements) {
            for (String loreLine : itemStack.getItemMeta().getLore()) {
                lore.add(BankerPlus.getInstance().colorize(loreLine.replace(replacement.getThen(), replacement.getNow())));
            }
            if (meta.getDisplayName().contains(replacement.getThen())) {
                meta.setDisplayName(meta.getDisplayName().replace(replacement.getThen(), replacement.getNow()));
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
