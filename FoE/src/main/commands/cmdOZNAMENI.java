package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdOZNAMENI implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public cmdOZNAMENI(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("zpravacmd")) {
			try {
				String vysledek = "";
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Oznameni"))) {
					for (int i = 0; i < args.length; i++) {
						vysledek = vysledek + (i > 0 ? " " : "") + args[i];
					}
					if (vysledek.isEmpty()) {
						sender.sendMessage(ChatColor.RED + "Nemuzete odeslat prazdny text!");
					} else {
						Bukkit.broadcastMessage(plugin.nahradit(cm.config.getString("Oznameni.Prefix"), jmenoHrace) + " " + plugin.nahradit(cm.config.getString("Oznameni.Suffix"), jmenoHrace) + vysledek);
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