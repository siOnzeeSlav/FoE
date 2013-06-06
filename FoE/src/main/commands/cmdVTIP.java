package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdVTIP implements CommandExecutor {
	public FoE		plugin;
	public String	vysledek	= "";
	
	public cmdVTIP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender odesilatel, Command prikaz, String label, String[] args) {
		if (prikaz.getName().equalsIgnoreCase("vtipcmd")) {
			try {
				String jmenoHrace = odesilatel.getName();
				if ((odesilatel.isOp()) || (odesilatel.hasPermission("FoE.Vtip"))) {
					if (args.length == 0) {
						odesilatel.sendMessage(plugin.config.getString("Prikazy.Vtip") + " [CISLO]  " + ChatColor.GOLD + "Pro zobrazení vtipu.");
					} else {
						int cislo = Integer.valueOf(args[0]);
						odesilatel.sendMessage(plugin.vtipy.get(cislo));
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