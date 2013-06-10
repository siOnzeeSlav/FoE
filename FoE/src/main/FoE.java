package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import main.commands.cmdA;
import main.commands.cmdBAN;
import main.commands.cmdCENZURA;
import main.commands.cmdCHAT;
import main.commands.cmdCLEAR;
import main.commands.cmdFOE;
import main.commands.cmdGRAMATIKA;
import main.commands.cmdHELP;
import main.commands.cmdINF;
import main.commands.cmdINV;
import main.commands.cmdKICK;
import main.commands.cmdMSG;
import main.commands.cmdTP;
import main.commands.cmdUNBAN;
import main.commands.cmdVTIP;
import main.commands.cmdWARP;
import main.commands.cmdZPRAVA;
import main.events.EntityDeath;
import main.events.onChat;
import main.events.onHoldingsUpdate;
import main.events.onInventoryClick;
import main.events.onInventoryDrag;
import main.events.onJoin;
import main.events.onKick;
import main.events.onPlayerDeath;
import main.events.onPlayerLogin;
import main.events.onQuit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;

public class FoE extends JavaPlugin implements Listener {
	
	public File						configFile						= new File("plugins/FoE/config.yml");
	public YamlConfiguration		config;
	public File						umrtiZpravyFile					= new File("plugins/FoE/umrtiZpravy.yml");
	public YamlConfiguration		umrtiZpravy						= YamlConfiguration.loadConfiguration(umrtiZpravyFile);
	public File						uzivFile;
	public YamlConfiguration		uziv;
	public HashMap<String, Long>	nahranyCas						= new HashMap<String, Long>();
	public MySQL					mysql;
	public List<String>				vtipy							= new ArrayList<String>();
	public int						minutesLeft						= 0;
	public int						minutesLeft2					= 0;
	public int						minutesLeft3					= 0;
	public int						minutesLeft4					= 0;
	public int						minutesLeft5					= 0;
	public int						AntiSpamCas						= 0;
	public int						vyhledavatAktualizaceCas		= 0;
	public int						mysqlCas						= 0;
	public int						vtipyInterval					= 0;
	public int						autoZpravyInterval				= 0;
	public boolean					Chat							= true;
	public boolean					mysqlPovolit					= false;
	public boolean					oznameniPovolit					= false;
	public boolean					kdyzHracSePripojiPovolit		= false;
	public boolean					kdyzHracSeOdpojiPovolit			= false;
	public boolean					kdyzHracSeVyhodiPovolit			= false;
	public boolean					nahranostPovolit				= false;
	public boolean					nahranostPrivitaciZpravaPovolit	= false;
	public boolean					antiReklamaPovolit				= false;
	public boolean					cenzuraPovolit					= false;
	public boolean					capsLockPovolit					= false;
	public boolean					gramatikaPovolit				= false;
	public boolean					vypnoutChatPovolit				= false;
	public boolean					adminChatPovolit				= false;
	public boolean					zpravaAdminum					= false;
	public boolean					teleportPovolit					= false;
	public boolean					guiPovolit						= false;
	public boolean					guiTydny						= false;
	public boolean					guiDny							= false;
	public boolean					guiHodiny						= false;
	public boolean					guiPocetHracu					= false;
	public boolean					guiIconomy						= false;
	public boolean					guiZabitoHracu					= false;
	public boolean					guiZabitoMobu					= false;
	public boolean					guiZabitoZvirat					= false;
	public boolean					guiPocetSmrti					= false;
	public boolean					antiSpamPovolit					= false;
	public boolean					antiSpamDuplikacePovolit		= false;
	public boolean					rezervacePovolit				= false;
	public boolean					inventarPovolit					= false;
	public boolean					managerBan						= false;
	public boolean					vtipyPovolit					= false;
	public boolean					clearChat						= false;
	public boolean					umrtiZpravyPovolit				= false;
	public boolean					whiteListPovolit				= false;
	public boolean					uvitaciZpravaPovolit			= false;
	public boolean					autoZpravyPovolit				= false;
	public boolean					warpPovolit						= false;
	public boolean					msgPovolit						= false;
	public boolean					herniRezimy						= false;
	public boolean					debug							= false;
	public ConfigManager			cm								= new ConfigManager();
	
	@Override
	public void onEnable() {
		cm.checkConfig();
		if (cm.loaded) {
			config = YamlConfiguration.loadConfiguration(configFile);
			if (config.getBoolean("debug"))
				debug = true;
			System.out.println("Registruji event 'onPlayerLogin'");
			Bukkit.getPluginManager().registerEvents(new onPlayerLogin(this), this);
			if (debug)
				Bukkit.broadcastMessage("onPlayerLogin byl zaregistrovan.");
			System.out.println("Registruji event 'onJoin'");
			Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
			if (debug)
				Bukkit.broadcastMessage("onJoin byl zaregistrovan.");
			System.out.println("Registruji event 'onQuit'");
			Bukkit.getPluginManager().registerEvents(new onQuit(this), this);
			if (debug)
				Bukkit.broadcastMessage("onQuit byl zaregistrovan.");
			System.out.println("Registruji event 'onKick'");
			Bukkit.getPluginManager().registerEvents(new onKick(this), this);
			if (debug)
				Bukkit.broadcastMessage("onKick byl zaregistrovan.");
			System.out.println("Registruji event 'onChat'");
			Bukkit.getPluginManager().registerEvents(new onChat(this), this);
			if (debug)
				Bukkit.broadcastMessage("onChat byl zaregistrovan.");
			System.out.println("Registruji event 'onInventoryClick'");
			Bukkit.getPluginManager().registerEvents(new onInventoryClick(this), this);
			if (debug)
				Bukkit.broadcastMessage("onInventoryClick byl zaregistrovan.");
			System.out.println("Registruji event 'onInventoryDrag'");
			Bukkit.getPluginManager().registerEvents(new onInventoryDrag(this), this);
			if (debug)
				Bukkit.broadcastMessage("onInventoryDrag byl zaregistrovan.");
			System.out.println("Registruji event 'EntityDeath'");
			Bukkit.getPluginManager().registerEvents(new EntityDeath(this), this);
			if (debug)
				Bukkit.broadcastMessage("EntityDeath byl zaregistrovan.");
			System.out.println("Registruji event 'onPlayerDeath'");
			Bukkit.getPluginManager().registerEvents(new onPlayerDeath(this), this);
			if (debug)
				Bukkit.broadcastMessage("onPlayerDeath byl zaregistrovan.");
			Bukkit.getPluginManager().registerEvents(this, this);
			if (debug)
				Bukkit.broadcastMessage("Zdejsi eventy byly zaregistrovany.");
			Bukkit.getServer().getPluginCommand("FoE").setExecutor(new cmdFOE(this));
			if (debug)
				Bukkit.broadcastMessage("FoE byl zaregistrovan.");
			vtipyInterval = config.getInt("Vtipy.Interval");
			if (debug)
				Bukkit.broadcastMessage("vtipyInterval = " + vtipyInterval);
			if (Status(config, "Nahranost.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Nahranost") + "'");
				Bukkit.getServer().getPluginCommand("infcmd").setExecutor(new cmdINF(this));
				if (debug)
					Bukkit.broadcastMessage("infcmd byl zaregistrovan.");
				nahranostPovolit = true;
				if (Status(config, "Nahranost.PrivitaciZprava.Povolit"))
					nahranostPrivitaciZpravaPovolit = true;
				if (Status(config, "Ostatni.Nahranost.GUI.Tydny-Povolit"))
					guiTydny = true;
				if (Status(config, "Ostatni.Nahranost.GUI.Dny-Povolit"))
					guiDny = true;
				if (Status(config, "Ostatni.Nahranost.GUI.Hodiny-Povolit"))
					guiHodiny = true;
				if (Status(config, "Ostatni.Nahranost.GUI.PocetHracu-Povolit"))
					guiPocetHracu = true;
				if (Status(config, "Ostatni.Nahranost.GUI.iConomy-Povolit"))
					guiIconomy = true;
				if (Status(config, "Ostatni.Nahranost.GUI.ZabitoHracu-Povolit"))
					guiZabitoHracu = true;
				if (Status(config, "Ostatni.Nahranost.GUI.ZabitoMobu-Povolit"))
					guiZabitoMobu = true;
				if (Status(config, "Ostatni.Nahranost.GUI.ZabitoZvirat-Povolit"))
					guiZabitoZvirat = true;
				if (Status(config, "Ostatni.Nahranost.GUI.PocetSmrti-Povolit"))
					guiPocetSmrti = true;
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					registrovatHrace(p.getName());
					if (debug)
						Bukkit.broadcastMessage("Nahranost: Registruji hrace: " + p.getName());
				}
			}
			if (Status(config, "Ostatni.Nahranost.GUI.iConomy-Povolit")) {
				System.out.println("Registruji event 'onHoldingsUpdate'");
				Bukkit.getPluginManager().registerEvents(new onHoldingsUpdate(this), this);
				if (debug)
					Bukkit.broadcastMessage("onHoldinsUpdate byl zaregistrovan.");
			}
			if (Status(config, "Oznameni.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Oznameni") + "'");
				Bukkit.getServer().getPluginCommand("zpravacmd").setExecutor(new cmdZPRAVA(this));
				oznameniPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("zpravacmd byl zaregistrovan.");
			}
			if (Status(config, "VypnoutChat.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.VypnoutChat") + "'");
				Bukkit.getServer().getPluginCommand("chatcmd").setExecutor(new cmdCHAT(this));
				vypnoutChatPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("chatcmd byl zaregistrovan.");
			}
			if (Status(config, "Gramatika.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Gramatika") + "'");
				Bukkit.getServer().getPluginCommand("gramatikacmd").setExecutor(new cmdGRAMATIKA(this));
				gramatikaPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("gramatikacmd byl zaregistrovan.");
			}
			if (Status(config, "Msg.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Msg") + "'");
				msgPovolit = true;
				Bukkit.getServer().getPluginCommand("msgcmd").setExecutor(new cmdMSG(this));
				if (debug)
					Bukkit.broadcastMessage("cmdmsg byl zaregistrovan.");
			}
			if (Status(config, "Cenzura.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Cenzura") + "'");
				Bukkit.getServer().getPluginCommand("cenzuracmd").setExecutor(new cmdCENZURA(this));
				cenzuraPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("cenzuracmd byl zaregistrovan.");
			}
			if (Status(config, "Warp.Povolit")) {
				warpPovolit = true;
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Warp") + "'");
				Bukkit.getServer().getPluginCommand("warpcmd").setExecutor(new cmdWARP(this));
				if (debug)
					Bukkit.broadcastMessage("warpcmd byl zaregistrovan.");
			}
			if (Status(config, "AdminChat.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.AdminChat") + "'");
				Bukkit.getServer().getPluginCommand("acmd").setExecutor(new cmdA(this));
				adminChatPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("acmd byl zaregistrovan.");
			}
			if (Status(config, "TP.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Teleport") + "'");
				Bukkit.getServer().getPluginCommand("tpcmd").setExecutor(new cmdTP(this));
				if (debug)
					Bukkit.broadcastMessage("tpcmd byl zaregistrovan.");
				teleportPovolit = true;
			}
			if (Status(config, "autoZpravy.Povolit")) {
				autoZpravyPovolit = true;
				autoZpravyInterval = config.getInt("autoZpravy.Interval");
				startLoop5(autoZpravyInterval);
				if (debug)
					Bukkit.broadcastMessage("autoZpravy Interval = " + autoZpravyInterval);
			}
			if (Status(config, "Inventar.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Inventar") + "'");
				Bukkit.getServer().getPluginCommand("invcmd").setExecutor(new cmdINV(this));
				if (debug)
					Bukkit.broadcastMessage("invcmd byl zaregistrovan.");
				inventarPovolit = true;
			}
			if (Status(config, "capsLock.Povolit")) {
				capsLockPovolit = true;
			}
			if (Status(config, "Manager.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Manager.Ban") + "'");
				Bukkit.getServer().getPluginCommand("bancmd").setExecutor(new cmdBAN(this));
				if (debug)
					Bukkit.broadcastMessage("bancmd byl zaregistrovan.");
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Manager.Unban") + "'");
				Bukkit.getServer().getPluginCommand("unbancmd").setExecutor(new cmdUNBAN(this));
				if (debug)
					Bukkit.broadcastMessage("unbancmd byl zaregistrovan.");
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Manager.Kick") + "'");
				Bukkit.getServer().getPluginCommand("kickcmd").setExecutor(new cmdKICK(this));
				if (debug)
					Bukkit.broadcastMessage("kickcmd byl zaregistrovan.");
				managerBan = true;
			}
			if (Status(config, "clearChat.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Clear") + "'");
				Bukkit.getServer().getPluginCommand("clearcmd").setExecutor(new cmdCLEAR(this));
				if (debug)
					Bukkit.broadcastMessage("clearcmd byl zaregistrovan.");
				clearChat = true;
			}
			if (Status(config, "zpravaAdminum.Povolit")) {
				zpravaAdminum = true;
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Help") + "'");
				Bukkit.getServer().getPluginCommand("helpcmd").setExecutor(new cmdHELP(this));
				if (debug)
					Bukkit.broadcastMessage("helpcmd byl zaregistrovan.");
			}
			if (Status(config, "Ostatni.Upgrade")) {
				Upgrade();
				if (debug)
					Bukkit.broadcastMessage("Upgrade byl proveden.");
				config.set("Ostatni.Upgrade", "ne");
				saveConfig(config, configFile);
				zkontrolovatPluginy();
				if (debug)
					Bukkit.broadcastMessage("Pluginy byli odstraneny.");
			}
			if (Status(config, "AntiReklama.Povolit")) {
				antiReklamaPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Antireklama byla povolena.");
			}
			
			if (Status(config, "KdyzHracSe.Pripoji.Povolit")) {
				kdyzHracSePripojiPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Zprava kdyz se hrac pripoji byla povolena.");
			}
			
			if (Status(config, "KdyzHracSe.Vyhodi.Povolit")) {
				kdyzHracSeVyhodiPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Zprava kdyz se hrac vyhodi byla povolena.");
			}
			
			if (Status(config, "KdyzHracSe.Odpoji.Povolit")) {
				kdyzHracSeOdpojiPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Zprava kdyz se hrac odpoji byla povolena.");
			}
			
			if (Status(config, "uvitaciZprava.Povolit")) {
				if (debug)
					Bukkit.broadcastMessage("Uvitaci zprava byla povolena.");
				uvitaciZpravaPovolit = true;
			}
			if (Status(config, "Ostatni.Nahranost.GUI.Povolit")) {
				if (debug)
					Bukkit.broadcastMessage("GUI bylo povoleno.");
				guiPovolit = true;
			}
			
			if (Status(config, "AntiSpam.Povolit")) {
				if (debug)
					Bukkit.broadcastMessage("AntiSpam byl povolen.");
				AntiSpamCas = config.getInt("AntiSpam.PockatSekund");
				antiSpamPovolit = true;
			}
			if (Status(config, "AntiSpam.Duplikace.Povolit"))
				antiSpamDuplikacePovolit = true;
			if (Status(config, "Rezervace.Povolit")) {
				rezervacePovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Rezervace byla povolena.");
			}
			if (Status(config, "umrtiZpravy.Povolit")) {
				umrtiZpravyPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("Umrtne zpravy byly povoleny.");
			}
			if (Status(config, "whiteList.Povolit")) {
				whiteListPovolit = true;
				if (debug)
					Bukkit.broadcastMessage("WhiteListZprava byla povolena.");
			}
			if (Status(config, "Vtipy.Povolit")) {
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Vtip") + "'");
				Bukkit.getServer().getPluginCommand("vtipcmd").setExecutor(new cmdVTIP(this));
				if (debug)
					Bukkit.broadcastMessage("vtipcmd byl zaregistrovan.");
				vtipyPovolit = true;
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.foe.frelania.eu/vtipy.txt").openStream()));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();
					while (line != null) {
						sb.append(line);
						sb.append("\n");
						line = br.readLine();
					}
					String vysledek = sb.toString();
					for (String vtip : vysledek.split("#")) {
						vtipy.add(vtip);
					}
				} catch (Exception e) {
					Writer writer = new StringWriter();
					PrintWriter printWriter = new PrintWriter(writer);
					e.printStackTrace(printWriter);
					Error(writer.toString());
				}
				startLoop4(vtipyInterval);
				if (debug)
					Bukkit.broadcastMessage("Vtipy byly povoleny.");
			}
			Plugin updater = Bukkit.getPluginManager().getPlugin("FoE-Updater");
			if (updater != null) {
				if (!updater.isEnabled()) {
					if (debug)
						Bukkit.broadcastMessage("Updater nebyl nalezen.");
					if (Status(config, "VyhledavatAktualizace.Povolit")) {
						vyhledavatAktualizaceCas = config.getInt("VyhledavatAktualizace.Cas");
						zkontrolovatVerziPluginu();
						startLoop2(vyhledavatAktualizaceCas);
					}
				} else {
					if (debug)
						Bukkit.broadcastMessage("Updater byl nalezen.");
				}
			} else {
				if (Status(config, "VyhledavatAktualizace.Povolit")) {
					vyhledavatAktualizaceCas = config.getInt("VyhledavatAktualizace.Cas");
					zkontrolovatVerziPluginu();
					startLoop2(vyhledavatAktualizaceCas);
				}
			}
			/*if (Status(config, "herniRezimy.Povolit")) {
				herniRezimy = true;
				if (debug)
					Bukkit.broadcastMessage("herniRezimy byly povoleny.");
				
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Creative") + "'");
				Bukkit.getServer().getPluginCommand("creativecmd").setExecutor(new cmdCREATIVE(this));
				if (debug)
					Bukkit.broadcastMessage("creativecmd byl zaregistrovan.");
				
				System.out.println("Registruji prikaz '" + config.getString("Prikazy.Survival") + "'");
				Bukkit.getServer().getPluginCommand("survivalcmd").setExecutor(new cmdSURVIVAL(this));
				if (debug)
					Bukkit.broadcastMessage("survivalcmd byl zaregistrovan.");
			}*/
			if (Status(config, "MySQL.Povolit")) {
				mysql = new MySQL(this);
				mysqlCas = config.getInt("MySQL.Cas");
				mysql.open();
				if (debug)
					Bukkit.broadcastMessage("MySQL bylo povoleno.");
				mysqlPovolit = true;
				mysql.MySQLPovolit = true;
			}
			statistiky();
			if (debug)
				Bukkit.broadcastMessage("Server byl pridan do statistik.");
			if (debug)
				Bukkit.broadcastMessage("[FoE] byl uspesne zapnut.");
			System.out.println("[FoE] byl uspesne zapnut.");
		}
	}
	
	public void MySQL_Warp(String warpName, String playerName, String typ) {
		try {
			if (warpName != null && playerName != null) {
				return;
			}
			if (typ == "AKTIVNI") {
				ResultSet rs = mysql.query("SELECT `warp` FROM `FoE_Warpy` WHERE `warp` = '" + warpName + "'");
				if (rs.next()) {
					mysql.query("UPDATE `FoE_Warpy` SET `typ` = 'AKTIVNI' AND SET `datum` = '" + System.currentTimeMillis() + "' WHERE `warp` = '" + warpName + "'");
				} else {
					mysql.query("INSERT INTO `FoE_Warpy` (warp, autor, datum, typ) VALUES (" + "'" + warpName + "'," + " '" + playerName + "', '" + System.currentTimeMillis() + "', 'AKTIVNI')");
				}
			}
			if (typ == "ODSTRANENO") {
				ResultSet rs = mysql.query("SELECT `warp` FROM `FoE_Warpy` WHERE `warp` = '" + warpName + "'");
				if (rs.next()) {
					mysql.query("UPDATE `FoE_Warpy` SET `typ` = 'ODSTRANENO' WHERE `warp` = '" + warpName + "'");
				}
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void MySQL_Message(String playerName, String targetName, String message) {
		try {
			mysql.query("INSERT INTO `FoE_Zpravy` (hrac, prijemce, zprava, datum) VALUES ('" + playerName + "', '" + targetName + "', '" + message + "', '" + System.currentTimeMillis() + "')");
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void MySQL_Nahranost(String playerName) {
		try {
			uzivatel(playerName);
			Long nahranost = (System.currentTimeMillis() - nahranyCas.get(playerName)) + uziv.getLong("Nahrano");
			ResultSet rs = mysql.query("SELECT `hrac` FROM `FoE_Uzivatele` WHERE `hrac` = '" + playerName + "'");
			if (rs.next()) {
				mysql.query("UPDATE `FoE_Uzivatele` SET `nahranost` = '" + nahranost + "' WHERE `hrac` = '" + playerName + "'");
			} else {
				mysql.query("INSERT INTO `FoE_Uzivatele` (hrac,nahranost) VALUES ('" + playerName + "', '" + nahranost + "')");
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void aktualizovatGUI(String playerName) {
		try {
			Player player = Bukkit.getPlayer(playerName);
			if (player != null) {
				uzivatel(playerName);
				long[] cas;
				if (nahranyCas.containsKey(playerName))
					cas = spravnyFormat((System.currentTimeMillis() - nahranyCas.get(playerName)) + uziv.getLong("Nahrano"));
				else
					cas = spravnyFormat(uziv.getLong("Nahrano"));
				
				Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective objective = board.registerNewObjective(playerName, "dummy");
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
				objective.setDisplayName(nahradit(config.getString("Ostatni.Nahranost.GUI.Nadpis"), playerName));
				Score score = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.Tydny")));
				Score score2 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.Dny")));
				Score score3 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.Hodiny")));
				Score score4 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.PocetHracu")));
				Score score5 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.iConomy")));
				Score score6 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.ZabitoHracu")));
				Score score7 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.ZabitoMobu")));
				Score score8 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.ZabitoZvirat")));
				Score score9 = objective.getScore(Bukkit.getOfflinePlayer(config.getString("Ostatni.Nahranost.GUI.PocetSmrti")));
				
				if (guiTydny)
					score.setScore((int) cas[4]);
				if (guiDny)
					score2.setScore((int) cas[3]);
				if (guiHodiny)
					score3.setScore((int) cas[2]);
				if (guiPocetHracu)
					score4.setScore(Bukkit.getOnlinePlayers().length);
				if (guiIconomy) {
					Account account = new Accounts().get(playerName);
					Double money = account.getHoldings().getBalance();
					int intMoney = money.intValue();
					score5.setScore(intMoney);
				}
				if (guiZabitoHracu)
					score6.setScore(uziv.getInt("ZabitoHracu"));
				if (guiZabitoMobu)
					score7.setScore(uziv.getInt("ZabitoMobu"));
				if (guiZabitoZvirat)
					score8.setScore(uziv.getInt("ZabitoZvirat"));
				if (guiPocetSmrti)
					score9.setScore(uziv.getInt("PocetSmrti"));
				player.setScoreboard(board);
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void ulozitPozici(Player player) {
		try {
			String playerName = player.getName();
			Double X = player.getLocation().getX();
			Double Y = player.getLocation().getY();
			Double Z = player.getLocation().getZ();
			uzivatel(playerName);
			uziv.set("Svet", player.getLocation().getWorld().getName());
			uziv.set("X", X);
			uziv.set("Y", Y);
			uziv.set("Z", Z);
			saveConfig(uziv, uzivFile);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void Error(String message) {
		try {
			File u = new File("plugins/FoE/errors.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("================== " + time + " - FoE: " + getDescription().getVersion() + "\n" + "CB: " + Bukkit.getVersion() + "\n" + message + "\n==================\n");
			pw.flush();
			pw.close();
			System.out.println("[FoE] ERROR!");
			System.out.println("===========================");
			System.out.println("Prekopirujte obsah souboru errors.log do prispevku.");
			System.out.println("===========================");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void minute2() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				minutesLeft2 -= 1;
				if (minutesLeft2 > 0) {
					minute2();
				}
				
				if (minutesLeft2 == 0) {
					zkontrolovatVerziPluginu();
					startLoop2(vyhledavatAktualizaceCas);
				}
			}
		}, 1200L);
	}
	
	public void minute4() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				minutesLeft4 -= 1;
				if (minutesLeft4 > 0) {
					minute4();
				}
				
				if (minutesLeft4 == 0) {
					try {
						Bukkit.broadcastMessage(nahraditVtip(config.getString("Vtipy.Format")));
						startLoop4(vtipyInterval);
					} catch (Exception e) {
						Writer writer = new StringWriter();
						PrintWriter printWriter = new PrintWriter(writer);
						e.printStackTrace(printWriter);
						Error(writer.toString());
					}
				}
			}
		}, 1200L);
	}
	
	public String nahraditVtip(String message) {
		if (message.matches(".*\\{VTIP}.*")) {
			Random rnd = new Random();
			message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
			return message = message.replaceAll("\\{VTIP}", vtipy.get(rnd.nextInt(vtipy.size())));
		}
		return "ERROR(FORMAT:VTIP)";
	}
	
	public String replaceWelcomeMessage(String message, String playerName, String worldName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		
		if (message.matches(".*\\{SVET}.*"))
			message = message.replaceAll("\\{SVET}", worldName);
		
		if (message.matches(".*\\{CAS}.*")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(date);
			message = message.replaceAll("\\{CAS}", time);
		}
		
		if (message.matches(".*\\[RADEK].*"))
			message = message.replaceAll("\\[RADEK]", "\n");
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public void startLoop4(int length) {
		minutesLeft4 = length;
		minute4();
	}
	
	public void startLoop2(int length) {
		minutesLeft2 = length;
		minute2();
	}
	
	public void registrovatHrace(String jmenoHrace) {
		try {
			nahranyCas.put(jmenoHrace, System.currentTimeMillis());
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void odRegistrovatHrace(String jmenoHrace) {
		try {
			if (nahranyCas.containsKey(jmenoHrace)) {
				uzivatel(jmenoHrace);
				long casPripojeni = nahranyCas.get(jmenoHrace), vConfigu = uziv.getLong("Nahrano"), vysledek = System.currentTimeMillis() - casPripojeni + vConfigu;
				uziv.set("Nahrano", vysledek);
				saveConfig(uziv, uzivFile);
				if (mysqlPovolit)
					MySQL_Nahranost(jmenoHrace);
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			odRegistrovatHrace(p.getName());
		}
		Bukkit.getScheduler().cancelAllTasks();
	}
	
	public void zkontrolovatPluginy() {
		try {
			Plugin nahrano = Bukkit.getPluginManager().getPlugin("Nahrano"), cestinator = Bukkit.getPluginManager().getPlugin("Cestinator"), antireklama = Bukkit.getPluginManager().getPlugin("AntiReklama"), verejnazprava = Bukkit.getPluginManager().getPlugin("VerejnaZprava"), vypnoutchat = Bukkit.getPluginManager().getPlugin("VypnoutChat");
			File nahranoFile = new File("plugins/Nahrano.jar"), cestinatorFile = new File("plugins/Cestinator.jar"), antireklamaFile = new File("plugins/AntiReklama.jar"), verejnazpravaFile = new File("plugins/VerejnaZprava.jar"), vypnoutchatFile = new File("plugins/VypnoutChat.jar");
			if (nahrano != null) {
				Bukkit.getPluginManager().disablePlugin(nahrano);
				nahranoFile.delete();
			}
			if (cestinator != null) {
				Bukkit.getPluginManager().disablePlugin(cestinator);
				cestinatorFile.delete();
			}
			if (antireklama != null) {
				Bukkit.getPluginManager().disablePlugin(antireklama);
				antireklamaFile.delete();
			}
			if (verejnazprava != null) {
				Bukkit.getPluginManager().disablePlugin(verejnazprava);
				verejnazpravaFile.delete();
			}
			if (vypnoutchat != null) {
				Bukkit.getPluginManager().disablePlugin(vypnoutchat);
				vypnoutchatFile.delete();
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public String nahraditCas(String zprava, String hrac) {
		if (zprava != null && hrac != null && nahranyCas.containsKey(hrac)) {
			uzivatel(hrac);
			long[] cas = spravnyFormat(System.currentTimeMillis() - nahranyCas.get(hrac) + uziv.getLong("Nahrano"));
			String s = String.valueOf(cas[0]), m = String.valueOf(cas[1]), h = String.valueOf(cas[2]), d = String.valueOf(cas[3]), t = String.valueOf(cas[4]);
			if (zprava.matches(".*\\{TYDEN}.*")) {
				zprava = zprava.replaceAll("\\{TYDEN}", t);
			}
			if (zprava.matches(".*\\{DEN}.*")) {
				zprava = zprava.replaceAll("\\{DEN}", d);
			}
			if (zprava.matches(".*\\{HODIN}.*")) {
				zprava = zprava.replaceAll("\\{HODIN}", h);
			}
			if (zprava.matches(".*\\{MINUT}.*")) {
				zprava = zprava.replaceAll("\\{MINUT}", m);
			}
			if (zprava.matches(".*\\{SEKUND}.*")) {
				zprava = zprava.replaceAll("\\{SEKUND}", s);
			}
		} else {
			zprava = null;
		}
		return zprava;
	}
	
	public long[] spravnyFormat(long Long) {
		long sekundy = Long / 1000L;
		long minuty = 0L;
		long hodiny = 0L;
		long dny = 0L;
		long tydny = 0L;
		
		while (sekundy > 60L) {
			minuty += 1L;
			sekundy -= 60L;
		}
		
		while (minuty > 60L) {
			hodiny += 1L;
			minuty -= 60L;
		}
		
		while (hodiny > 24L) {
			dny += 1L;
			hodiny -= 24L;
		}
		
		while (dny > 7L) {
			tydny += 1L;
			dny -= 7L;
		}
		return new long[] { sekundy, minuty, hodiny, dny, tydny };
	}
	
	public void checkUser(String playerName) {
		try {
			uzivFile = new File("plugins/FoE/uzivatele/" + playerName + ".yml");
			uziv = YamlConfiguration.loadConfiguration(uzivFile);
			
			if (!uziv.contains("Nahranost"))
				uziv.set("Nahrano", 0);
			
			if (!uziv.contains("isBanned"))
				uziv.set("isBanned", false);
			
			if (!uziv.contains("ZabitoHracu"))
				uziv.set("ZabitoHracu", 0);
			
			if (!uziv.contains("ZabitoMobu"))
				uziv.set("ZabitoMobu", 0);
			
			if (!uziv.contains("ZabitoZvirat"))
				uziv.set("ZabitoZvirat", 0);
			
			if (!uziv.contains("PocetSmrti"))
				uziv.set("PocetSmrti", 0);
			
			saveConfig(uziv, uzivFile);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void uzivatel(String jmenoHrace) {
		try {
			uzivFile = new File("plugins/FoE/uzivatele/" + jmenoHrace + ".yml");
			uziv = YamlConfiguration.loadConfiguration(uzivFile);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void Upgrade() {
		try {
			File nahrano = new File("plugins/Nahrano/config.yml"), cestinator = new File("plugins/Cestinator/config.yml"), antireklama = new File("plugins/AntiReklama/config.yml"), verejnazprava = new File("plugins/VerejnaZprava/config.yml"), vypnoutchat = new File("plugins/VypnoutChat/config.yml");
			String nahranoDir = "plugins/Nahrano/", cestinatorDir = "plugins/Cestinator/", antireklamaDir = "plugins/AntiReklama/", verejnazpravaDir = "plugins/VerejnaZprava/", vypnoutchatDir = "plugins/VypnoutChat/";
			Boolean b = false, c = false, d = false, a = false;
			if (nahrano.exists()) {
				YamlConfiguration aa = YamlConfiguration.loadConfiguration(nahrano);
				for (String hrac : aa.getConfigurationSection("Nahrano").getKeys(false)) {
					Long cas = aa.getLong("Nahrano." + hrac);
					uzivatel(hrac);
					if (!uzivFile.exists()) {
						uziv.set("Nahrano", cas);
						System.out.println(hrac + " - " + cas);
						saveConfig(config, configFile);
					} else {
						Long nahr = Long.valueOf(uziv.getLong("Nahrano"));
						uziv.set("Nahrano", nahr + cas);
						System.out.println(hrac + " - " + nahr + cas);
						saveConfig(config, configFile);
					}
				}
				System.out.println("Upgrade: Nahrano - Hotovo");
				b = true;
				deleteFolder(nahrano);
				DeleteFileFolder(nahranoDir);
			} else {
				b = true;
			}
			if (b) {
				if (cestinator.exists()) {
					YamlConfiguration aa = YamlConfiguration.loadConfiguration(cestinator);
					List<String> m = new ArrayList<String>();
					List<String> n = new ArrayList<String>();
					List<String> o = new ArrayList<String>();
					for (String l : aa.getStringList("Slova.vsude")) {
						m.add(l);
						System.out.println(l);
					}
					for (String l : aa.getStringList("Slova.cele")) {
						n.add(l);
						System.out.println(l);
					}
					for (String l : aa.getStringList("Cenzura.slova")) {
						o.add(l);
						System.out.println(l);
					}
					config.set("Gramatika.Vsude", m);
					config.set("Gramatika.Cele", n);
					config.set("Cenzura.slova", o);
					saveConfig(config, configFile);
					System.out.println("Upgrade: Gramatika, Cenzura - Hotovo");
					c = true;
					deleteFolder(cestinator);
					DeleteFileFolder(cestinatorDir);
				} else {
					c = true;
				}
			}
			if (c) {
				if (antireklama.exists()) {
					YamlConfiguration aa = YamlConfiguration.loadConfiguration(antireklama);
					List<String> n = new ArrayList<String>();
					List<String> m = new ArrayList<String>();
					for (String l : aa.getStringList("IP.povoleno")) {
						n.add(l);
						System.out.println(l);
					}
					for (String l : aa.getStringList("WEB.povoleno")) {
						m.add(l);
						System.out.println(l);
					}
					config.set("AntiReklama.WEB.Zprava", aa.getString("WEB.verejnaZprava"));
					config.set("AntiReklama.WEB.Akce", aa.getString("WEB.akce"));
					config.set("AntiReklama.WEB.Whitelist", m);
					config.set("AntiReklama.IP.Akce", aa.getString("IP.akce"));
					config.set("AntiReklama.IP.Zprava", aa.getString("IP.verejnaZprava"));
					config.set("AntiReklama.IP.Whitelist", n);
					saveConfig(config, configFile);
					System.out.println("Upgrade: AntiReklama - Hotovo");
					d = true;
					deleteFolder(antireklama);
					DeleteFileFolder(antireklamaDir);
				} else {
					d = true;
				}
			}
			if (d) {
				if (verejnazprava.exists()) {
					YamlConfiguration aa = YamlConfiguration.loadConfiguration(verejnazprava);
					config.set("Oznameni.Prefix", aa.getString("Prefix"));
					config.set("Oznameni.Suffix", aa.getString("Suffix"));
					saveConfig(config, configFile);
					System.out.println("Upgrade: Oznameni - Hotovo");
					a = true;
					deleteFolder(verejnazprava);
					DeleteFileFolder(verejnazpravaDir);
				} else {
					a = true;
				}
			}
			if (a) {
				if (vypnoutchat.exists()) {
					YamlConfiguration aa = YamlConfiguration.loadConfiguration(vypnoutchat);
					config.set("VypnoutChat.KdyzJeVypnutyChat", aa.getString("Zpravy.Chat"));
					saveConfig(config, configFile);
					System.out.println("Upgrade: VypnoutChat - Hotovo");
					deleteFolder(vypnoutchat);
					DeleteFileFolder(vypnoutchatDir);
				}
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	private void delete(File file) {
		if (file.isDirectory()) {
			String[] fileList = file.list();
			if (fileList.length == 0) {
				file.delete();
			} else {
				int size = fileList.length;
				for (int i = 0; i < size; i++) {
					String fileName = fileList[i];
					String fullPath = file.getPath() + "/" + fileName;
					File fileOrFolder = new File(fullPath);
					delete(fileOrFolder);
				}
			}
		} else {
			file.delete();
		}
	}
	
	public void DeleteFileFolder(String path) {
		File file = new File(path);
		if (file.exists()) {
			do
				delete(file);
			while (file.exists());
		}
	}
	
	public void deleteFolder(File folder) {
		try {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isDirectory())
						deleteFolder(f);
					else {
						f.delete();
					}
				}
			}
			folder.delete();
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void kontrolaConfigu() {
		try {
			
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void helpMessageToLog(String playerName, String Message) {
		try {
			File u = new File("plugins/FoE/help.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("# " + playerName + " - " + time + "\n" + Message + "\n");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String nahraditMezery(String zCeho) {
		return zCeho = zCeho.replaceAll(" ", "_");
	}
	
	public void statistiky() {
		try {
			String serverName = Bukkit.getServerName();
			String name;
			if (!config.contains("NM")) {
				if (serverName.equals("Unknown Server")) {
					Random rnd = new Random();
					config.set("NM", rnd.nextInt(10000));
					saveConfig(config, configFile);
				} else {
					config.set("NM", serverName);
					saveConfig(config, configFile);
				}
			}
			name = config.getString("NM");
			URL url = new URL("http://www.foe.frelania.eu/servers/post.php?ip=" + this.getServer().getIp() + "&port=" + this.getServer().getPort() + "&jmeno=" + nahraditMezery(name) + "&verze=" + this.getDescription().getVersion());
			if (url != null) {
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.connect();
				OutputStreamWriter w = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				w.write("ip=" + URLEncoder.encode(this.getServer().getIp(), "UTF-8") + "&port=" + URLEncoder.encode(String.valueOf(this.getServer().getPort()), "UTF-8") + "&jmeno=" + URLEncoder.encode(nahraditMezery(name), "UTF-8") + "&verze=" + URLEncoder.encode(this.getDescription().getVersion(), "UTF-8"));
				w.flush();
				BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				w.close();
				rd.close();
			}
		} catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public void zkontrolovatVerziPluginu() {
		try {
			URL url = new URL("http://www.frelania.eu/MyImages/FoE.txt");
			URLConnection connection = url.openConnection();
			
			if (connection.getContentLength() == -1) {
				System.out.println("Neni pripojeni k internetu, moznost overit novou verzi je nemozne.");
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://www.frelania.eu/MyImages/FoE.txt").openStream()));
				String newVersion = br.readLine();
				String nowVersion = getDescription().getVersion();
				String newVersionWithoutDots = removeDots(newVersion);
				String nowVersionWithoutDots = removeDots(nowVersion);
				int intNew = Integer.valueOf(newVersionWithoutDots).intValue();
				int intNow = Integer.valueOf(nowVersionWithoutDots).intValue();
				BufferedReader br2 = new BufferedReader(new InputStreamReader(new URL("http://www.frelania.eu/MyImages/FoEv" + intNew + ".txt").openStream(), "UTF8"));
				if (intNew > intNow) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.isOp()) {
							p.sendMessage("FoE - Je dostupná nová verze " + ChatColor.RED + newVersion);
							p.sendMessage(nahraditBarvy(br2.readLine().split("\\n").toString()));
						}
					}
					System.out.println("FoE - Je dostupna nova verze " + newVersion);
				}
				br.close();
			}
		} catch (IOException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public String nahraditBarvy(String zprava) {
		return zprava = zprava.replaceAll("(&([a-fk-or0-9]))", "§$2");
	}
	
	public String removeDots(String fromWhat) {
		fromWhat = fromWhat.replace(".", "");
		return fromWhat;
	}
	
	public String nahradit(String zprava, String hrac) {
		try {
			uzivatel(hrac);
			if (zprava.matches(".*\\{JMENO}.*")) {
				zprava = zprava.replaceAll("\\{JMENO}", hrac);
			}
			zprava = zprava.replaceAll("(&([a-fk-or0-9]))", "§$2");
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
		return zprava;
	}
	
	public String replaceAutoMessage(String message, String playerName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		
		if (message.matches(".*\\{PREFIX}.*"))
			message = message.replaceAll("\\{PREFIX}", config.getString("autoZpravy.Prefix"));
		
		if (message.matches(".*\\{CAS}.*")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(date);
			message = message.replaceAll("\\{CAS}", time);
		}
		
		if (message.matches(".*\\{SVET}.*"))
			message = message.replaceAll("\\{SVET}", Bukkit.getPlayer(playerName).getLocation().getWorld().getName());
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public void minute5() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				minutesLeft5 -= 1;
				if (minutesLeft5 > 0) {
					minute5();
				}
				
				if (minutesLeft5 == 0) {
					try {
						List<String> list = config.getStringList("autoZpravy.Zpravy");
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (!p.hasPermission("FoE.AutoZpravy.Bypass")) {
								Random rnd = new Random();
								p.sendMessage(replaceAutoMessage(list.get(rnd.nextInt(list.size())), p.getName()));
							}
						}
						startLoop5(autoZpravyInterval);
					} catch (Exception e) {
						Writer writer = new StringWriter();
						PrintWriter printWriter = new PrintWriter(writer);
						e.printStackTrace(printWriter);
						Error(writer.toString());
					}
				}
			}
		}, 20L);
	}
	
	public void startLoop5(int length) {
		minutesLeft5 = length;
		minute5();
	}
	
	public void saveConfig(YamlConfiguration config, File configFile) {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean Status(YamlConfiguration config, String node) {
		if (node != null && config != null) {
			if (debug)
				System.out.println(node);
			if (config.getString(node).equalsIgnoreCase("ano"))
				return true;
		} else {
			System.out.println(node + " nebyl nalezen, aktualizujte config.");
		}
		return false;
	}
	
	@EventHandler
	public void onPlayerPreProcessCommand(PlayerCommandPreprocessEvent event) {
		String vysledek = "";
		String args[] = event.getMessage().split(" ");
		for (int i = 1; i < args.length; i++) {
			vysledek = (vysledek + (i > 1 ? " " : "") + args[i]);
		}
		
		if (args[0].equalsIgnoreCase(config.getString("Prikazy.AdminChat"))) {
			if (adminChatPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "acmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Manager.Ban"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "bancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Manager.Unban"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "unbancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Manager.Kick"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "kickcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Cenzura"))) {
			if (cenzuraPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "cenzuracmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Gramatika"))) {
			if (gramatikaPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "gramatikacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Help"))) {
			if (zpravaAdminum) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "helpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.VypnoutChat"))) {
			if (vypnoutChatPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "chatcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Nahranost"))) {
			if (nahranostPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "infcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Inventar"))) {
			if (inventarPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "invcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Teleport"))) {
			if (teleportPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "tpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Oznameni"))) {
			if (oznameniPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "zpravacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Clear"))) {
			if (clearChat) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "clearcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Vtip"))) {
			if (vtipyPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "vtipcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Warp"))) {
			if (warpPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "warpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Msg"))) {
			if (msgPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "msgcmd " + vysledek);
				event.setCancelled(true);
			}
		}
		// Pøíprava na Žádost #017.
		/*else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Creative"))) {
			Bukkit.getServer().dispatchCommand(event.getPlayer(), "creativecmd " + vysledek);
			event.setCancelled(true);
			} else if (args[0].equalsIgnoreCase(config.getString("Prikazy.Survival"))) {
			Bukkit.getServer().dispatchCommand(event.getPlayer(), "survivalcmd " + vysledek);
			event.setCancelled(true);
			}*/
	}
}
