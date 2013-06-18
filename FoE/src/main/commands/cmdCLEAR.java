package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCLEAR implements CommandExecutor {
	public FoE				plugin;
	public String			vysledek	= "";
	public ConfigManager	cm			= new ConfigManager();
	public ErrorManager		err			= new ErrorManager();
	
	public cmdCLEAR(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearcmd")) {
			try {
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Clear"))) {
					int i = 0;
					while (i < 100) {
						i++;
						Bukkit.broadcastMessage("");
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