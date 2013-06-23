package main.events;

import java.sql.ResultSet;

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
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.Plugin;

public class onKick implements Listener {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public PlayerManager	pm;
	public Replaces			replace;
	public GUIManager		gm;
	public MySQL			mysql;
	public Plugin			plugin;
	
	public onKick(Plugin plugin) {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager(cm);
		mysql = new MySQL();
		pm = null;
		gm = null;
		replace = null;
		plugin = this.plugin;
	}
	
	public void delayedKick(PlayerKickEvent event) {
		Player player = event.getPlayer();
		pm = new PlayerManager(player);
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
	
	public void MySQL_Nahranost(String playerName) {
		try {
			PlayerManager pm = new PlayerManager(Bukkit.getPlayer(playerName));
			pm.loadPlayer();
			Long nahranost = (System.currentTimeMillis() - pm.playedTime.get(playerName)) + pm.getPlayerPlayedTime();
			ResultSet rs = mysql.query("SELECT `player` FROM `FoE_Uzivatele` WHERE `player` = '" + playerName + "'");
			if (rs.next()) {
				mysql.query("UPDATE `FoE_Uzivatele` SET `nahranost` = '" + nahranost + "' WHERE `player` = '" + playerName + "'");
			} else {
				mysql.query("INSERT INTO `FoE_Uzivatele` (player,nahranost) VALUES ('" + playerName + "', '" + nahranost + "')");
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	@EventHandler
	public void Kick(final PlayerKickEvent event) {
		try {
			if (fm.kdyzHracSeVyhodiIsEnabled)
				replace = new Replaces(event.getPlayer());
			event.setLeaveMessage(replace.PlayerName(cm.config.getString("KdyzHracSe.Vyhodi.Zprava"), event.getPlayer().getName()));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					delayedKick(event);
				}
			});
		} catch (Exception e) {
			err.postError(e);
		}
		
	}
}
