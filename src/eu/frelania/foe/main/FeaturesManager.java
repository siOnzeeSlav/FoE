package eu.frelania.foe.main;

import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import eu.frelania.foe.events.*;
import eu.frelania.foe.utils.MySQL;

public class FeaturesManager {

	private FoE foe;
	@SuppressWarnings("unused")
	private List<String> jokes;

	public MySQL mysql;
	public int mysqlUpdateInterval;
	public boolean featureMySQL = false;
	public boolean featureBroadcast = false;
	public boolean featureWhenPlayerJoin = false;
	public boolean featureWhenPlayerLeft = false;
	public boolean featureWhenPlayerKick = false;
	public boolean featureDeathMessages = false;
	public boolean featurePlayedTime = false;
	public boolean featurePlayedTimeWMsg = false;
	public boolean featureWelcomeMsg = false;
	public boolean featureChatWelcomeMsg = false;
	public boolean featureChatClear = false;
	public boolean featureChatAdminChat = false;
	public boolean featureChatAntiAds = false;
	public boolean featureChatAntiSpam = false;
	public boolean featureChatCensor = false;
	public boolean featureChatGrammar = false;
	public boolean featureChatOffChat = false;
	public boolean featureChatCapsLock = false;
	public boolean featureUpdater = false;
	public boolean featurePlayedTimeGui = false;
	public boolean featureTP = false;
	public boolean featureSlotsReservation = false;
	public boolean featureInventory = false;
	public boolean featureManager = false;
	public boolean featureJokes = false;
	public boolean featureWhitelist = false;
	public boolean featureAutoMessage = false;
	public boolean featureWarp = false;
	public boolean featureMsg = false;
	public boolean featureGamemode = false;
	public boolean featureHelp = false;

	public FeaturesManager(FoE plugin, List<String> jokes) {
		foe = plugin;
		this.jokes = jokes;

		loadConfig();
	}

	private void loadConfig() {
		YamlConfiguration configuration = foe.getConfigManager().config;

		if (foe.getConfigManager().config.getBoolean("debugMode")) foe.debugMode = true;

		/**
		 * Registrace event�
		 */
		Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(foe), foe);
		foe.logDebug("Event OnPlayerJoin zasegistrovan");

		Bukkit.getPluginManager().registerEvents(new OnPlayerQuit(foe), foe);
		foe.logDebug("Event OnPlayerQuit zasegistrovan");

		
		/**
		 * Na�ten� DB, pokud je povolena
		 */
		if (status("mysql.povolit")) {
			featureMySQL = true;
			mysqlUpdateInterval = configuration.getInt("mysql.intervalNacitaniZDatabaze");
			// TODO: MySQL connection
		}

		/**
		 * Aktivace funkc� FoE
		 */
		if (status("oznameni.povolit")) featureBroadcast = true;

		if (status("kdyzSeHrac.pripoji.povolit")) featureWhenPlayerJoin = true;
		if (status("kdyzSeHrac.odpoji.povolit")) featureWhenPlayerLeft = true;
		if (status("kdyzSeHrac.kick.povolit")) featureWhenPlayerKick = true;

		if (status("smrt.povolitVlastniZpravy")) featureDeathMessages = true;

		if (status("nahrano.povolit")) featurePlayedTime = true;

		if (status("chat.uvitaciZprava.povolit")) featureChatWelcomeMsg = true;
		if (status("chat.vycistitChat.povolit")) featureChatClear = true;
		if (status("chat.adminChat.povolit")) featureChatAdminChat = true;
		if (status("chat.antiReklama.povolit")) featureChatAntiAds = true;
		if (status("chat.antiSpam.povolit")) featureChatAntiSpam = true;
		if (status("chat.cenzura.povolit")) featureChatCensor = true;
		if (status("chat.gramatika.povolit")) featureChatGrammar = true;
		if (status("chat.vypnoutChat.povolit")) featureChatOffChat = true;
		if (status("chat.capsLock.povolit")) featureChatCapsLock = true;

		if (status("updater.povolit")) featureUpdater = true;

		if (status("nahrano.gui.povolit")) featurePlayedTimeGui = true;

		if (status("teleport.povolit")) featureTP = true;

		if (status("rezervaceMist.povolit")) featureSlotsReservation = true;

		if (status("inventar.povolit")) featureInventory = true;

		if (status("manager.povolit")) featureManager = true;

		if (status("vtipy.povolit")) {
			featureJokes = true;
			// TODO: Jokes loading & scheduling
		}

		if (status("whiteList.povolit")) featureWhitelist = true;

		if (status("autoZpravy.povolit")) featureAutoMessage = true;

		if (status("warp.povolit")) featureWarp = true;

		if (status("msg.povolit")) featureMsg = true;

		if (status("gamemode.povolit")) featureGamemode = true;

		if (status("help.povolit")) featureHelp = true;

		if (status("prevestConfig")) {
			// TODO: Aktualizace configu
			foe.getConfigManager().config.set("prevestConfig", "ne");
		}

		foe.getLogger().log(Level.INFO, "Configuration successfully loaded!");

	}

	private boolean status(String path) {
		if (path != null && foe.getConfigManager().config.contains(path)) {
			if (foe.getConfigManager().config.getString(path).equalsIgnoreCase("ano")) {
				if (foe.debugMode) foe.getErrorMananger().log(Level.CONFIG, path + ": ANO");
				return true;
			} else {
				if (foe.debugMode) foe.getErrorMananger().log(Level.CONFIG, path + ": NE");
			}
		} else {
			foe.getErrorMananger().log(Level.INFO, "\"" + path + "\" nebyl nalezen, aktualizujte prosim FoE/config.yml");
		}
		return false;
	}

}
