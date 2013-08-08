package eu.frelania.foe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import eu.frelania.foe.main.PlayerManager;

public class Replace {

	public static String playerName(String message, String playerName) {
		if (message.matches(".*\\{JMENO}.*")) message = message.replaceAll("\\{JMENO}", playerName);

		return Replace.format(message);
	}
	
	public static String format(String message){
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}

	public static String welcomeMessage(String message, PlayerManager player, String worldName) {
		if (message.matches(".*\\{JMENO}.*")) message = message.replaceAll("\\{JMENO}", player.playerName);
		if (message.matches(".*\\{SVET}.*")) message = message.replaceAll("\\{SVET}", worldName);
		if (message.matches(".*\\{NAHRANO}.*")) message = message.replaceAll("\\{NAHRANO}", formatTime(player.getPlayedTime()));
		if (message.matches(".*\\{CAS}.*")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(date);
			message = message.replaceAll("\\{CAS}", time);
		}

		return Replace.format(message);
	}

	public static String formatTime(int par) {

		int hours = par / 3600;
		int remainder = par % 3600;
		int minutes = remainder / 60;
		int seconds = remainder % 60;

		return ((hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
	}
	public static String killedMsg(String message, String player, String killer, String item) {
		if (message.matches(".*\\{JMENO}.*")) message = message.replaceAll("\\{JMENO}", player);
		if (message.matches(".*\\{ZABIJAK}.*")) message = message.replaceAll("\\{ZABIJAK}", killer);
		if (message.matches(".*\\{ITEM}.*")) message = message.replaceAll("\\{ITEM}", item);

		return Replace.format(message);
	}
}
