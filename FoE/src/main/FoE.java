package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
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
		fm = new FeaturesManager(cm, plugin, mysql);
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
			err.postError(e);
		}
	}
	
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
	
	public void Upgrade() {
		try {
			File nahrano = new File("plugins/Nahrano/cm.config.yml"), cestinator = new File("plugins/Cestinator/cm.config.yml"), antireklama = new File("plugins/AntiReklama/cm.config.yml"), verejnazprava = new File("plugins/VerejnaZprava/cm.config.yml"), vypnoutchat = new File("plugins/VypnoutChat/cm.config.yml");
			String nahranoDir = "plugins/Nahrano/", cestinatorDir = "plugins/Cestinator/", antireklamaDir = "plugins/AntiReklama/", verejnazpravaDir = "plugins/VerejnaZprava/", vypnoutchatDir = "plugins/VypnoutChat/";
			Boolean b = false, c = false, d = false, a = false;
			if (nahrano.exists()) {
				YamlConfiguration aa = YamlConfiguration.loadConfiguration(nahrano);
				for (String player : aa.getConfigurationSection("Nahrano").getKeys(false)) {
					Long cas = aa.getLong("Nahrano." + player);
					PlayerManager pm = new PlayerManager(player);
					pm.loadPlayer();
					if (!pm.uzivFile.exists()) {
						pm.uziv.set("Nahrano", cas);
						System.out.println(player + " - " + cas);
						pm.saveUser();
					} else {
						Long nahr = Long.valueOf(pm.getPlayerPlayedTime());
						pm.uziv.set("Nahrano", nahr + cas);
						System.out.println(player + " - " + nahr + cas);
						pm.saveUser();
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
					cm.config.set("Gramatika.Vsude", m);
					cm.config.set("Gramatika.Cele", n);
					cm.config.set("Cenzura.slova", o);
					cm.saveConfig(cm.config, cm.configFile);
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
					cm.config.set("AntiReklama.WEB.Zprava", aa.getString("WEB.verejnaZprava"));
					cm.config.set("AntiReklama.WEB.Akce", aa.getString("WEB.akce"));
					cm.config.set("AntiReklama.WEB.Whitelist", m);
					cm.config.set("AntiReklama.IP.Akce", aa.getString("IP.akce"));
					cm.config.set("AntiReklama.IP.Zprava", aa.getString("IP.verejnaZprava"));
					cm.config.set("AntiReklama.IP.Whitelist", n);
					cm.saveConfig(cm.config, cm.configFile);
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
					cm.config.set("Oznameni.Prefix", aa.getString("Prefix"));
					cm.config.set("Oznameni.Suffix", aa.getString("Suffix"));
					cm.saveConfig(cm.config, cm.configFile);
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
					cm.config.set("VypnoutChat.KdyzJeVypnutyChat", aa.getString("Zpravy.Chat"));
					cm.saveConfig(cm.config, cm.configFile);
					System.out.println("Upgrade: VypnoutChat - Hotovo");
					deleteFolder(vypnoutchat);
					DeleteFileFolder(vypnoutchatDir);
				}
			}
		} catch (Exception e) {
			err.postError(e);
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
			err.postError(e);
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
							p.sendMessage("FoE - Je dostupna nova verze " + ChatColor.RED + newVersion);
							p.sendMessage(replace.Colors(br2.readLine().split("\\n").toString()));
						}
					}
					System.out.println("FoE - Je dostupna nova verze " + newVersion);
				}
				br.close();
			}
		} catch (IOException e) {
			err.postError(e);
		}
	}
	
	public String removeDots(String fromWhat) {
		fromWhat = fromWhat.replace(".", "");
		return fromWhat;
	}
}
