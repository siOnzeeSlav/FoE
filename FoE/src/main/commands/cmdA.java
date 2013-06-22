package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdA implements CommandExecutor {
	public String			vysledek	= "";
	public ConfigManager	cm;
	public ErrorManager		err;
	
	public cmdA() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("acmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.AdminChat.Psat"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.AdminChat") + " [TEXT]  " + ChatColor.GOLD + "Pro psani do adminchatu.");
					} else {
						vysledek = "";
						for (int i = 0; i < args.length; i++) {
							vysledek = (vysledek + (i > 1 ? " " : " ") + args[i]);
						}
						for (Player p : Bukkit.getOnlinePlayers()) {
							if ((p.hasPermission("FoE.AdminChat.Videt")) || (p.isOp()))
								p.sendMessage(nahradit(cm.config.getString("AdminChat.Zprava"), sender.getName()));
						}
					}
				} else {
					sender.sendMessage(new Replaces(playerName).PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
	
	public String nahradit(String zprava, String hrac) {
		if (zprava.matches(".*\\{JMENO}.*")) {
			zprava = zprava.replaceAll("\\{JMENO}", hrac);
		}
		if (zprava.matches(".*\\{ZPRAVA}.*")) {
			zprava = zprava.replaceAll("\\{ZPRAVA}", vysledek);
		}
		zprava = zprava.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return zprava;
	}
}