package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdOZNAMENI implements CommandExecutor {
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("zpravacmd")) {
			try {
				String vysledek = "";
				String playerName = sender.getName();
				Replaces replace = new Replaces(playerName);
				if ((sender.isOp()) || (sender.hasPermission("FoE.Oznameni"))) {
					for (int i = 0; i < args.length; i++) {
						vysledek = vysledek + (i > 0 ? " " : "") + args[i];
					}
					if (vysledek.isEmpty()) {
						sender.sendMessage(ChatColor.RED + "Nemuzete odeslat prazdny text!");
					} else {
						Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("Oznameni.Prefix"), playerName) + " " + replace.PlayerName(cm.config.getString("Oznameni.Suffix"), playerName) + vysledek);
					}
				} else {
					sender.sendMessage(replace.PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
}