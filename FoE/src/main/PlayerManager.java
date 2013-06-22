package main;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerManager {
	
	public File						uzivFile	= null;
	public YamlConfiguration		uziv		= null;
	public HashMap<String, Long>	playedTime	= new HashMap<String, Long>();
	public Player					player		= null;
	public String					playerName	= null;
	public ErrorManager				err;
	
	public PlayerManager(Player player) {
		player = this.player;
		playerName = player.getName();
		err = new ErrorManager();
	}
	
	public PlayerManager(String playerName) {
		player = Bukkit.getPlayer(playerName);
		playerName = player.getName();
		err = new ErrorManager();
	}
	
	public PlayerManager() {
		err = new ErrorManager();
	}
	
	public void loadPlayer() {
		uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
		uziv = YamlConfiguration.loadConfiguration(uzivFile);
	}
	
	public void saveUser() {
		try {
			uziv.save(uzivFile);
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public Long getPlayerPlayedTime() {
		return uziv.getLong("Nahrano");
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isPlayerRegistered() {
		return playedTime.containsKey(playerName);
	}
	
	public String getBanReason() {
		return uziv.getString("banReason");
	}
	
	public Boolean isBanned() {
		return uziv.getBoolean("isBanned");
	}
	
	public int getTotalKilledsMobs() {
		return uziv.getInt("ZabitoMobu");
	}
	
	public int getTotalKilledsPlayers() {
		return uziv.getInt("ZabitoHracu");
	}
	
	public int getTotalKilledsAnimals() {
		return uziv.getInt("ZabitoZvirat");
	}
	
	public int getTotalDeaths() {
		return uziv.getInt("PocetSmrti");
	}
	
	public boolean hasPermission(Permissions permission) {
		if (player.hasPermission(convertPermission(permission.toString())))
			return true;
		return false;
	}
	
	public void kickPlayer(String sender, String whom, String reason) {
		BanManager bm = new BanManager();
		bm.kickPlayer(sender, whom, reason);
	}
	
	public void banPlayer(String sender, String whom, String reason) {
		BanManager bm = new BanManager();
		bm.banPlayer(sender, whom, reason);
	}
	
	public void unbanPlayer(String sender, String whom, String reason) {
		BanManager bm = new BanManager();
		bm.unbanPlayer(sender, whom, reason);
	}
	
	private String convertPermission(String permission) {
		return permission = permission.replaceAll("_", ".");
	}
	
	public void registerPlayer() {
		PlayerManager pm = new PlayerManager(playerName);
		pm.playedTime.put(playerName, System.currentTimeMillis());
	}
	
}
