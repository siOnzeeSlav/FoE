package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import main.ConfigManager;
import main.FoE;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdGRAMATIKA implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	
	public cmdGRAMATIKA(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gramatikacmd")) {
			try {
				String jmenoHrace = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Gramatika"))) {
					if (args.length == 0) {
						sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " add cele [Slovo] [Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
						sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
						sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo]  " + ChatColor.GOLD + "Odstraní slovo z listu.");
					} else if (args[0].equalsIgnoreCase("add")) {
						String duvod = "";
						for (int i = 4; i < args.length; i++) {
							duvod = duvod + (i > 0 ? " " : "") + args[i];
						}
						if (args[1].equalsIgnoreCase("Vsude")) {
							List<String> b = plugin.config.getStringList("Gramatika.Vsude");
							if (args.length < 5) {
								sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
								sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
							} else {
								Iterator<String> it = b.iterator();
								if (it.hasNext()) {
									if (!b.contains(args[2])) {
										b.add(args[2] + "," + args[3]);
										plugin.config.set("Gramatika.Vsude", b);
										plugin.config.set("Gramatika.Duvody." + args[2], duvod);
										sender.sendMessage(args[2] + "," + args[3] + " bylo pøidáno do všude s dùvodem" + duvod);
										cm.saveConfig(plugin.config, plugin.configFile);
									} else {
										sender.sendMessage(args[2] + " již je v seznamu!");
									}
								}
							}
						} else if (args[1].equalsIgnoreCase("Cele")) {
							List<String> b = plugin.config.getStringList("Gramatika.Cele");
							if (args.length < 5) {
								sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
								sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Odùvodnìní]  " + ChatColor.GOLD + "Pøídá slovo do listu.");
							} else {
								Iterator<String> it = b.iterator();
								if (it.hasNext()) {
									if (!b.contains(args[2])) {
										b.add(args[2] + "," + args[3]);
										plugin.config.set("Gramatika.Cele", b);
										plugin.config.set("Gramatika.Duvody." + args[2], duvod);
										sender.sendMessage(args[2] + "," + args[3] + " bylo pøidáno do cele s dùvodem" + duvod);
										cm.saveConfig(plugin.config, plugin.configFile);
									} else {
										sender.sendMessage(args[2] + " již je v seznamu!");
									}
								}
							}
						} else {
							sender.sendMessage("Doplòte parameter! ' Cele, Vsude '");
						}
					} else if (args[0].equalsIgnoreCase("del")) {
						if (args.length == 1) {
							sender.sendMessage("Doplòte slovo!");
						} else {
							List<String> e = plugin.config.getStringList("Gramatika.Vsude");
							List<String> f = plugin.config.getStringList("Gramatika.Cele");
							Iterator<String> it = e.iterator();
							Iterator<String> it2 = f.iterator();
							if (args.length > 2) {
								if (it.hasNext()) {
									if (e.contains(args[1] + "," + args[2])) {
										e.remove(args[1] + "," + args[2]);
										plugin.config.set("Gramatika.Duvody." + args[1], null);
										sender.sendMessage("Odstranil jste " + args[1] + "," + args[2] + " z Všude");
									}
								}
								if (it2.hasNext()) {
									if (f.contains(args[1] + "," + args[2])) {
										f.remove(args[1] + "," + args[2]);
										plugin.config.set("Gramatika.Duvody." + args[1], null);
										sender.sendMessage("Odstranil jste " + args[1] + "," + args[2] + " z Celé");
									}
								}
							} else {
								sender.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo]  " + ChatColor.GOLD + "Odstraní slovo z listu.");
							}
							plugin.config.set("Gramatika.Vsude", e);
							plugin.config.set("Gramatika.Cele", f);
							cm.saveConfig(plugin.config, plugin.configFile);
						}
					}
				} else
					sender.sendMessage(plugin.nahradit(plugin.config.getString("Ostatni.KdyzNemaOpravneni"), jmenoHrace));
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