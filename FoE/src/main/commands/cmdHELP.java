package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdHELP implements CommandExecutor {
	public FoE	plugin;
	
	public cmdHELP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("helpcmd")) {
			try {
				if (sender.hasPermission("FoE.HELP")) {
					if (args.length > 0) {
						String helpMessage = "";
						for (int i = 0; i < args.length; i++) {
							helpMessage = (helpMessage + (i > 1 ? " " : "") + args[i]);
						}
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.hasPermission("FoE.HELP.Videt")) {
								p.sendMessage(sender.getName() + ": " + helpMessage);
							}
						}
						plugin.helpMessageToLog(sender.getName(), helpMessage);
					} else {
						sender.sendMessage("Zadejte text.");
					}
				} else {
					sender.sendMessage(plugin.nahradit(plugin.config.getString("Ostatni.KdyzNemaOpravneni"), sender.getName()));
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