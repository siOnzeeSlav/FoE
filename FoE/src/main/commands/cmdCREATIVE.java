package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCREATIVE implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public Replaces			replace;
	
	public cmdCREATIVE() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("creativecmd")) {
			try {
				String playerName = sender.getName();
				replace = new Replaces(playerName);
				if ((sender.isOp()) || (sender.hasPermission("FoE.Creative"))) {
					Player player = (Player) sender;
					if (player.getGameMode() == GameMode.CREATIVE) {
						player.sendMessage(replace.PlayerName(cm.config.getString("herniRezimy.Zpravy.MaCreative"), playerName));
						return true;
					}
					player.setGameMode(GameMode.CREATIVE);
					Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("herniRezimy.Zpravy.Creative"), playerName));
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