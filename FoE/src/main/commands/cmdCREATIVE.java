package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.ConfigManager;
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
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		return false;
	}
}