package main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BanManager {
	
	public File					uzivFile;
	public YamlConfiguration	uziv;
	public ConfigManager		cm;
	public MySQL				mysql;
	public ErrorManager			err;
	public FeaturesManager		fm;
	
	public BanManager() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager();
		if (fm.mysqlIsEnabled)
			mysql = new MySQL();
	}
	
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
			Bukkit.broadcastMessage(replaceNicknamesInBan(cm.config.getString("Manager.Kick.Zprava"), sender, playerName, reason));
			pl.kickPlayer(reason);
			if (fm.mysqlIsEnabled)
				MySQL_Manager(sender, playerName, reason, "KICK");
		} else {
			Player s = Bukkit.getPlayer(sender);
			s.sendMessage(playerName + " je jiz zabanovan!");
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
			if (fm.mysqlIsEnabled)
				MySQL_Manager(sender, playerName, reason, "BAN");
			Bukkit.broadcastMessage(replaceNicknamesInBan(cm.config.getString("Manager.Ban.Zprava"), sender, playerName, reason));
		} else {
			Player p = Bukkit.getPlayer(sender);
			p.sendMessage(playerName + " je jiz zabanovan!");
		}
	}
	
	public void unbanPlayer(String sender, String playerName, String reason) {
		if (isBanned(playerName)) {
			uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
			uziv = YamlConfiguration.loadConfiguration(uzivFile);
			uziv.set("isBanned", false);
			Bukkit.broadcastMessage(replaceNicknamesInBan(cm.config.getString("Manager.Unban.Zprava"), sender, playerName, reason));
			if (fm.mysqlIsEnabled)
				MySQL_Manager(sender, playerName, reason, "UNBAN");
			saveConfig(uziv, uzivFile);
		} else {
			Player p = Bukkit.getPlayer(sender);
			p.sendMessage(playerName + " nema ban!");
		}
	}
	
	public void MySQL_Manager(String playerName, String targetName, String reason, String typ) {
		try {
			if (playerName != null && targetName != null && reason != null && typ != null)
				mysql.query("INSERT INTO `FoE_Banlist` (hrac, admin, duvod, datum, typ) VALUES (" + "'" + targetName + "'," + " '" + playerName + "'," + " '" + reason + "'," + " '" + System.currentTimeMillis() + "'," + " '" + typ + "')");
			else
				System.out.println("Nekde je chyba, null: " + playerName + "|" + targetName + "|" + reason + "|" + typ);
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public void saveConfig(YamlConfiguration config, File configFile) {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
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
