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

public class cmdA implements CommandExecutor {
	public FoE		plugin;
	public String	vysledek	= "";
	
	public cmdA(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender odesilatel, Command prikaz, String label, String[] args) {
		if (prikaz.getName().equalsIgnoreCase("acmd")) {
			try {
				String jmenoHrace = odesilatel.getName();
				if ((odesilatel.isOp()) || (odesilatel.hasPermission("FoE.AdminChat.Psat"))) {
					if (args.length == 0) {
						odesilatel.sendMessage(plugin.config.getString("Prikazy.AdminChat") + " [TEXT]  " + ChatColor.GOLD + "Pro psaní do adminchatu.");
					} else {
						vysledek = "";
						for (int i = 0; i < args.length; i++) {
							vysledek = (vysledek + (i > 1 ? " " : " ") + args[i]);
						}
						for (Player p : Bukkit.getOnlinePlayers()) {
							if ((p.hasPermission("FoE.AdminChat.Videt")) || (p.isOp()))
								p.sendMessage(nahradit(plugin.config.getString("AdminChat.Zprava"), odesilatel.getName()));
						}
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
	
	public String nahradit(String zprava, String hrac) {
		if (zprava.matches(".*\\{JMENO}.*")) {
			zprava = zprava.replaceAll("\\{JMENO}", hrac);
		}
		if (zprava.matches(".*\\{ZPRAVA}.*")) {
			zprava = zprava.replaceAll("\\{ZPRAVA}", vysledek);
		}
		zprava = zprava.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return zprava;
	}
}