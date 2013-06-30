package eu.frelania.foe.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class cmdFoe implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("foe") && args.length < 1) {
			cmd(sender, "/foe", "Prikaz ktery zobrazi napovedu pro plugin FoE.");
		} else if (cmd.getName().equalsIgnoreCase("foe") && args.length > 0) {
			sender.sendMessage("Prikaz foe nepodporuje vice argumentu!");
		}
		return false;
	}
	
	public void cmd(CommandSender sender, String cmd, String info) {
		sender.sendMessage(ChatColor.GREEN + cmd + ChatColor.WHITE + "  --  " + ChatColor.GOLD + info + ".");
	}
	
}
