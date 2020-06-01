package com.horizon.bankerplus;

import com.horizon.bankerplus.cmds.BankCMD;
import com.horizon.bankerplus.events.ChatEvent;
import com.horizon.bankerplus.utilities.AmountSender;
import com.horizon.bankerplus.utilities.BalanceManger;
import com.horizon.bankerplus.utilities.config.MessagesConfig;
import com.horizon.bankerplus.utilities.config.SettingsConfig;
import com.horizon.bankerplus.utilities.config.UsersConfig;
import com.yakovliam.yakocoreapi.YakoCoreAPI;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BankerPlus extends JavaPlugin implements Listener {

    @Getter
    private static BankerPlus instance;

    @Getter
    private static Economy economy = null;

    @Getter
    private UsersConfig usersConfig;

    @Getter
    private MessagesConfig messagesConfig;

    @Getter
    private SettingsConfig settingsConfig;

    @Getter
    private BalanceManger balanceManger;

    @Getter
    private ArrayList<AmountSender> amountSenders;

    @Override
    public void onEnable() {
        // Initialize classes
        instance = this;
        YakoCoreAPI.getInstance(this);
        balanceManger = new BalanceManger();

        amountSenders = new ArrayList<>();

        // Check for economy plugin, and set it up if there is one.
        if (!setupEconomy()) {
            getLogger().severe("Vault economy plugin not found! Disabling Banker+!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register configurations
        usersConfig = new UsersConfig();
        messagesConfig = new MessagesConfig();
        settingsConfig = new SettingsConfig();

        // Start interest timer
        switch (getSettingsConfig().getConfig().getString("interest-frequency")) {
            case "HOURLY":
                getServer().getScheduler().runTaskTimer(this, () -> getBalanceManger().interestAll(), 3600 * 20L, 1L);
                break;
            case "DAILY":
                getServer().getScheduler().runTaskTimer(this, () -> getBalanceManger().interestAll(), 86400 * 20L, 1L);
                break;
            case "WEEKLY":
                getServer().getScheduler().runTaskTimer(this, () -> getBalanceManger().interestAll(), 604800 * 20L, 1L);
                break;
        }

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);

        getCommand("bank").setExecutor(new BankCMD());
    }

    public String colorize(String input) { return ChatColor.translateAlternateColorCodes('&', input); }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        amountSenders.removeIf(sender -> sender.getPlayer().getUniqueId().equals(event.getPlayer().getUniqueId()));
    }
}
