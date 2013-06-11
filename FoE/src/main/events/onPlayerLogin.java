package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Random;

import main.BanManager;
import main.ConfigManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class onPlayerLogin implements Listener {
	public FoE				p;
	public BanManager		bm	= new BanManager();
	public ConfigManager	cm	= new ConfigManager();
	
	public onPlayerLogin(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onLogin(PlayerLoginEvent event) {
		String playerName = event.getPlayer().getName();
		try {
			if (p.whiteListPovolit) {
				if (event.getResult() == Result.KICK_WHITELIST) {
					event.disallow(Result.KICK_WHITELIST, p.nahraditBarvy(cm.config.getString("whiteList.Zprava")));
				}
			}
			p.uzivatel(playerName);
			if (p.managerBan) {
				if (bm.isBanned(playerName)) {
					event.disallow(Result.KICK_BANNED, p.nahraditBarvy(p.uziv.getString("banReason")));
				}
			}
			
			if (p.rezervacePovolit) {
				if (Bukkit.getOnlinePlayers().length == Bukkit.getServer().getMaxPlayers()) {
					Player randomPlayer = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
					while (!randomPlayer.hasPermission("FoE.Rezervace.VIP")) {
						randomPlayer.kickPlayer(p.nahradit(cm.config.getString("Rezervace.Zprava"), playerName));
						break;
					}
				}
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
	}
}
