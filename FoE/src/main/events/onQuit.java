package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.GUIManager;
import main.MySQL;
import main.PlayerManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class onQuit implements Listener {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public PlayerManager	pm;
	public Replaces			replace;
	public GUIManager		gm;
	public MySQL			mysql;
	public Plugin			plugin;
	
	public onQuit(Plugin plugin) {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager(cm);
		mysql = new MySQL();
		pm = null;
		gm = null;
		replace = null;
		plugin = this.plugin;
	}
	
	public void delayedQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		try {
			if (fm.nahranostIsEnabled) {
				pm.unRegisterPlayer();
			}
			if (fm.guiIsEnabled) {
				for (Player PL : Bukkit.getOnlinePlayers()) {
					gm = new GUIManager(PL);
					gm.aktualizovatGUI();
				}
			}
			if (fm.teleportIsEnabled) {
				savePosition(player);
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public void savePosition(Player player) {
		try {
			Double X = player.getLocation().getX();
			Double Y = player.getLocation().getY();
			Double Z = player.getLocation().getZ();
			pm = new PlayerManager(player);
			pm.loadPlayer();
			pm.uziv.set("Svet", player.getLocation().getWorld().getName());
			pm.uziv.set("X", X);
			pm.uziv.set("Y", Y);
			pm.uziv.set("Z", Z);
			pm.saveUser();
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	@EventHandler
	public void Quit(final PlayerQuitEvent event) {
		try {
			Player player = event.getPlayer();
			pm = new PlayerManager(player);
			gm = new GUIManager(player);
			replace = new Replaces(player);
			if (fm.kdyzHracSeOdpojiIsEnabled)
				event.setQuitMessage(replace.PlayerName(cm.config.getString("KdyzHracSe.Odpoji.Zprava"), event.getPlayer().getName()));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					delayedQuit(event);
				}
			});
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
