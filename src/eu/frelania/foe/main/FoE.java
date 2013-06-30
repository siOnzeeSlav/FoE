package eu.frelania.foe.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FoE extends JavaPlugin {

	private List<String> jokes = new ArrayList<String>();
	private ConfigManager configManager;
	private FeaturesManager featuresManager;
	private ErrorManager errorManager;
	public boolean debugMode = false;
	public HashMap<String, PlayerManager> joinedUsers;

	@Override
	public void onEnable() {
		joinedUsers = new HashMap<String, PlayerManager>();
		errorManager = new ErrorManager(this);
		configManager = new ConfigManager(this);
		configManager.setVersion(getDescription().getVersion());
		featuresManager = new FeaturesManager(this, jokes);
		
		for(Player player : getServer().getOnlinePlayers()){
			PlayerManager pm = new PlayerManager(this, player);
			pm.loadPlayer();
			joinedUsers.put(player.getName(), pm);
		}
	}

	@Override
	public void onDisable() {
		for (PlayerManager player : joinedUsers.values()) {
			player.unloadPlayer();
		}
		Bukkit.getScheduler().cancelAllTasks();
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public FeaturesManager getFeaturesManager() {
		return featuresManager;
	}

	public ErrorManager getErrorMananger() {
		return errorManager;
	}

	public void logDebug(String msg) {
		if (debugMode) getLogger().log(Level.FINE, msg);
	}
}
