package eu.frelania.foe.main;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerManager {

	private FoE foe;
	private File userFile;
	public YamlConfiguration userConfig;
	public Player player;
	public String playerName;
	private long joinedTime;

	public PlayerManager(FoE plugin, Player player) {
		foe = plugin;
		this.player = player;
		playerName = player.getName();
	}

	public PlayerManager(FoE plugin, String playerName) {
		foe = plugin;
		player = Bukkit.getPlayer(playerName);
		this.playerName = playerName;
	}

	public void loadPlayer() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.loadPlayer() - Player: " + player.getName());
		joinedTime = System.currentTimeMillis() / 1000;

		userFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
		userConfig = YamlConfiguration.loadConfiguration(userFile);

		if (userConfig.contains("login.ip")) userConfig.set("login.predchoziIp", userConfig.get("login.ip"));
		userConfig.set("login.ip", player.getAddress().toString());
		if (!userConfig.contains("ban.zabanovan")) userConfig.set("ban.zabanovan", false);
		if (!userConfig.contains("ban.duvod")) userConfig.set("ban.duvod", "");
		if (!userConfig.contains("stats.nahrano")) userConfig.set("stats.nahrano", 0);
		if (!userConfig.contains("stats.smrti")) userConfig.set("stats.smrti", 0);
		if (!userConfig.contains("stats.zabito.hracu")) userConfig.set("stats.zabito.hracu", 0);
		if (!userConfig.contains("stats.zabito.monster")) userConfig.set("stats.zabito.monster", 0);
		if (!userConfig.contains("stats.zabito.zvirat")) userConfig.set("stats.zabito.zvirat", 0);

		saveUser();
	}

	public void unloadPlayer() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.unloadPlayer() - Player: " + player.getName());
		saveNewPlayedTime();

		saveUser();
	}

	public void saveUser() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.savePlayer() - Player: " + player.getName());
		if (userFile.exists() && (!userFile.canRead() || !userFile.canWrite())) {
			foe.getErrorMananger().log(Level.SEVERE, "Neco je spatne se souborem \"plugins/FoE/uzivatele/" + playerName + ".yml\"! - Nelze nacist nebo zapisovat\n @PlayerManager:53");
		} else {
			try {
				userConfig.save(userFile);
			} catch (Exception e) {
				foe.getErrorMananger().log(Level.SEVERE, e);
			}
		}
	}

	private void saveNewPlayedTime() {
		long nowTime = System.currentTimeMillis() / 1000;
		if (joinedTime < nowTime) {
			if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.saveNewPlayedTime() - Player: " + player.getName() + ", playedTime: " + userConfig.getInt("stats.nahrano") + ", now: " + nowTime);
			long total = (System.currentTimeMillis() / 1000 - joinedTime) + userConfig.getInt("stats.nahrano");
			joinedTime = System.currentTimeMillis();
			userConfig.set("stats.nahrano", total);
		}
	}

	public void hasDied() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.hasDied() - Player: " + player.getName() + ", nove skore: " + userConfig.getInt("stats.smrti") + 1);
		userConfig.set("stats.smrti", userConfig.getInt("stats.smrti") + 1);
	}

	public void hasKilledPlayer() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.hasKilledPlayer() - Player: " + player.getName() + ", nove skore: " + userConfig.getInt("stats.zabito.hracu") + 1);
		userConfig.set("stats.zabito.hracu", userConfig.getInt("stats.zabito.hracu") + 1);
	}

	public void hasKilledMonster() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.hasKilledMonster() - Player: " + player.getName() + ", nove skore: " + userConfig.getInt("stats.zabito.monster") + 1);
		userConfig.set("stats.zabito.monster", userConfig.getInt("stats.zabito.monster") + 1);
	}

	public void hasKilledAnimal() {
		if (foe.debugMode) foe.getLogger().log(Level.FINE, "PlayerManager.hasKilledAnimal() - Player: " + player.getName() + ", nove skore: " + userConfig.getInt("stats.zabito.zvirat") + 1);
		userConfig.set("stats.zabito.zvirat", userConfig.getInt("stats.zabito.zvirat") + 1);
	}

	public int getPlayedTime() {
		saveNewPlayedTime();

		return userConfig.getInt("stats.nahrano");
	}

}
