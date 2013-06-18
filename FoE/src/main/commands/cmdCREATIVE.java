package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdCREATIVE implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public cmdCREATIVE(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("creativecmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Creative"))) {
					Player player = (Player) sender;
					if (player.getGameMode() == GameMode.CREATIVE) {
						player.sendMessage(plugin.nahradit(cm.config.getString("herniRezimy.Zpravy.MaCreative"), playerName));
						return true;
					}
					player.setGameMode(GameMode.CREATIVE);
					Bukkit.broadcastMessage(plugin.nahradit(cm.config.getString("herniRezimy.Zpravy.Creative"), playerName));
				} else {
					sender.sendMessage(plugin.nahradit(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
}