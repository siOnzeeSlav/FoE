package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.BanManager;
import main.ConfigManager;
import main.FoE;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdKICK implements CommandExecutor {
	public FoE				plugin;
	public BanManager		bm	= new BanManager();
	public ConfigManager	cm	= new ConfigManager();
	
	public cmdKICK(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kickcmd")) {
			try {
				if (sender.hasPermission("FoE.Kick")) {
					if (args.length > 0) {
						if (args.length > 1) {
							String reason = "";
							for (int i = 1; i < args.length; i++) {
								reason = (reason + (i > 1 ? " " : "") + args[i]);
							}
							bm.kickPlayer(sender.getName(), args[0], reason);
						} else {
							sender.sendMessage("Zadejte duvod.");
						}
					} else {
						sender.sendMessage("Zadejte jmeno hrace.");
					}
				} else {
					sender.sendMessage(plugin.nahradit(cm.config.getString("Ostatni.KdyzNemaOpravneni"), sender.getName()));
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