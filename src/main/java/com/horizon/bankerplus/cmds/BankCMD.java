package com.horizon.bankerplus.cmds;

import com.horizon.bankerplus.guis.BankGUI;
import com.horizon.bankerplus.utilities.chat.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.MUST_BE_PLAYER.getMessage());
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("bank.use")) {
            new BankGUI(p).open(p);
        } else {
            p.sendMessage(Messages.NO_PERMISSION.getMessage());
        }
        return true;
    }
}
