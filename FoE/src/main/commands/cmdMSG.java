package main.commands;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.MySQL;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdMSG implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public Replaces			replace;
	public FeaturesManager	fm;
	public MySQL			mysql;
	
	public cmdMSG() {
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager();
		if (fm.mysqlIsEnabled)
			mysql = new MySQL();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msgcmd")) {
			try {
				if (!sender.hasPermission("FoE.Msg"))
					return true;
				
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(replace.PlayerName(cm.config.getString("Msg.Zprava.jeOffline"), args[0]));
					return true;
				}
				String playerName = sender.getName();
				replace = new Replaces(playerName);
				if (args.length < 1) {
					sender.sendMessage(cm.config.getString("Prikazy.Msg") + " [JMENO] [TEXT]  " + ChatColor.GOLD + "Pro poslani soukrome zpravy.");
				} else if (args.length > 1) {
					String message = "";
					for (int i = 1; i < args.length; i++) {
						message = (message + (i > 1 ? " " : "") + args[i]);
					}
					String targetName = target.getName();
					sender.sendMessage(reFormat(cm.config.getString("Msg.Format"), playerName, targetName, message));
					target.sendMessage(reFormat(cm.config.getString("Msg.Format"), playerName, targetName, message));
					if (fm.mysqlIsEnabled)
						MySQL_Message(playerName, targetName, message);
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
	
	public void MySQL_Message(String playerName, String targetName, String message) {
		try {
			if (playerName == null || targetName == null || message == null)
				return;
			mysql.query("INSERT INTO `FoE_Zpravy` (player, prijemce, zprava, datum) VALUES ('" + playerName + "', '" + targetName + "', '" + message + "', '" + System.currentTimeMillis() + "')");
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public String reFormat(String configMessage, String playerName, String targetName, String message) {
		if (configMessage.matches(".*\\{JMENO}.*")) {
			configMessage = configMessage.replaceAll("\\{JMENO}", playerName);
		}
		if (configMessage.matches(".*\\{TARGET}.*")) {
			configMessage = configMessage.replaceAll("\\{TARGET}", targetName);
		}
		if (configMessage.matches(".*\\{ZPRAVA}.*")) {
			configMessage = configMessage.replaceAll("\\{ZPRAVA}", message);
		}
		configMessage = configMessage.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return configMessage;
	}
}