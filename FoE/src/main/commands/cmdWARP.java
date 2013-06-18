package main.commands;

import java.io.File;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

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
	public FoE					plugin;
	public File					warpFile	= new File("plugins/FoE/warps.yml");
	public YamlConfiguration	warp		= YamlConfiguration.loadConfiguration(warpFile);
	public ConfigManager		cm			= new ConfigManager();
	public ErrorManager			err			= new ErrorManager();
	
	public cmdWARP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warpcmd")) {
			try {
				Player player = (Player) sender;
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
						if (plugin.debug)
							Bukkit.broadcastMessage("MySQL: " + plugin.mysqlPovolit);
						if (plugin.mysqlPovolit)
							plugin.MySQL_Warp(warpName, playerName, "AKTIVNI");
						
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
					if (plugin.debug)
						Bukkit.broadcastMessage("MySQL: " + plugin.mysqlPovolit);
					if (plugin.mysqlPovolit)
						plugin.MySQL_Warp(warpName, playerName, "ODSTRANENO");
					sender.sendMessage(replace(cm.config.getString("Warp.Zprava.Odstranit"), warpName));
				} else if (args[0].equalsIgnoreCase("list")) {
					if (warp.getConfigurationSection(warp.getRoot().getCurrentPath()).getKeys(false).size() > 0)
						sender.sendMessage("" + warp.getConfigurationSection(warp.getRoot().getCurrentPath()).getKeys(false));
					else
						sender.sendMessage(plugin.nahraditBarvy(cm.config.getString("Warp.Zprava.Prazdno")));
				} else if (warp.contains(args[0])) {
					if (!sender.hasPermission("FoE.Warp." + args[0]) && (!sender.hasPermission("FoE.Warp.*"))) {
						sender.sendMessage(plugin.nahraditBarvy(cm.config.getString("Warp.NemaOpravneni")));
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