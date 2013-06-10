package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdTP implements CommandExecutor {
	public FoE	plugin;
	
	public cmdTP(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpcmd")) {
			try {
				String playerName = sender.getName();
				if ((sender.isOp()) || (sender.hasPermission("FoE.Tp"))) {
					if (args.length == 0) {
						sender.sendMessage(plugin.config.getString("Prikazy.Teleport") + " [JMENO]");
					} else {
						Player player = (Player) sender;
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							player.teleport(target);
							sender.sendMessage(plugin.nahradit(plugin.config.getString("TP.Zprava.Uspesne"), target.getName()));
						} else {
							sender.sendMessage(plugin.nahradit(plugin.config.getString("TP.Zprava.Offline"), args[0]));
							plugin.uzivatel(args[0]);
							if (plugin.uziv.contains("X") && plugin.uziv.contains("Y") && plugin.uziv.contains("Z") && plugin.uziv.contains("Svet")) {
								player.teleport(new Location(Bukkit.getWorld(plugin.uziv.getString("Svet")), plugin.uziv.getDouble("X"), plugin.uziv.getDouble("Y"), plugin.uziv.getDouble("Z")));
							} else {
								sender.sendMessage(plugin.nahradit(plugin.config.getString("TP.Zprava.NeniZaznamenan"), args[0]));
							}
						}
					}
				} else {
					sender.sendMessage(plugin.nahradit(plugin.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
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