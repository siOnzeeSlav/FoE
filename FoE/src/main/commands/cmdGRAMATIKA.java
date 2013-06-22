package main.commands;

import java.util.Iterator;
import java.util.List;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdGRAMATIKA implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public Replaces			replace;
	
	public cmdGRAMATIKA() {
		err = new ErrorManager();
		cm = new ConfigManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gramatikacmd")) {
			try {
				String playerName = sender.getName();
				replace = new Replaces(playerName);
				if ((sender.isOp()) || (sender.hasPermission("FoE.Gramatika"))) {
					if (args.length == 0) {
						sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " add cele [Slovo] [Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
						sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
						sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo]  " + ChatColor.GOLD + "Odstrani slovo z listu.");
					} else if (args[0].equalsIgnoreCase("add")) {
						String duvod = "";
						for (int i = 4; i < args.length; i++) {
							duvod = duvod + (i > 0 ? " " : "") + args[i];
						}
						if (args[1].equalsIgnoreCase("Vsude")) {
							List<String> b = cm.config.getStringList("Gramatika.Vsude");
							if (args.length < 5) {
								sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
								sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
							} else {
								Iterator<String> it = b.iterator();
								if (it.hasNext()) {
									if (!b.contains(args[2])) {
										b.add(args[2] + "," + args[3]);
										cm.config.set("Gramatika.Vsude", b);
										cm.config.set("Gramatika.Duvody." + args[2], duvod);
										sender.sendMessage(args[2] + "," + args[3] + " bylo pridano do vsude s duvodem" + duvod);
										cm.saveConfig(cm.config, cm.configFile);
									} else {
										sender.sendMessage(args[2] + " jiz je v seznamu!");
									}
								}
							}
						} else if (args[1].equalsIgnoreCase("Cele")) {
							List<String> b = cm.config.getStringList("Gramatika.Cele");
							if (args.length < 5) {
								sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
								sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo],[Slovo] [Oduvodneni]  " + ChatColor.GOLD + "Prida slovo do listu.");
							} else {
								Iterator<String> it = b.iterator();
								if (it.hasNext()) {
									if (!b.contains(args[2])) {
										b.add(args[2] + "," + args[3]);
										cm.config.set("Gramatika.Cele", b);
										cm.config.set("Gramatika.Duvody." + args[2], duvod);
										sender.sendMessage(args[2] + "," + args[3] + " bylo pridano do cele s duvodem" + duvod);
										cm.saveConfig(cm.config, cm.configFile);
									} else {
										sender.sendMessage(args[2] + " jiz je v seznamu!");
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
							List<String> e = cm.config.getStringList("Gramatika.Vsude");
							List<String> f = cm.config.getStringList("Gramatika.Cele");
							Iterator<String> it = e.iterator();
							Iterator<String> it2 = f.iterator();
							if (args.length > 2) {
								if (it.hasNext()) {
									if (e.contains(args[1] + "," + args[2])) {
										e.remove(args[1] + "," + args[2]);
										cm.config.set("Gramatika.Duvody." + args[1], null);
										sender.sendMessage("Odstranil jste " + args[1] + "," + args[2] + " z Vsude");
									}
								}
								if (it2.hasNext()) {
									if (f.contains(args[1] + "," + args[2])) {
										f.remove(args[1] + "," + args[2]);
										cm.config.set("Gramatika.Duvody." + args[1], null);
										sender.sendMessage("Odstranil jste " + args[1] + "," + args[2] + " z Cele");
									}
								}
							} else {
								sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " [Slovo] [Slovo]  " + ChatColor.GOLD + "Odstrani slovo z listu.");
							}
							cm.config.set("Gramatika.Vsude", e);
							cm.config.set("Gramatika.Cele", f);
							cm.saveConfig(cm.config, cm.configFile);
						}
					}
				} else
					sender.sendMessage(replace.PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
}