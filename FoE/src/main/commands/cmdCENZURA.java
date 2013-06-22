package main.commands;

import java.util.List;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;
import main.Replaces;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCENZURA implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm;
	public ErrorManager		err;
	
	public cmdCENZURA() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cenzuracmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Cenzura"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " add [Slovo]  " + ChatColor.GOLD + "Prida sproste slovo do listu.");
						sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " del [Slovo]  " + ChatColor.GOLD + "Odstrani sproste slovo z listu.");
					} else if (args[0].equalsIgnoreCase("add")) {
						List<String> b = cm.config.getStringList("Cenzura.Slova");
						if (!b.contains(args[1])) {
							b.add(args[1]);
							cm.config.set("Cenzura.Slova", b);
							sender.sendMessage(args[1] + " bylo pridano do cenzury");
							cm.saveConfig(cm.config, cm.configFile);
						} else {
							sender.sendMessage(ChatColor.RED + "Toto slovo v cenzure jiz je!");
						}
					} else if (args[0].equalsIgnoreCase("del")) {
						List<String> b = cm.config.getStringList("Cenzura.Slova");
						if (b.contains(args[1])) {
							b.remove(args[1]);
							cm.config.set("Cenzura.Slova", b);
							sender.sendMessage(args[1] + " bylo odstraneno z cenzury");
							cm.saveConfig(cm.config, cm.configFile);
						} else {
							sender.sendMessage(ChatColor.RED + "Toto slovo neni v cenzure!");
						}
					} else {
						sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " [Slovo]  " + ChatColor.GOLD + "Prida sproste slovo do listu.");
						sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " [Slovo]  " + ChatColor.GOLD + "Odstrani sproste slovo z listu.");
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
}