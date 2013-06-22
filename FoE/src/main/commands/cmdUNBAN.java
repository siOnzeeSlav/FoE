package main.commands;

import main.BanManager;
import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdUNBAN implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public BanManager		bm;
	
	public cmdUNBAN() {
		cm = new ConfigManager();
		err = new ErrorManager();
		bm = new BanManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("unbancmd")) {
			try {
				if (sender.hasPermission("FoE.Unban")) {
					if (args.length > 0) {
						if (args.length > 1) {
							String reason = "";
							for (int i = 1; i < args.length; i++) {
								reason = (reason + (i > 1 ? " " : "") + args[i]);
							}
							bm.unbanPlayer(sender.getName(), args[0], reason);
						} else {
							sender.sendMessage("Zadejte duvod.");
						}
					} else {
						sender.sendMessage("Zadejte jmeno hrace.");
					}
				} else {
					sender.sendMessage(new Replaces(sender.getName()).PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), sender.getName()));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
}