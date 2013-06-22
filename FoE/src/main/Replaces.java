package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Replaces {
	
	public ConfigManager		cm	= new ConfigManager();
	public PlayerManager		pm	= null;
	public GUIManager			gm	= null;
	public ErrorManager			err	= new ErrorManager();
	public LinkedList<String>	jokes;
	
	public Replaces() {
		
	}
	
	public Replaces(Player player) {
		pm = new PlayerManager(player);
		gm = new GUIManager(player);
	}
	
	public Replaces(String playerName) {
		pm = new PlayerManager(Bukkit.getPlayer(playerName));
		gm = new GUIManager(Bukkit.getPlayer(playerName));
	}
	
	public Replaces(Player player, List<String> jokes) {
		pm = new PlayerManager(player);
		gm = new GUIManager(player);
		jokes = this.jokes;
	}
	
	public Replaces(List<String> jokes) {
		jokes = this.jokes;
	}
	
	public String Joke(String message) {
		if (message.matches(".*\\{VTIP}.*")) {
			Random rnd = new Random();
			message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
			return message = message.replaceAll("\\{VTIP}", jokes.get(rnd.nextInt(jokes.size())));
		}
		return "ERROR(FORMAT:VTIP)";
	}
	
	public String Time(String message, String playerName) {
		if (message != null && playerName != null && pm.playedTime.containsKey(playerName)) {
			long[] cas = gm.getCorrectFormat(System.currentTimeMillis() - pm.playedTime.get(playerName) + pm.uziv.getLong("Nahrano"));
			String s = String.valueOf(cas[0]), m = String.valueOf(cas[1]), h = String.valueOf(cas[2]), d = String.valueOf(cas[3]), t = String.valueOf(cas[4]);
			if (message.matches(".*\\{TYDEN}.*")) {
				message = message.replaceAll("\\{TYDEN}", t);
			}
			if (message.matches(".*\\{DEN}.*")) {
				message = message.replaceAll("\\{DEN}", d);
			}
			if (message.matches(".*\\{HODIN}.*")) {
				message = message.replaceAll("\\{HODIN}", h);
			}
			if (message.matches(".*\\{MINUT}.*")) {
				message = message.replaceAll("\\{MINUT}", m);
			}
			if (message.matches(".*\\{SEKUND}.*")) {
				message = message.replaceAll("\\{SEKUND}", s);
			}
		} else {
			message = null;
		}
		return message;
	}
	
	public String WelcomeMessage(String message, String playerName, String worldName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		
		if (message.matches(".*\\{SVET}.*"))
			message = message.replaceAll("\\{SVET}", worldName);
		
		if (message.matches(".*\\{CAS}.*")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(date);
			message = message.replaceAll("\\{CAS}", time);
		}
		
		if (message.matches(".*\\[RADEK].*"))
			message = message.replaceAll("\\[RADEK]", "\n");
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String PlayerName(String message, String playerName) {
		try {
			pm.loadPlayer();
			if (message.matches(".*\\{JMENO}.*")) {
				message = message.replaceAll("\\{JMENO}", playerName);
			}
			message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		} catch (Exception e) {
			err.postError(e);
		}
		return message;
	}
	
	public String AutoMessage(String message, String playerName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		
		if (message.matches(".*\\{PREFIX}.*"))
			message = message.replaceAll("\\{PREFIX}", cm.config.getString("autoZpravy.Prefix"));
		
		if (message.matches(".*\\{CAS}.*")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(date);
			message = message.replaceAll("\\{CAS}", time);
		}
		
		if (message.matches(".*\\{SVET}.*"))
			message = message.replaceAll("\\{SVET}", Bukkit.getPlayer(playerName).getLocation().getWorld().getName());
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String Spaces(String zCeho) {
		return zCeho = zCeho.replaceAll(" ", "_");
	}
	
	public String Colors(String message) {
		return message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
	}
	
}
