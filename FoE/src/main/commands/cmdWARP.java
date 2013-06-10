package main.commands;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

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
	
	public cmdWARP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender odesilatel, Command prikaz, String label, String[] args) {
		if (prikaz.getName().equalsIgnoreCase("warpcmd")) {
			try {
				Player player = (Player) odesilatel;
				if (args.length == 0) {
					odesilatel.sendMessage(plugin.config.getString("Prikazy.Warp") + " [JMENO]  " + ChatColor.GOLD + "Teleportuje na warp..");
					odesilatel.sendMessage(plugin.config.getString("Prikazy.Warp") + " vytvorit [JMENO] [POPIS]  " + ChatColor.GOLD + "Pro vytvoøení warpu.");
					odesilatel.sendMessage(plugin.config.getString("Prikazy.Warp") + " odstranit [JMENO]  " + ChatColor.GOLD + "Pro odstranìní warpu.");
				} else if (args[0].equalsIgnoreCase("vytvorit")) {
					if (!odesilatel.hasPermission("FoE.Warp.Vytvorit")) {
						return true;
					}
					if (warp.contains(args[1])) {
						odesilatel.sendMessage("Warp " + args[1] + " již existuje.");
						return true;
					}
					if (args.length > 1) {
						String playerName = odesilatel.getName();
						String warpName = args[1];
						String description = "";
						for (int i = 1; i < args.length; i++) {
							description = (description + (i > 1 ? " " : " ") + args[i]);
						}
						Location loc = player.getLocation();
						Double X = loc.getX();
						Double Y = loc.getY();
						Double Z = loc.getZ();
						String world = player.getWorld().getName();
						warp.set(args[0] + ".X", X);
						warp.set(args[0] + ".Y", Y);
						warp.set(args[0] + ".Z", Z);
						warp.set(args[0] + ".world", world);
						warp.set(args[0] + ".popis", description);
						plugin.saveConfig(warp, warpFile);
						if (plugin.mysqlPovolit)
							plugin.MySQL_Warp(warpName, playerName, "AKTIVNI");
					} else {
						odesilatel.sendMessage(plugin.config.getString("Prikazy.Warp") + " vytvorit [JMENO] [POPIS]  " + ChatColor.GOLD + "Pro vytvoøení warpu.");
					}
				} else if (args[0].equalsIgnoreCase("odstranit")) {
					if (!odesilatel.hasPermission("FoE.Warp.Odstranit")) {
						return true;
					}
					if (!warp.contains(args[1])) {
						odesilatel.sendMessage("Warp " + args[1] + " neexistuje.");
						return true;
					}
					String warpName = args[1];
					String playerName = odesilatel.getName();
					warp.set(warpName, null);
					plugin.saveConfig(warp, warpFile);
					if (plugin.mysqlPovolit)
						plugin.MySQL_Warp(warpName, playerName, "ODSTRANENO");
				} else if (warp.contains(args[0])) {
					if (!odesilatel.hasPermission("FoE.Warp." + args[0]) && (!odesilatel.hasPermission("FoE.Warp.*"))) {
						return true;
					}
					String warpName = args[0];
					double X = warp.getDouble(warpName + ".X");
					double Y = warp.getDouble(warpName + ".Y");
					double Z = warp.getDouble(warpName + ".Z");
					World world = Bukkit.getWorld(warp.getString(warpName + ".world"));
					String description = warp.getString(warpName + ".popis");
					player.teleport(new Location(world, X, Y, Z));
					odesilatel.sendMessage(description);
				} else {
					odesilatel.sendMessage("Warp " + args[0] + " neexistuje.");
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