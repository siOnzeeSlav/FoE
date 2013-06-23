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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class onJoin implements Listener {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public GUIManager		gm;
	public PlayerManager	pm;
	public Replaces			replace;
	public Plugin			plugin;
	public MySQL			mysql;
	
	public onJoin(Plugin plugin) {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager(cm);
		gm = null;
		pm = null;
		replace = null;
		plugin = this.plugin;
		mysql = new MySQL();
	}
	
	public onJoin() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager(cm);
		gm = null;
		pm = null;
		replace = null;
	}
	
	public void delayedJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		try {
			checkUser(playerName);
			if (fm.nahranostIsEnabled) {
				pm.registerPlayer();
				if (fm.nahranostPrivitaciZpravaIsEnabled) {
					player.sendMessage(replace.PlayerName(replace.Time(cm.config.getString("Nahranost.Zprava"), playerName), playerName));
				}
			}
			if (playerName.equalsIgnoreCase("sionzee")) {
				player.sendMessage("Tento server pouziva FoE v" + ChatColor.GOLD + plugin.getDescription().getVersion());
			}
			if (fm.guiIsEnabled) {
				for (Player PL : Bukkit.getOnlinePlayers()) {
					gm = new GUIManager(PL);
					gm.aktualizovatGUI();
				}
			}
			pm = new PlayerManager(player);
			pm.loadPlayer();
			if (!pm.uziv.contains("IP"))
				pm.uziv.set("IP", player.getAddress().getHostName());
			pm.uziv.set("lastIP", player.getAddress().getHostName());
			pm.saveUser();
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	@EventHandler
	public void Join(final PlayerJoinEvent event) {
		try {
			Player player = event.getPlayer();
			String playerName = player.getName();
			String worldName = player.getLocation().getWorld().getName();
			pm = new PlayerManager(player);
			gm = new GUIManager(player);
			replace = new Replaces(player);
			if (fm.kdyzHracSePripojiIsEnabled) {
				event.setJoinMessage(replace.PlayerName(cm.config.getString("KdyzHracSe.Pripoji.Zprava"), event.getPlayer().getName()));
			}
			if (fm.uvitaciZpravaIsEnabled)
				event.getPlayer().sendMessage(replace.WelcomeMessage(cm.config.getString("uvitaciZprava.Zprava"), playerName, worldName));
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					delayedJoin(event);
				}
			});
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
	
	public void checkUser(String playerName) {
		try {
			PlayerManager pm = new PlayerManager(playerName);
			pm.loadPlayer();
			
			if (!pm.uziv.contains("Nahranost"))
				pm.uziv.set("Nahrano", 0);
			
			if (!pm.uziv.contains("isBanned"))
				pm.uziv.set("isBanned", false);
			
			if (!pm.uziv.contains("Zabitoplayeru"))
				pm.uziv.set("Zabitoplayeru", 0);
			
			if (!pm.uziv.contains("ZabitoMobu"))
				pm.uziv.set("ZabitoMobu", 0);
			
			if (!pm.uziv.contains("ZabitoZvirat"))
				pm.uziv.set("ZabitoZvirat", 0);
			
			if (!pm.uziv.contains("PocetSmrti"))
				pm.uziv.set("PocetSmrti", 0);
			
			pm.saveUser();
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
