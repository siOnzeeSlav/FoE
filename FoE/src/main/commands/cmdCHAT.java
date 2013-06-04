package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdCHAT implements CommandExecutor {
	public FoE	plugin;
	
	public cmdCHAT(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender odesilatel, Command prikaz, String label, String[] args) {
		if (prikaz.getName().equalsIgnoreCase("chatcmd")) {
			try {
				String jmenoHrace = odesilatel.getName();
				if ((odesilatel.isOp()) || (odesilatel.hasPermission("FoE.VypnoutChat"))) {
					if (plugin.Chat) {
						plugin.Chat = false;
						Bukkit.broadcastMessage(plugin.nahradit(plugin.config.getString("VypnoutChat.KdyzSeVypne"), jmenoHrace));
					} else {
						plugin.Chat = true;
						Bukkit.broadcastMessage(plugin.nahradit(plugin.config.getString("VypnoutChat.KdyzSeZapne"), jmenoHrace));
					}
				} else {
					odesilatel.sendMessage(plugin.nahradit(plugin.config.getString("Ostatni.KdyzNemaOpravneni"), jmenoHrace));
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