package main.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {
	public Pattern					ipPattern			= Pattern.compile("(\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{5})");
	public Pattern					webpattern			= Pattern.compile("(http://)|(https://)?(www)?\\S{2,}((\\.com)|(\\.net)|(\\.org)|(\\.co)|(\\.uk)|(\\.tk)|(\\.info)|(\\.es)|(\\.de)|(\\.arpa)|(\\.edu)|(\\.firm)|(\\.int)|(\\.mil)|(\\.mobi)|(\\.nato)|(\\.to)|(\\.fr)|(\\.ms)|(\\.vu)|(\\.eu)|(\\.nl)|(\\.us)|(\\.dk))|(\\.cz)|(\\.sk)|(\\.bis)");
	public HashMap<String, Long>	messagePerSecond	= new HashMap<String, Long>();
	public HashMap<String, String>	lastMessage			= new HashMap<String, String>();
	public ErrorManager				err;
	public ConfigManager			cm;
	public FeaturesManager			fm;
	public Replaces					replace;
	
	public onChat() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager();
	}
	
	@EventHandler
	public void Chat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName(), message = event.getMessage();
		try {
			replace = new Replaces(player);
			Set<String> reasons = cm.config.getConfigurationSection("Gramatika.Duvody").getKeys(false);
			if (fm.antiReklamaIsEnabled) {
				List<String> allowedIPs = cm.config.getStringList("AntiReklama.IP.Whitelist");
				List<String> allowedWEBs = cm.config.getStringList("AntiReklama.WEB.Whitelist");
				if (!player.hasPermission("FoE.AntiReklama.Bypass") && !player.isOp()) {
					Matcher ip = ipPattern.matcher(message);
					while (ip.find()) {
						if (!allowedIPs.contains(message)) {
							event.setCancelled(true);
							zapsat(playerName + " - " + message);
							if (cm.config.getString("AntiReklama.IP.Akce").length() != 0)
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replace.PlayerName(cm.config.getString("AntiReklama.IP.Akce"), playerName));
							Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("AntiReklama.IP.Zprava"), playerName));
						}
					}
					Matcher url = webpattern.matcher(message);
					while (url.find()) {
						if (!allowedWEBs.contains(message)) {
							event.setCancelled(true);
							zapsat(playerName + " - " + message);
							if (cm.config.getString("AntiReklama.WEB.Akce").length() != 0)
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replace.PlayerName(cm.config.getString("AntiReklama.WEB.Akce"), playerName));
							Bukkit.broadcastMessage(replace.PlayerName(cm.config.getString("AntiReklama.WEB.Zprava"), playerName));
						}
					}
				}
			}
			if ((fm.vypnoutChatIsEnabled) && (!fm.Chat) && (!event.getPlayer().isOp()) && (!event.getPlayer().hasPermission("FoE.VypnoutChat.Psat"))) {
				event.getPlayer().sendMessage(replace.PlayerName(cm.config.getString("VypnoutChat.KdyzJeVypnutyChat"), playerName));
				event.setCancelled(true);
			}
			
			if (fm.cenzuraIsEnabled) {
				for (String l : cm.config.getStringList("Cenzura.Slova")) {
					if (message.equalsIgnoreCase(l) || message.contains(l)) {
						message = message.replaceAll("(?i)" + l, replace.PlayerName(cm.config.getString("Cenzura.Nahrada"), playerName));
						player.sendMessage(replace.PlayerName(cm.config.getString("Cenzura.Zprava"), playerName));
						if (!cm.config.getString("Cenzura.Akce").isEmpty()) {
							if (cm.config.getString("Cenzura.Akce").length() != 0)
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replace.PlayerName(cm.config.getString("Cenzura.Akce"), playerName));
						}
					}
				}
			}
			if (fm.gramatikaIsEnabled) {
				for (String l : cm.config.getStringList("Gramatika.Vsude")) {
					String[] s = l.split(",");
					if (message.contains(s[0])) {
						message = message.replaceAll(s[0], s[1]);
						if (reasons.contains(s[0])) {
							player.sendMessage(replace.PlayerName(cm.config.getString(new StringBuilder("Gramatika.Duvody.").append(s[0]).toString()), playerName));
						}
					}
				}
				
				for (String l : cm.config.getStringList("Gramatika.Cele")) {
					String[] s = l.split(",");
					if ((message.equalsIgnoreCase(s[0] + " ")) || (message.equalsIgnoreCase(" " + s[0]) || message.startsWith(s[0]))) {
						if (message.startsWith(s[0]))
							message = message.replace(s[0] + " ", s[1] + " ");
						else {
							message = message.replace(" " + s[0] + " ", " " + s[1] + " ");
						}
						if (reasons.contains(s[0])) {
							player.sendMessage(replace.PlayerName(cm.config.getString(new StringBuilder("Gramatika.Duvody.").append(s[0]).toString()), playerName));
						}
					}
				}
			}
			if (fm.antiSpamIsEnabled) {
				if (!player.hasPermission("FoE.AntiSpam.Psat") && !player.isOp()) {
					if (messagePerSecond.containsKey(playerName)) {
						long cas = System.currentTimeMillis() - messagePerSecond.get(playerName);
						if (!((cas / 1000) > fm.AntiSpamCas)) {
							event.setCancelled(true);
							player.sendMessage(replace.PlayerName(nahraditMessagePerSecond(cm.config.getString("AntiSpam.Zprava")), playerName));
						} else {
							messagePerSecond.put(playerName, System.currentTimeMillis());
						}
					} else {
						messagePerSecond.put(playerName, System.currentTimeMillis());
					}
				}
			}
			if (fm.antiSpamDuplikaceIsEnabled) {
				if (lastMessage.containsKey(playerName)) {
					if (lastMessage.get(playerName) == message) {
						player.sendMessage(replace.Colors(cm.config.getString("AntiSpam.Duplikace.Zprava")));
						event.setCancelled(true);
					} else {
						lastMessage.remove(playerName);
					}
				}
				lastMessage.put(playerName, message);
			}
			if (fm.capsLockIsEnabled) {
				if (message.equals(message.toUpperCase()) && message.matches("[A-Z]")) {
					player.sendMessage(replace.Colors(cm.config.getString("capsLock.Zprava")));
					if (cm.config.getString("capsLock.Akce").length() != 0)
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replace.PlayerName(cm.config.getString("capsLock.Akce"), playerName));
					message = message.toLowerCase();
				}
				// zadost #004.
			}
			event.setMessage(message);
			
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public String nahraditMessagePerSecond(String zprava) {
		if (zprava.matches(".*\\{SEKUND}.*")) {
			zprava = zprava.replaceAll("\\{SEKUND}", String.valueOf(fm.AntiSpamCas));
		}
		return zprava;
	}
	
	public boolean checkTimeSendedMessage(String playerName) {
		long cas = System.currentTimeMillis() - messagePerSecond.get(playerName);
		if ((cas % 1000) > fm.AntiSpamCas) {
			return true;
		} else {
			return false;
		}
	}
	
	public void zapsat(String coZapsat) {
		try {
			File u = new File("plugins/FoE/antireklama.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println(time + "\n" + coZapsat + "\n");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
