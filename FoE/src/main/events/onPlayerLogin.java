package main.events;

import java.util.Random;

import main.BanManager;
import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.PlayerManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class onPlayerLogin implements Listener {
	public ConfigManager	cm;
	public ErrorManager		err;
	public BanManager		bm;
	public FeaturesManager	fm;
	public PlayerManager	pm;
	public Replaces			replace;
	
	public onPlayerLogin() {
		cm = new ConfigManager();
		err = new ErrorManager();
		bm = new BanManager();
		fm = new FeaturesManager();
	}
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onLogin(PlayerLoginEvent event) {
		String playerName = event.getPlayer().getName();
		try {
			replace = new Replaces(event.getPlayer());
			pm = new PlayerManager(playerName);
			if (fm.whiteListIsEnabled) {
				if (event.getResult() == Result.KICK_WHITELIST) {
					event.disallow(Result.KICK_WHITELIST, replace.Colors(cm.config.getString("whiteList.Zprava")));
				}
			}
			pm.loadPlayer();
			if (fm.managerBan) {
				if (bm.isBanned(playerName)) {
					event.disallow(Result.KICK_BANNED, replace.Colors(pm.getBanReason()));
				}
			}
			
			if (fm.rezervaceIsEnabled) {
				if (Bukkit.getOnlinePlayers().length == Bukkit.getServer().getMaxPlayers()) {
					Player randomPlayer = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
					while (!randomPlayer.hasPermission("FoE.Rezervace.VIP")) {
						randomPlayer.kickPlayer(replace.PlayerName(cm.config.getString("Rezervace.Zprava"), playerName));
						break;
					}
				}
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
