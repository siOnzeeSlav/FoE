package eu.frelania.foe.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdBan implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cmdBan") && args.length < 1) {
			if (!sender.hasPermission("FoE.vycistitChat")) {
				return true;
			}
			for (int i = 0; i < 100; i++) {
				Bukkit.broadcastMessage("");
			}
		} else if (cmd.getName().equalsIgnoreCase("cmdclear") && args.length > 0) {
			sender.sendMessage("Prikaz clear nepodporuje vice argumentu!");
		}
		return false;
	}
}
