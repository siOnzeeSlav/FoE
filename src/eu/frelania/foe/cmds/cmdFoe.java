package eu.frelania.foe.cmds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import eu.frelania.foe.main.FoE;

public class cmdFoe implements CommandExecutor {
	
	public FoE	foe;
	
	public cmdFoe(FoE plugin) {
		foe = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("foe") && args.length < 1) {
			sender.sendMessage("    _______________________");
			sender.sendMessage("---                         ---");
			sender.sendMessage("    " + ChatColor.GREEN + "Fundamentals of Elements");
			cmd(sender, "    Verze pluginu:", foe.getDescription().getVersion());
			cmd(sender, "    Verze online:", getOnlineVersion());
			sender.sendMessage("___                         ___");
			sender.sendMessage("    -----------------------   ");
			cmd(sender, "/foe", "Prikaz ktery zobrazi napovedu pro plugin FoE.");
			cmd(sender, "/clear", "Prikaz ktery vycisti chat.");
		} else if (cmd.getName().equalsIgnoreCase("foe") && args.length > 0) {
			sender.sendMessage("Prikaz foe nepodporuje vice argumentu!");
		}
		return false;
	}
	
	public void cmd(CommandSender sender, String cmd, String info) {
		sender.sendMessage(ChatColor.GREEN + cmd + ChatColor.WHITE + "  --  " + ChatColor.GOLD + info);
	}
	
	public String getOnlineVersion() {
		try {
			URL url = new URL("http://www.frelania.eu/MyImages/FoE.txt");
			URLConnection connection = url.openConnection();
			
			if (connection.getContentLength() != -1) {
				BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.frelania.eu/MyImages/FoE.txt").openStream()));
				String newVersion = br.readLine();
				br.close();
				return newVersion;
			}
		} catch (Exception e) {
			foe.getErrorMananger().log(Level.SEVERE, e);
		}
		return "Neni pripojeni k internetu";
	}
	
}
