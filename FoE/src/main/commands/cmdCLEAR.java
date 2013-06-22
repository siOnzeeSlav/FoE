package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCLEAR implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	
	public cmdCLEAR() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearcmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Clear"))) {
					int i = 0;
					while (i < 100) {
						i++;
						Bukkit.broadcastMessage("");
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