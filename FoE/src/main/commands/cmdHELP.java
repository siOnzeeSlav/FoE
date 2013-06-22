package main.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ConfigManager;
import main.ErrorManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdHELP implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	
	public cmdHELP() {
		cm = new ConfigManager();
		err = new ErrorManager();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("helpcmd")) {
			try {
				String playerName = sender.getName();
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
						helpMessageToLog(sender.getName(), helpMessage);
					} else {
						sender.sendMessage("Zadejte text.");
					}
				} else {
					sender.sendMessage(new Replaces(playerName).PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), sender.getName()));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		return false;
	}
	
	public void helpMessageToLog(String playerName, String Message) {
		try {
			File u = new File("plugins/FoE/help.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("# " + playerName + " - " + time + "\n" + Message + "\n");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}