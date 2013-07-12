package eu.frelania.foe.events;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import eu.frelania.foe.main.FoE;
import eu.frelania.foe.utils.Replace;

public class OnPlayerChat implements Listener {
	
	private FoE foe;

	public OnPlayerChat(FoE plugin) {
		foe = plugin;
	}
	
	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent event) {
		foe.logDebug("OnPlayerChat.PlayerChatEvent() - Player: " + event.getPlayer().getName() + ", event: " + event.getEventName());
		Player player = event.getPlayer();
		String message = event.getMessage();
		String playerName = player.getName();
		Server server = Bukkit.getServer();
		
		if(foe.getFeaturesManager().featureChatCensor) {
			for (String word : foe.getConfigManager().config.getStringList("chat.cenzura.slovnik")) {
				if (message.equalsIgnoreCase(word) || message.contains(word)) {
					message = message.replaceAll("(?i)" + word, Replace.playerName(foe.getConfigManager().config.getString("chat.cenzura.nahrada"), playerName));
					player.sendMessage(Replace.playerName(foe.getConfigManager().config.getString("chat.cenzura.zprava"), playerName));
					if (foe.getConfigManager().config.getString("chat.cenzura.provest").length() != 0)
						server.dispatchCommand(server.getConsoleSender(), Replace.playerName(foe.getConfigManager().config.getString("chat.cenzura.provest"), playerName));
				}
			}
		}
		
		if(foe.getFeaturesManager().featureChatAntiAds) {
			List<String> ips = foe.getConfigManager().config.getStringList("chat.antiReklama.ip.whitelist");
			List<String> links = foe.getConfigManager().config.getStringList("chat.antiReklama.link.whitelist");
			
			if(!player.hasPermission("FoE.Chat.AntiReklama") && !player.isOp()) {
				Pattern ipPattern = Pattern.compile("(\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{1,3})[\\s.,:!\\-](\\d{5})");
				Matcher ip = ipPattern.matcher(message);
				if(ip.find()) {
					if(!ips.contains(message)) {
						event.setCancelled(true);
						if (foe.getConfigManager().config.getString("chat.antiReklama.ip.provest").length() != 0)
							server.dispatchCommand(server.getConsoleSender(), Replace.playerName(foe.getConfigManager().config.getString("chat.antiReklama.ip.provest"), playerName));
						writeToLog(playerName + " - " + message);
						Bukkit.broadcastMessage((Replace.playerName(foe.getConfigManager().config.getString("chat.antiReklama.ip.zprava"), playerName)));
					}
				}
				Pattern linkPattern = Pattern.compile("(http://)|(https://)?(www)?\\S{2,}((\\.com)|(\\.net)|(\\.org)|(\\.co)|(\\.uk)|(\\.tk)|(\\.info)|(\\.es)|(\\.de)|(\\.arpa)|(\\.edu)|(\\.firm)|(\\.int)|(\\.mil)|(\\.mobi)|(\\.nato)|(\\.to)|(\\.fr)|(\\.ms)|(\\.vu)|(\\.eu)|(\\.nl)|(\\.us)|(\\.dk))|(\\.cz)|(\\.sk)|(\\.bis)");
				Matcher link = linkPattern.matcher(message);
				if(link.find()) {
					if(!links.contains(message)) {
						event.setCancelled(true);
						if (foe.getConfigManager().config.getString("chat.antiReklama.link.provest").length() != 0)
							server.dispatchCommand(server.getConsoleSender(), Replace.playerName(foe.getConfigManager().config.getString("chat.antiReklama.link.provest"), playerName));
						writeToLog(playerName + " - " + message);
						Bukkit.broadcastMessage((Replace.playerName(foe.getConfigManager().config.getString("chat.antiReklama.link.zprava"), playerName)));
					}
				}
			}
		}
		
		// TODO: Grammar
		// TODO: ChatCapsLock
		// TODO: AntiSpam
		// TODO: Disable Chat
	}
	
	public void writeToLog(String message) {
		try {
			File file = new File("plugins/FoE/antireklama.log");
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println(time + "\n" + message + "\n");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
