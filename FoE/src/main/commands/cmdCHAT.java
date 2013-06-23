package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCHAT implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public Replaces			replace;
	
	public cmdCHAT() {
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chatcmd")) {
			try {
				String playerName = sender.getName();
				replace = new Replaces(playerName);
				if ((sender.isOp()) || (sender.hasPermission("FoE.VypnoutChat"))) {
					if (fm.Chat) {
						fm.Chat = false;
						Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("VypnoutChat.KdyzSeVypne"), playerName));
					} else {
						fm.Chat = true;
						Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("VypnoutChat.KdyzSeZapne"), playerName));
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