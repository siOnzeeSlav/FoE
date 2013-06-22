package main.commands;

import java.io.File;
import java.sql.ResultSet;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.MySQL;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class cmdWARP implements CommandExecutor {
	public File					warpFile	= new File("plugins/FoE/warps.yml");
	public YamlConfiguration	warp		= YamlConfiguration.loadConfiguration(warpFile);
	public ConfigManager		cm;
	public ErrorManager			err;
	public FeaturesManager		fm;
	public Boolean				debug;
	public Replaces				replace;
	public MySQL				mysql;
	
	public cmdWARP() {
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager(cm);
		debug = fm.debug;
		mysql = new MySQL();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warpcmd")) {
			try {
				Player player = (Player) sender;
				replace = new Replaces(player);
				if (args.length == 0) {
					sender.sendMessage(cm.config.getString("Prikazy.Warp") + " [JMENO]  " + ChatColor.GOLD + "Teleportuje na warp.");
					sender.sendMessage(cm.config.getString("Prikazy.Warp") + " vytvorit [JMENO] [POPIS]  " + ChatColor.GOLD + "Pro vytvoreni warpu.");
					sender.sendMessage(cm.config.getString("Prikazy.Warp") + " odstranit [JMENO]  " + ChatColor.GOLD + "Pro odstraneni warpu.");
					sender.sendMessage(cm.config.getString("Prikazy.Warp") + " list  " + ChatColor.GOLD + "Pro zobrazení všech warpù.");
				} else if (args[0].equalsIgnoreCase("vytvorit")) {
					if (!sender.hasPermission("FoE.Warp.Vytvorit")) {
						return true;
					}
					if (args.length > 2) {
						if (warp.contains(args[1])) {
							sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Existuje"), args[1]));
							return true;
						}
						String playerName = sender.getName();
						String warpName = args[1];
						String description = "";
						for (int i = 2; i < args.length; i++) {
							description = (description + (i > 1 ? " " : "") + args[i]);
						}
						Location loc = player.getLocation();
						Double X = loc.getX();
						Double Y = loc.getY();
						Double Z = loc.getZ();
						String world = player.getWorld().getName();
						warp.set(warpName + ".X", X);
						warp.set(warpName + ".Y", Y);
						warp.set(warpName + ".Z", Z);
						warp.set(warpName + ".world", world);
						warp.set(warpName + ".popis", description);
						cm.saveConfig(warp, warpFile);
						if (debug)
							Bukkit.broadcastMessage("MySQL: " + fm.mysqlIsEnabled);
						if (fm.mysqlIsEnabled)
							MySQL_Warp(warpName, playerName, "AKTIVNI");
						
						sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Vytvorit"), warpName, description));
					} else {
						sender.sendMessage(cm.config.getString("Prikazy.Warp") + " vytvorit [JMENO] [POPIS]  " + ChatColor.GOLD + "Pro vytvoreni warpu.");
					}
				} else if (args[0].equalsIgnoreCase("odstranit")) {
					if (!sender.hasPermission("FoE.Warp.Odstranit")) {
						return true;
					}
					if (!warp.contains(args[1])) {
						sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Neexistuje"), args[0]));
						return true;
					}
					String warpName = args[1];
					String playerName = sender.getName();
					warp.set(warpName, null);
					cm.saveConfig(warp, warpFile);
					if (debug)
						Bukkit.broadcastMessage("MySQL: " + fm.mysqlIsEnabled);
					if (fm.mysqlIsEnabled)
						MySQL_Warp(warpName, playerName, "ODSTRANENO");
					sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Odstranit"), warpName));
				} else if (args[0].equalsIgnoreCase("list")) {
					if (warp.getConfigurationSection(warp.getRoot().getCurrentPath()).getKeys(false).size() > 0)
						sender.sendMessage("" + warp.getConfigurationSection(warp.getRoot().getCurrentPath()).getKeys(false));
					else
						sender.sendMessage(replace.Colors(cm.config.getString("Warp.Zprava.Prazdno")));
				} else if (warp.contains(args[0])) {
					if (!sender.hasPermission("FoE.Warp." + args[0]) && (!sender.hasPermission("FoE.Warp.*"))) {
						sender.sendMessage(replace.Colors(cm.config.getString("Warp.NemaOpravneni")));
						return true;
					}
					String warpName = args[0];
					double X = warp.getDouble(warpName + ".X");
					double Y = warp.getDouble(warpName + ".Y");
					double Z = warp.getDouble(warpName + ".Z");
					World world = Bukkit.getWorld(warp.getString(warpName + ".world"));
					String description = warp.getString(warpName + ".popis");
					player.teleport(new Location(world, X, Y, Z));
					sender.sendMessage(description);
				} else {
					sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Neexistuje"), args[0]));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
	
	public void MySQL_Warp(String warpName, String playerName, String typ) {
		try {
			if (warpName == null || playerName == null) {
				if (debug)
					Bukkit.broadcastMessage(warpName + " - " + playerName + " - " + typ + " - Neco je null");
				return;
			}
			if (typ == "AKTIVNI") {
				if (debug)
					Bukkit.broadcastMessage("TYP:AKTIVNI");
				ResultSet rs = mysql.query("SELECT `warp` FROM `FoE_Warpy` WHERE `warp` = '" + warpName + "'");
				if (rs.next()) {
					if (debug)
						Bukkit.broadcastMessage("Aktualizuju FoE_Warpy" + warpName);
					mysql.query("UPDATE `FoE_Warpy` SET `typ` = 'AKTIVNI', `datum` = '" + System.currentTimeMillis() + "' WHERE `warp` = '" + warpName + "'");
				} else {
					Bukkit.broadcastMessage("Vkladam FoE_Warpy" + warpName);
					mysql.query("INSERT INTO `FoE_Warpy` (warp, autor, datum, typ) VALUES (" + "'" + warpName + "'," + " '" + playerName + "', '" + System.currentTimeMillis() + "', 'AKTIVNI')");
				}
			}
			if (typ == "ODSTRANENO") {
				if (debug)
					Bukkit.broadcastMessage("TYP:ODSTRANENO");
				ResultSet rs = mysql.query("SELECT `warp` FROM `FoE_Warpy` WHERE `warp` = '" + warpName + "'");
				if (rs.next()) {
					mysql.query("UPDATE `FoE_Warpy` SET `typ` = 'ODSTRANENO' WHERE `warp` = '" + warpName + "'");
				}
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public String replace(String message, String warp, String description) {
		if (message.matches(".*\\{WARP}.*")) {
			message = message.replaceAll("\\{WARP}", warp);
		}
		if (message.matches(".*\\{POPIS}.*")) {
			message = message.replaceAll("\\{POPIS}", description);
		}
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String replace(String message, String warp) {
		if (message.matches(".*\\{WARP}.*")) {
			message = message.replaceAll("\\{WARP}", warp);
		}
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
}