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
			sender.sendMessage(ChatColor.GREEN + "    Verze pluginu:" + ChatColor.WHITE + "  --  " + ChatColor.GOLD + foe.getDescription().getVersion());
			sender.sendMessage(ChatColor.GREEN + "    Verze online:" + ChatColor.WHITE + "  --  " + ChatColor.GOLD + getOnlineVersion());
			sender.sendMessage("___                         ___");
			sender.sendMessage("    -----------------------   ");
			sender.sendMessage(ChatColor.GREEN + "/foe" + ChatColor.WHITE + "  --  " + ChatColor.GOLD + "Prikaz ktery zobrazi napovedu pro plugin FoE.");
			cmd(sender, "prikazy.chat.adminChat", "Prikaz ktery umozni komunikovat pouze s adminy.");
			cmd(sender, "prikazy.chat.vycistitChat", "Prikaz ktery vycisti chat.");
			cmd(sender, "prikazy.chat.vypnoutChat", "Prikaz ktery umozni vypnout chat hracum.");
			cmd(sender, "prikazy.chat.cenzura", "Prikaz pres ktery muzeme upravit cenzurni zasobu.");
			cmd(sender, "prikazy.chat.gramatika", "Prikaz pres ktery muzeme upravit gramatickou zasobu.");
			cmd(sender, "prikazy.manager.ban", "Prikaz ktery zablokuje pristup hraci na server.");
			cmd(sender, "prikazy.manager.unban", "Prikaz ktery povoli pristup hraci na serveru.");
			cmd(sender, "prikazy.manager.kick", "Prikaz ktery vyhodi hrace ze serveru.");
			cmd(sender, "prikazy.help", "Prikaz ktery umozni oslovit vsechny adminy soukromou zpravou.");
			cmd(sender, "prikazy.nahrano", "Prikaz ktery umozni zobrazit ktery hrac tu jak dlouho hraje.");
			cmd(sender, "prikazy.inventar", "Prikaz ktery zobrazi hracuv inventar.");
		} else if (cmd.getName().equalsIgnoreCase("foe") && args.length > 0) {
			sender.sendMessage("Prikaz foe nepodporuje vice argumentu!");
		}
		return false;
	}
	
	public void cmd(CommandSender sender, String cmd, String info) {
		sender.sendMessage(ChatColor.GREEN + foe.getConfigManager().config.getString(cmd) + ChatColor.WHITE + "  --  " + ChatColor.GOLD + info);
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
