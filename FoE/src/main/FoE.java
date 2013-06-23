package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FoE extends JavaPlugin {
	
	public MySQL			mysql;
	public List<String>		jokes;
	public String			version;
	public ConfigManager	cm;
	public BanManager		bm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public PlayerManager	pm;
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
		pm = null;
		bm = new BanManager();
		jokes = new ArrayList<String>();
		fm = new FeaturesManager(cm, plugin, mysql, jokes, version);
		debug = fm.debug;
		replace = new Replaces(jokes);
		if (cm.loaded) {
			fm.loadValues();
		}
	}
	
	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			pm = new PlayerManager(p);
			pm.unRegisterPlayer();
		}
		Bukkit.getScheduler().cancelAllTasks();
	}
	
}
