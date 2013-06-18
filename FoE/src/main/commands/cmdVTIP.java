package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdVTIP implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public cmdVTIP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("vtipcmd")) {
			try {
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Vtip"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.Vtip") + " [CISLO]  " + ChatColor.GOLD + "Pro zobrazeni vtipu.");
					} else {
						int cislo = Integer.valueOf(args[0]);
						sender.sendMessage(plugin.vtipy.get(cislo));
					}
				} else {
					sender.sendMessage(plugin.nahradit(cm.config.getString("Ostatni.KdyzNemaOpravneni"), jmenoHrace));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
}