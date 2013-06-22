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

public class cmdSURVIVAL implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public Replaces			replace;
	
	public cmdSURVIVAL() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("survivalcmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Survival"))) {
					Player player = (Player) sender;
					replace = new Replaces(player);
					if (player.getGameMode() == GameMode.SURVIVAL) {
						player.sendMessage(replace.PlayerName(cm.config.getString("herniRezimy.Zpravy.MaSurvival"), playerName));
						return true;
					}
					player.setGameMode(GameMode.SURVIVAL);
					Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("herniRezimy.Zpravy.Survival"), playerName));
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