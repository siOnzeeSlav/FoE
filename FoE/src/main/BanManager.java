package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BanManager {
	
	public File					uzivFile;
	public YamlConfiguration	uziv;
	public File					configFile	= new File("plugins/FoE/config.yml");
	public YamlConfiguration	config		= YamlConfiguration.loadConfiguration(configFile);
	public FoE					plugin;
	public MySQL				mysql		= new MySQL(plugin);
	
	public boolean isBanned(String playerName) {
		uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
		uziv = YamlConfiguration.loadConfiguration(uzivFile);
		if (uziv.getBoolean("isBanned"))
			return true;
		return false;
	}
	
	public void kickPlayer(String sender, String playerName, String reason) {
		Player pl = Bukkit.getPlayer(playerName);
		if (pl != null) {
			Bukkit.broadcastMessage(replaceNicknamesInBan(config.getString("Manager.Kick.Zprava"), sender, playerName, reason));
			pl.kickPlayer(reason);
			if (mysql.isMySQLAllowed())
				MySQL_Manager(sender, playerName, reason, "KICK");
		} else {
			Player s = Bukkit.getPlayer(sender);
			s.sendMessage(playerName + " je již zabanován!");
		}
	}
	
	public void banPlayer(String sender, String playerName, String reason) {
		if (!isBanned(playerName)) {
			Player pl = Bukkit.getPlayer(playerName);
			uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
			uziv = YamlConfiguration.loadConfiguration(uzivFile);
			uziv.set("isBanned", true);
			uziv.set("banReason", reason);
			saveConfig(uziv, uzivFile);
			if (pl != null) {
				pl.kickPlayer(reason);
			}
			if (mysql.isMySQLAllowed())
				MySQL_Manager(sender, playerName, reason, "BAN");
			Bukkit.broadcastMessage(replaceNicknamesInBan(config.getString("Manager.Ban.Zprava"), sender, playerName, reason));
		} else {
			Player p = Bukkit.getPlayer(sender);
			p.sendMessage(playerName + " je již zabanován!");
		}
	}
	
	public void unbanPlayer(String sender, String playerName, String reason) {
		if (isBanned(playerName)) {
			uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
			uziv = YamlConfiguration.loadConfiguration(uzivFile);
			uziv.set("isBanned", false);
			Bukkit.broadcastMessage(replaceNicknamesInBan(config.getString("Manager.Unban.Zprava"), sender, playerName, reason));
			if (mysql.isMySQLAllowed())
				MySQL_Manager(sender, playerName, reason, "UNBAN");
			saveConfig(uziv, uzivFile);
		} else {
			Player p = Bukkit.getPlayer(sender);
			p.sendMessage(playerName + " nemá ban!");
		}
	}
	
	public void MySQL_Manager(String playerName, String targetName, String reason, String typ) {
		try {
			if (playerName != null && targetName != null && reason != null && typ != null)
				mysql.query("INSERT INTO `FoE_Banlist` (hrac, admin, duvod, datum, typ) VALUES (" + "'" + targetName + "'," + " '" + playerName + "'," + " '" + reason + "'," + " '" + System.currentTimeMillis() + "'," + " '" + typ + "')");
			else
				System.out.println("Nekde je chyba, null: " + playerName + "|" + targetName + "|" + reason + "|" + typ);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void saveConfig(YamlConfiguration config, File configFile) {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Error(String message) {
		try {
			File u = new File("plugins/FoE/errors.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("================== " + time + " - FoE: " + plugin.getDescription().getVersion() + "\n" + "CB: " + Bukkit.getVersion() + "\n" + message + "\n==================\n");
			pw.flush();
			pw.close();
			System.out.println("[FoE] ERROR!");
			System.out.println("===========================");
			System.out.println("Prekopirujte obsah souboru errors.log do prispevku.");
			System.out.println("===========================");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public String replaceNicknamesInBan(String message, String playerName, String targetName, String reason) {
		if (message != null) {
			if (message.matches(".*\\{JMENO}.*")) {
				message = message.replaceAll("\\{JMENO}", playerName);
			}
			if (message.matches(".*\\{TARGET}.*")) {
				message = message.replaceAll("\\{TARGET}", targetName);
			}
			if (message.matches(".*\\{DUVOD}.*")) {
				message = message.replaceAll("\\{DUVOD}", reason);
			}
			message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
			return message;
		} else {
			return "Messsage = null";
		}
	}
	
}
