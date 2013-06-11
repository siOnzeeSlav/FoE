package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.ConfigManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCHAT implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	
	public cmdCHAT(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chatcmd")) {
			try {
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.VypnoutChat"))) {
					if (plugin.Chat) {
						plugin.Chat = false;
						Bukkit.broadcastMessage(plugin.nahradit(cm.config.getString("VypnoutChat.KdyzSeVypne"), jmenoHrace));
					} else {
						plugin.Chat = true;
						Bukkit.broadcastMessage(plugin.nahradit(cm.config.getString("VypnoutChat.KdyzSeZapne"), jmenoHrace));
					}
				} else {
					sender.sendMessage(plugin.nahradit(cm.config.getString("Ostatni.KdyzNemaOpravneni"), jmenoHrace));
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