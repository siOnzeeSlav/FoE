package eu.frelania.foe.main;

import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import eu.frelania.foe.utils.MySQL;
import eu.frelania.foe.events.*;

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
	public boolean featureGui = false;
	public boolean guiWeeks = false;
	public boolean guiDays = false;
	public boolean guiHours = false;
	public boolean guiPlayers = false;
	public boolean guiKilledPlayers = false;
	public boolean guiKilledMobs = false;
	public boolean guiKilledAnimals = false;
	public boolean guiDeaths = false;
	public boolean guiIConomy = false;

	public FeaturesManager(FoE plugin, List<String> jokes) {
		foe = plugin;
		this.jokes = jokes;

		loadConfig();
	}

	private void loadConfig() {
		YamlConfiguration configuration = foe.getConfigManager().config;

		if (foe.getConfigManager().config.getBoolean("debugMode")) foe.debugMode = true;

		/**
		 * Registrace eventù
		 */
		Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(foe), foe);
		foe.logDebug("Event OnPlayerJoin zaregistrovan");

		Bukkit.getPluginManager().registerEvents(new OnPlayerQuit(foe), foe);
		foe.logDebug("Event OnPlayerQuit zaregistrovan");

		Bukkit.getPluginManager().registerEvents(new OnPlayerDeath(foe), foe);
		foe.logDebug("Event OnPlayerDeath zaregistrovan");

		Bukkit.getPluginManager().registerEvents(new OnEntityDeath(foe), foe);
		foe.logDebug("Event OnEntityDeath zaregistrovan");
		
		Bukkit.getPluginManager().registerEvents(new OnPlayerChat(foe), foe);
		foe.logDebug("Event OnPlayerChat zaregistrovan");

		/**
		 * Naètení DB, pokud je povolena
		 */
		if (status("mysql.povolit")) {
			featureMySQL = true;
			mysqlUpdateInterval = configuration.getInt("mysql.intervalNacitaniZDatabaze");
			// TODO: MySQL connection
		}

		/**
		 * Aktivace funkcí FoE
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

		if (status("nahrano.gui.povolit")) featureGui = true;
		if (status("nahrano.gui.tydny.zobrazit")) guiWeeks = true;
		if (status("nahrano.gui.dny.zobrazit")) guiDays = true;
		if (status("nahrano.gui.hodiny.zobrazit")) guiHours = true;
		if (status("nahrano.gui.pocetHracu.zobrazit")) guiPlayers = true;
		if (status("nahrano.gui.zabitoHracu.zobrazit")) guiKilledPlayers = true;
		if (status("nahrano.gui.zabitoMobu.zobrazit")) guiKilledMobs = true;
		if (status("nahrano.gui.zabitoZvirat.zobrazit")) guiKilledAnimals = true;
		if (status("nahrano.gui.pocetSmrti.zobrazit")) guiDeaths = true;
		if (status("nahrano.gui.iConomy.zobrazit")) guiIConomy = true;

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

		if (status("ostatni.prevestConfig")) {
			// TODO: Aktualizace configu
			
			set("oznameni.povolit", "Oznameni.Povolit");
			
			set("kdyzSeHrac.pripoji.povolit", "KdyzHracSe.Pripoji.Povolit");
			set("kdyzSeHrac.odpoji.povolit", "KdyzHracSe.Odpoji.Povolit");
			set("kdyzSeHrac.kick.povolit", "KdyzHracSe.Vyhodi.Povolit");
			
			set("smrt.povolitVlastniZpravy", "umrtiZpravy.Povolit");
			
			set("nahrano.povolit", "Nahranost.Povolit");
			
			set("chat.uvitaciZprava.povolit", "uvitaciZprava.Povolit");
			set("chat.vycistitChat.povolit", "clearChat.Povolit");
			set("chat.adminChat.povolit", "AdminChat.Povolit");
			set("chat.antiReklama.povolit", "AntiReklama.Povolit");
			set("chat.antiSpam.povolit", "AntiSpam.Povolit");
			set("chat.cenzura.povolit", "Cenzura.Povolit");
			set("chat.gramatika.povolit", "Gramatika.Povolit");
			set("chat.vypnoutChat.povolit", "VypnoutChat.Povolit");
			set("chat.capsLock.povolit", "capsLock.Povolit");
			
			set("updater.povolit", "VyhledavatAktualizace.Povolit");
			
			set("nahrano.gui.povolit", "Ostatni.Nahranost.GUI.Povolit");
			set("nahrano.gui.tydny.zobrazit", "Ostatni.Nahranost.GUI.Tydny-Povolit");
			set("nahrano.gui.dny.zobrazit", "Ostatni.Nahranost.GUI.Dny-Povolit");
			set("nahrano.gui.hodiny.zobrazit", "Ostatni.Nahranost.GUI.Hodiny-Povolit");
			set("nahrano.gui.pocetHracu.zobrazit", "Ostatni.Nahranost.GUI.PocetHracu-Povolit");
			set("nahrano.gui.zabitoHracu.zobrazit", "Ostatni.Nahranost.GUI.ZabitoHracu-Povolit");
			set("nahrano.gui.zabitoMobu.zobrazit", "Ostatni.Nahranost.GUI.ZabitoZvirat-Povolit");
			set("nahrano.gui.zabitoZvirat.zobrazit", "Ostatni.Nahranost.GUI.ZabitoMobu-Povolit");
			set("nahrano.gui.pocetSmrti.zobrazit", "Ostatni.Nahranost.GUI.PocetSmrti-Povolit");
			set("nahrano.gui.iConomy.zobrazit", "Ostatni.Nahranost.GUI.iConomy-Povolit");
			
			set("teleport.povolit", "TP.Povolit");
			
			set("rezervaceMist.povolit", "Rezervace.Povolit");
			
			set("inventar.povolit", "Inventar.Povolit");
			
			set("manager.povolit", "Manager.Povolit");
			
			set("vtipy.povoli", "Vtipy.Povolit");
			
			// FIXME: WhiteList Zpráva byla odstranìna!  -  set("whiteList.povolit", ""));
			
			set("autoZpravy.povolit", "autoZpravy.Povolit");
			
			set("warp.povolit", "Warp.Povolit");
			
			set("msg.povolit", "Msg.Povolit");
			
			set("gamemode.povolit", "herniRezimy.Povolit");
			
			set("help.povolit", "zpravaAdminum.Povolit");
			
			foe.getConfigManager().config.set("ostatni.prevestConfig", "ne");
		}

		foe.getLogger().log(Level.INFO, "Nastaveni uspesne nacteno!");

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

	private void set(String what, String msg) {
		foe.getConfigManager().config.set(what, foe.getConfigManager().config.get(msg));
		foe.getConfigManager().config.set(msg, null);
	}

}
