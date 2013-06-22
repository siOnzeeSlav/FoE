package main;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class FoE extends JavaPlugin {
	
	public MySQL			mysql;
	public List<String>		jokes;
	public String			version;
	public ConfigManager	cm;
	public BanManager		bm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public FoE				plugin;
	public Replaces			replace;
	public boolean			debug;
	
	@Override
	public void onEnable() {
		version = this.getDescription().getVersion();
		cm = new ConfigManager();
		cm.checkConfig();
		cm.version(version);
		err = new ErrorManager();
		bm = new BanManager();
		jokes = new ArrayList<String>();
		fm = new FeaturesManager(cm, plugin, mysql, jokes, version);
		debug = fm.debug;
		replace = new Replaces(jokes);
		if (cm.loaded) {
			fm.loadValues();
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
	
	/*public void ulozitPozici(Player player) {
		try {
			String playerName = player.getName();
			Double X = player.getLocation().getX();
			Double Y = player.getLocation().getY();
			Double Z = player.getLocation().getZ();
			pm.loadPlayer();
			uziv.set("Svet", player.getLocation().getWorld().getName());
			uziv.set("X", X);
			uziv.set("Y", Y);
			uziv.set("Z", Z);
			pm.saveUser();
		} catch (Exception e) {
			err.postError(e);
		}
	}*/
	
	/*public void odRegistrovatplayere(String playerName) {
		try {
			PlayerManager pm = new PlayerManager(playerName);
			if (pm.playedTime.containsKey(playerName)) {
				pm.loadPlayer();
				long casPripojeni = pm.playedTime.get(playerName), vConfigu = pm.getPlayerPlayedTime(), vysledek = System.currentTimeMillis() - casPripojeni + vConfigu;
				uziv.set("Nahrano", vysledek);
				pm.saveUser();
				if (fm.mysqlPovolit)
					MySQL_Nahranost(playerName);
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}*/
	
	/*@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			odRegistrovatplayere(p.getName());
		}
		Bukkit.getScheduler().cancelAllTasks();
	}*/
	
	/*public void checkUser(String playerName) {
		try {
			PlayerManager pm = new PlayerManager(playerName);
			pm.loadPlayer();
			
			if (!uziv.contains("Nahranost"))
				uziv.set("Nahrano", 0);
			
			if (!uziv.contains("isBanned"))
				uziv.set("isBanned", false);
			
			if (!uziv.contains("Zabitoplayeru"))
				uziv.set("Zabitoplayeru", 0);
			
			if (!uziv.contains("ZabitoMobu"))
				uziv.set("ZabitoMobu", 0);
			
			if (!uziv.contains("ZabitoZvirat"))
				uziv.set("ZabitoZvirat", 0);
			
			if (!uziv.contains("PocetSmrti"))
				uziv.set("PocetSmrti", 0);
			
			pm.saveUser();
		} catch (Exception e) {
			err.postError(e);
		}
	}*/
	
}
