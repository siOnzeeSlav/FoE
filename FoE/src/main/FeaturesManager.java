package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import main.commands.cmdA;
import main.commands.cmdBAN;
import main.commands.cmdCENZURA;
import main.commands.cmdCHAT;
import main.commands.cmdCLEAR;
import main.commands.cmdCREATIVE;
import main.commands.cmdFOE;
import main.commands.cmdGRAMATIKA;
import main.commands.cmdHELP;
import main.commands.cmdINF;
import main.commands.cmdINV;
import main.commands.cmdKICK;
import main.commands.cmdMSG;
import main.commands.cmdOZNAMENI;
import main.commands.cmdSURVIVAL;
import main.commands.cmdTP;
import main.commands.cmdUNBAN;
import main.commands.cmdVTIP;
import main.commands.cmdWARP;
import main.events.EntityDeath;
import main.events.onChat;
import main.events.onHoldingsUpdate;
import main.events.onInventoryClick;
import main.events.onInventoryDrag;
import main.events.onJoin;
import main.events.onKick;
import main.events.onPlayerCommands;
import main.events.onPlayerDeath;
import main.events.onPlayerLogin;
import main.events.onQuit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FeaturesManager {
	
	public ConfigManager	cm;
	public boolean			Chat								= true;
	public boolean			mysqlIsEnabled						= false;
	public boolean			oznameniIsEnabled					= false;
	public boolean			kdyzHracSePripojiIsEnabled			= false;
	public boolean			kdyzHracSeOdpojiIsEnabled			= false;
	public boolean			kdyzHracSeVyhodiIsEnabled			= false;
	public boolean			nahranostIsEnabled					= false;
	public boolean			nahranostPrivitaciZpravaIsEnabled	= false;
	public boolean			antiReklamaIsEnabled				= false;
	public boolean			cenzuraIsEnabled					= false;
	public boolean			capsLockIsEnabled					= false;
	public boolean			gramatikaIsEnabled					= false;
	public boolean			vypnoutChatIsEnabled				= false;
	public boolean			adminChatIsEnabled					= false;
	public boolean			zpravaAdminum						= false;
	public boolean			teleportIsEnabled					= false;
	public boolean			guiIsEnabled						= false;
	public boolean			guiTydny							= false;
	public boolean			guiDny								= false;
	public boolean			guiHodiny							= false;
	public boolean			guiPocetHracu						= false;
	public boolean			guiIconomy							= false;
	public boolean			guiZabitoHracu						= false;
	public boolean			guiZabitoMobu						= false;
	public boolean			guiZabitoZvirat						= false;
	public boolean			guiPocetSmrti						= false;
	public boolean			antiSpamIsEnabled					= false;
	public boolean			antiSpamDuplikaceIsEnabled			= false;
	public boolean			rezervaceIsEnabled					= false;
	public boolean			inventarIsEnabled					= false;
	public boolean			managerBan							= false;
	public boolean			jokesIsEnabled						= false;
	public boolean			clearChat							= false;
	public boolean			umrtiZpravyIsEnabled				= false;
	public boolean			whiteListIsEnabled					= false;
	public boolean			uvitaciZpravaIsEnabled				= false;
	public boolean			autoZpravyIsEnabled					= false;
	public boolean			warpIsEnabled						= false;
	public boolean			msgIsEnabled						= false;
	public boolean			herniRezimy							= false;
	public boolean			debug								= false;
	public int				mysqlCas							= 0;
	public int				jokesInterval						= 0;
	public int				AntiSpamCas							= 0;
	public int				vyhledavatAktualizaceCas			= 0;
	public int				autoZpravyInterval					= 0;
	public Plugin			plugin;
	public MySQL			mysql;
	
	public FeaturesManager(ConfigManager cm, Plugin plugin, MySQL mysql) {
		cm = this.cm;
		plugin = this.plugin;
		mysql = this.mysql;
	}
	
	public FeaturesManager(ConfigManager cm, Plugin plugin) {
		cm = this.cm;
		plugin = this.plugin;
	}
	
	public FeaturesManager(ConfigManager cm) {
		cm = this.cm;
	}
	
	public void loadValues() {
		cm.config = YamlConfiguration.loadConfiguration(cm.configFile);
		
		if (cm.config.getBoolean("debug"))
			debug = true;
		
		System.out.println("Registruji event 'onPlayerLogin'");
		Bukkit.getPluginManager().registerEvents(new onPlayerLogin(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onPlayerLogin byl zaregistrovan.");
		
		System.out.println("Registruji event 'onJoin'");
		Bukkit.getPluginManager().registerEvents(new onJoin(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onJoin byl zaregistrovan.");
		
		System.out.println("Registruji event 'onQuit'");
		Bukkit.getPluginManager().registerEvents(new onQuit(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onQuit byl zaregistrovan.");
		
		System.out.println("Registruji event 'onKick'");
		Bukkit.getPluginManager().registerEvents(new onKick(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onKick byl zaregistrovan.");
		
		System.out.println("Registruji event 'onChat'");
		Bukkit.getPluginManager().registerEvents(new onChat(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onChat byl zaregistrovan.");
		
		System.out.println("Registruji event 'onInventoryClick'");
		Bukkit.getPluginManager().registerEvents(new onInventoryClick(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onInventoryClick byl zaregistrovan.");
		
		System.out.println("Registruji event 'onInventoryDrag'");
		
		Bukkit.getPluginManager().registerEvents(new onInventoryDrag(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onInventoryDrag byl zaregistrovan.");
		
		System.out.println("Registruji event 'EntityDeath'");
		Bukkit.getPluginManager().registerEvents(new EntityDeath(), plugin);
		if (debug)
			Bukkit.broadcastMessage("EntityDeath byl zaregistrovan.");
		
		System.out.println("Registruji event 'onPlayerDeath'");
		Bukkit.getPluginManager().registerEvents(new onPlayerDeath(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onPlayerDeath byl zaregistrovan.");
		
		System.out.println("Registruji event 'onPlayerCommands'");
		Bukkit.getPluginManager().registerEvents(new onPlayerCommands(), plugin);
		if (debug)
			Bukkit.broadcastMessage("onPlayerDeath byl zaregistrovan.");
		
		Bukkit.getServer().getPluginCommand("FoE").setExecutor(new cmdFOE(plugin));
		if (debug)
			Bukkit.broadcastMessage("FoE byl zaregistrovan.");
		
		jokesInterval = cm.config.getInt("jokes.Interval");
		if (debug)
			Bukkit.broadcastMessage("jokesInterval = " + jokesInterval);
		if (Status(cm.config, "Nahranost.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Nahranost") + "'");
			Bukkit.getServer().getPluginCommand("infcmd").setExecutor(new cmdINF(plugin));
			if (debug)
				Bukkit.broadcastMessage("infcmd byl zaregistrovan.");
			nahranostIsEnabled = true;
			if (Status(cm.config, "Nahranost.PrivitaciZprava.Povolit"))
				nahranostPrivitaciZpravaIsEnabled = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.Tydny-IsEnabled"))
				guiTydny = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.Dny-IsEnabled"))
				guiDny = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.Hodiny-IsEnabled"))
				guiHodiny = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.PocetHracu-IsEnabled"))
				guiPocetHracu = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.iConomy-IsEnabled"))
				guiIconomy = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.ZabitoHracu-IsEnabled"))
				guiZabitoHracu = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.ZabitoMobu-IsEnabled"))
				guiZabitoMobu = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.ZabitoZvirat-IsEnabled"))
				guiZabitoZvirat = true;
			if (Status(cm.config, "Ostatni.Nahranost.GUI.PocetSmrti-IsEnabled"))
				guiPocetSmrti = true;
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				PlayerManager pm = new PlayerManager(p);
				pm.registerPlayer();
				if (debug)
					Bukkit.broadcastMessage("Nahranost: Registruji hrace: " + p.getName());
			}
		}
		if (Status(cm.config, "Ostatni.Nahranost.GUI.iConomy-IsEnabled")) {
			System.out.println("Registruji event 'onHoldingsUpdate'");
			Bukkit.getPluginManager().registerEvents(new onHoldingsUpdate(), plugin);
			if (debug)
				Bukkit.broadcastMessage("onHoldinsUpdate byl zaregistrovan.");
		}
		if (Status(cm.config, "Oznameni.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Oznameni") + "'");
			Bukkit.getServer().getPluginCommand("zpravacmd").setExecutor(new cmdOZNAMENI());
			oznameniIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("zpravacmd byl zaregistrovan.");
		}
		if (Status(cm.config, "VypnoutChat.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.VypnoutChat") + "'");
			Bukkit.getServer().getPluginCommand("chatcmd").setExecutor(new cmdCHAT());
			vypnoutChatIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("chatcmd byl zaregistrovan.");
		}
		if (Status(cm.config, "Gramatika.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Gramatika") + "'");
			Bukkit.getServer().getPluginCommand("gramatikacmd").setExecutor(new cmdGRAMATIKA());
			gramatikaIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("gramatikacmd byl zaregistrovan.");
		}
		if (Status(cm.config, "Msg.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Msg") + "'");
			msgIsEnabled = true;
			Bukkit.getServer().getPluginCommand("msgcmd").setExecutor(new cmdMSG());
			if (debug)
				Bukkit.broadcastMessage("cmdmsg byl zaregistrovan.");
		}
		if (Status(cm.config, "Cenzura.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Cenzura") + "'");
			Bukkit.getServer().getPluginCommand("cenzuracmd").setExecutor(new cmdCENZURA());
			cenzuraIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("cenzuracmd byl zaregistrovan.");
		}
		if (Status(cm.config, "Warp.Povolit")) {
			warpIsEnabled = true;
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Warp") + "'");
			Bukkit.getServer().getPluginCommand("warpcmd").setExecutor(new cmdWARP());
			if (debug)
				Bukkit.broadcastMessage("warpcmd byl zaregistrovan.");
		}
		if (Status(cm.config, "AdminChat.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.AdminChat") + "'");
			Bukkit.getServer().getPluginCommand("acmd").setExecutor(new cmdA());
			adminChatIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("acmd byl zaregistrovan.");
		}
		if (Status(cm.config, "TP.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Teleport") + "'");
			Bukkit.getServer().getPluginCommand("tpcmd").setExecutor(new cmdTP());
			if (debug)
				Bukkit.broadcastMessage("tpcmd byl zaregistrovan.");
			teleportIsEnabled = true;
		}
		if (Status(cm.config, "autoZpravy.Povolit")) {
			autoZpravyIsEnabled = true;
			autoZpravyInterval = cm.config.getInt("autoZpravy.Interval");
			startLoop5(autoZpravyInterval);
			if (debug)
				Bukkit.broadcastMessage("autoZpravy Interval = " + autoZpravyInterval);
		}
		if (Status(cm.config, "Inventar.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Inventar") + "'");
			Bukkit.getServer().getPluginCommand("invcmd").setExecutor(new cmdINV());
			if (debug)
				Bukkit.broadcastMessage("invcmd byl zaregistrovan.");
			inventarIsEnabled = true;
		}
		if (Status(cm.config, "capsLock.Povolit")) {
			capsLockIsEnabled = true;
		}
		if (Status(cm.config, "Manager.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Manager.Ban") + "'");
			Bukkit.getServer().getPluginCommand("bancmd").setExecutor(new cmdBAN(plugin));
			if (debug)
				Bukkit.broadcastMessage("bancmd byl zaregistrovan.");
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Manager.Unban") + "'");
			Bukkit.getServer().getPluginCommand("unbancmd").setExecutor(new cmdUNBAN(plugin));
			if (debug)
				Bukkit.broadcastMessage("unbancmd byl zaregistrovan.");
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Manager.Kick") + "'");
			Bukkit.getServer().getPluginCommand("kickcmd").setExecutor(new cmdKICK(plugin));
			if (debug)
				Bukkit.broadcastMessage("kickcmd byl zaregistrovan.");
			managerBan = true;
		}
		if (Status(cm.config, "clearChat.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Clear") + "'");
			Bukkit.getServer().getPluginCommand("clearcmd").setExecutor(new cmdCLEAR(plugin));
			if (debug)
				Bukkit.broadcastMessage("clearcmd byl zaregistrovan.");
			clearChat = true;
		}
		if (Status(cm.config, "zpravaAdminum.Povolit")) {
			zpravaAdminum = true;
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Help") + "'");
			Bukkit.getServer().getPluginCommand("helpcmd").setExecutor(new cmdHELP(plugin));
			if (debug)
				Bukkit.broadcastMessage("helpcmd byl zaregistrovan.");
		}
		if (Status(cm.config, "Ostatni.Upgrade")) {
			Upgrade();
			if (debug)
				Bukkit.broadcastMessage("Upgrade byl proveden.");
			cm.config.set("Ostatni.Upgrade", "ne");
			cm.saveConfig(cm.config, cm.configFile);
			zkontrolovatPluginy();
			if (debug)
				Bukkit.broadcastMessage("Pluginy byli odstraneny.");
		}
		if (Status(cm.config, "AntiReklama.Povolit")) {
			antiReklamaIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Antireklama byla povolena.");
		}
		
		if (Status(cm.config, "KdyzHracSe.Pripoji.Povolit")) {
			kdyzHracSePripojiIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Zprava kdyz se hrac pripoji byla povolena.");
		}
		
		if (Status(cm.config, "KdyzHracSe.Vyhodi.Povolit")) {
			kdyzHracSeVyhodiIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Zprava kdyz se hrac vyhodi byla povolena.");
		}
		
		if (Status(cm.config, "KdyzHracSe.Odpoji.Povolit")) {
			kdyzHracSeOdpojiIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Zprava kdyz se hrac odpoji byla povolena.");
		}
		
		if (Status(cm.config, "uvitaciZprava.Povolit")) {
			if (debug)
				Bukkit.broadcastMessage("Uvitaci zprava byla povolena.");
			uvitaciZpravaIsEnabled = true;
		}
		if (Status(cm.config, "Ostatni.Nahranost.GUI.Povolit")) {
			if (debug)
				Bukkit.broadcastMessage("GUI bylo povoleno.");
			guiIsEnabled = true;
		}
		
		if (Status(cm.config, "AntiSpam.Povolit")) {
			if (debug)
				Bukkit.broadcastMessage("AntiSpam byl povolen.");
			AntiSpamCas = cm.config.getInt("AntiSpam.PockatSekund");
			antiSpamIsEnabled = true;
		}
		if (Status(cm.config, "AntiSpam.Duplikace.Povolit"))
			antiSpamDuplikaceIsEnabled = true;
		if (Status(cm.config, "Rezervace.Povolit")) {
			rezervaceIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Rezervace byla povolena.");
		}
		if (Status(cm.config, "umrtiZpravy.Povolit")) {
			umrtiZpravyIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("Umrtne zpravy byly povoleny.");
		}
		if (Status(cm.config, "whiteList.Povolit")) {
			whiteListIsEnabled = true;
			if (debug)
				Bukkit.broadcastMessage("WhiteListZprava byla povolena.");
		}
		if (Status(cm.config, "jokes.Povolit")) {
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Vtip") + "'");
			Bukkit.getServer().getPluginCommand("vtipcmd").setExecutor(new cmdVTIP(plugin));
			if (debug)
				Bukkit.broadcastMessage("vtipcmd byl zaregistrovan.");
			jokesIsEnabled = true;
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.foe.frelania.eu/jokes.txt").openStream()));
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append("\n");
					line = br.readLine();
				}
				String vysledek = sb.toString();
				for (String vtip : vysledek.split("#")) {
					jokes.add(vtip);
				}
			} catch (Exception e) {
				err.postError(e);
			}
			startLoop4(jokesInterval);
			if (debug)
				Bukkit.broadcastMessage("jokes byly povoleny.");
		}
		Plugin updater = Bukkit.getPluginManager().getPlugin("FoE-Updater");
		if (updater != null) {
			if (!updater.Povolit()) {
				if (debug)
					Bukkit.broadcastMessage("Updater nebyl nalezen.");
				if (Status(cm.config, "VyhledavatAktualizace.Povolit")) {
					vyhledavatAktualizaceCas = cm.config.getInt("VyhledavatAktualizace.Cas");
					zkontrolovatVerziPluginu();
					startLoop2(vyhledavatAktualizaceCas);
				}
			} else {
				if (debug)
					Bukkit.broadcastMessage("Updater byl nalezen.");
			}
		} else {
			if (Status(cm.config, "VyhledavatAktualizace.Povolit")) {
				vyhledavatAktualizaceCas = cm.config.getInt("VyhledavatAktualizace.Cas");
				zkontrolovatVerziPluginu();
				startLoop2(vyhledavatAktualizaceCas);
			}
		}
		if (Status(cm.config, "herniRezimy.Povolit")) {
			herniRezimy = true;
			if (debug)
				Bukkit.broadcastMessage("herniRezimy byly povoleny.");
			
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Creative") + "'");
			Bukkit.getServer().getPluginCommand("creativecmd").setExecutor(new cmdCREATIVE());
			if (debug)
				Bukkit.broadcastMessage("creativecmd byl zaregistrovan.");
			
			System.out.println("Registruji prikaz '" + cm.config.getString("Prikazy.Survival") + "'");
			Bukkit.getServer().getPluginCommand("survivalcmd").setExecutor(new cmdSURVIVAL());
			if (debug)
				Bukkit.broadcastMessage("survivalcmd byl zaregistrovan.");
		}
		if (Status(cm.config, "MySQL.Povolit")) {
			mysql = new MySQL();
			mysqlCas = cm.config.getInt("MySQL.Cas");
			mysql.open();
			if (debug)
				Bukkit.broadcastMessage("MySQL bylo povoleno.");
			mysqlIsEnabled = true;
		}
		if (debug)
			Bukkit.broadcastMessage("Server byl pridan do statistik.");
		if (debug)
			Bukkit.broadcastMessage("[FoE] byl uspesne zapnut.");
		System.out.println("[FoE] byl uspesne zapnut.");
	}
	
	public boolean Status(YamlConfiguration config, String node) {
		if (node != null && config != null) {
			if (debug)
				System.out.println(node);
			if (cm.config.getString(node).equalsIgnoreCase("ano"))
				return true;
		} else {
			System.out.println(node + " nebyl nalezen, aktualizujte cm.config.");
		}
		return false;
	}
}
