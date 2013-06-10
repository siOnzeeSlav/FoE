package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdMSG implements CommandExecutor {
	public FoE		plugin;
	public String	vysledek	= "";
	
	public cmdMSG(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msgcmd")) {
			try {
				if (!sender.hasPermission("FoE.Msg"))
					return true;
				
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(plugin.nahradit(plugin.config.getString("Msg.Zprava.jeOffline"), args[0]));
					return true;
				}
				String playerName = sender.getName();
				if (args.length < 1) {
					sender.sendMessage(plugin.config.getString("Prikazy.Msg") + " [JMENO] [TEXT]  " + ChatColor.GOLD + "Pro poslání soukromé zprávy.");
				} else if (args.length > 1) {
					String message = "";
					for (int i = 1; i < args.length; i++) {
						message = (message + (i > 1 ? " " : "") + args[i]);
					}
					String targetName = target.getName();
					sender.sendMessage(reFormat(plugin.config.getString("Msg.Format"), playerName, targetName, message));
					target.sendMessage(reFormat(plugin.config.getString("Msg.Format"), playerName, targetName, message));
					if (plugin.mysqlPovolit)
						plugin.MySQL_Message(playerName, targetName, message);
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