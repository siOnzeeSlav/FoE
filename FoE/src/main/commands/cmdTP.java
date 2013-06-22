package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.PlayerManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdTP implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public Replaces			replace;
	
	public cmdTP() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpcmd")) {
			try {
				String playerName = sender.getName();
				replace = new Replaces(playerName);
				if ((sender.isOp()) || (sender.hasPermission("FoE.Tp"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.Teleport") + " [JMENO]");
					} else {
						Player player = (Player) sender;
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							player.teleport(target);
							sender.sendMessage(replace.PlayerName(cm.config.getString("TP.Zprava.Uspesne"), target.getName()));
						} else {
							sender.sendMessage(replace.PlayerName(cm.config.getString("TP.Zprava.Offline"), args[0]));
							PlayerManager pm = new PlayerManager(args[0]);
							pm.loadPlayer();
							if (pm.uziv.contains("X") && pm.uziv.contains("Y") && pm.uziv.contains("Z") && pm.uziv.contains("Svet")) {
								player.teleport(new Location(Bukkit.getWorld(pm.uziv.getString("Svet")), pm.uziv.getDouble("X"), pm.uziv.getDouble("Y"), pm.uziv.getDouble("Z")));
							} else {
								sender.sendMessage(replace.PlayerName(cm.config.getString("TP.Zprava.NeniZaznamenan"), args[0]));
							}
						}
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