package eu.frelania.foe.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.frelania.foe.cmds.cmdClear;
import eu.frelania.foe.cmds.cmdFoe;

public class FoE extends JavaPlugin {

	private final List<String> jokes = new ArrayList<String>();
	private ConfigManager configManager;
	private FeaturesManager featuresManager;
	private ErrorManager errorManager;
	public boolean debugMode = false;
	public HashMap<String, PlayerManager> joinedUsers;
	
	public boolean modeChatOff = false;

	@Override
	public void onEnable() {
		joinedUsers = new HashMap<String, PlayerManager>();
		errorManager = new ErrorManager(this);
		configManager = new ConfigManager(this);
		configManager.setVersion(getDescription().getVersion());
		featuresManager = new FeaturesManager(this, jokes);

		for (Player player : getServer().getOnlinePlayers()) {
			PlayerManager pm = new PlayerManager(this, player);
			pm.loadPlayer();
			joinedUsers.put(player.getName(), pm);
		}
		loadCommands();
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

	public void loadCommands() {
		Bukkit.getPluginCommand("foe").setExecutor(new cmdFoe(this));
		logDebug("Prikaz FoE byl nacten.");
		
		Bukkit.getPluginCommand("cmdclear").setExecutor(new cmdClear());
		logDebug("Prikaz Clear byl nacten.");
	}
}
