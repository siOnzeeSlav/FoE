package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.Replaces;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdVTIP implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	
	public cmdVTIP() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("vtipcmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Vtip"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.Vtip") + " [CISLO]  " + ChatColor.GOLD + "Pro zobrazeni vtipu.");
					} else {
						int cislo = Integer.valueOf(args[0]);
						sender.sendMessage(fm.jokes.get(cislo));
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