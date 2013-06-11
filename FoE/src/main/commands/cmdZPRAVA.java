package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.ConfigManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdZPRAVA implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	
	public cmdZPRAVA(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("zpravacmd")) {
			try {
				String vysledek = "";
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Oznameni"))) {
					for (int i = 0; i < args.length; i++) {
						vysledek = vysledek + (i > 0 ? " " : "") + args[i];
					}
					if (vysledek.isEmpty()) {
						sender.sendMessage(ChatColor.RED + "Nemùžete odeslat prázdný text!");
					} else {
						Bukkit.broadcastMessage(plugin.nahradit(cm.config.getString("Oznameni.Prefix"), jmenoHrace) + " " + plugin.nahradit(cm.config.getString("Oznameni.Suffix"), jmenoHrace) + vysledek);
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