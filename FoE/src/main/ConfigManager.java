package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	
	public File					configFile		= new File("plugins/FoE/config.yml");
	public YamlConfiguration	config			= YamlConfiguration.loadConfiguration(configFile);
	public File					umrtiZpravyFile	= new File("plugins/FoE/umrtiZpravy.yml");
	public YamlConfiguration	umrtiZpravy		= YamlConfiguration.loadConfiguration(umrtiZpravyFile);
	public boolean				loaded			= false;
	
	public void checkConfig() {
		if (!config.contains("debug"))
			config.set("debug", false);
		
		if (!config.contains("Prikazy.AdminChat"))
			config.set("Prikazy.AdminChat", "/a");
		
		if (!config.contains("Prikazy.Manager.Ban"))
			config.set("Prikazy.Manager.Ban", "/ban");
		
		if (!config.contains("Prikazy.Manager.Unban"))
			config.set("Prikazy.Manager.Unban", "/unban");
		
		if (!config.contains("Prikazy.Manager.Kick"))
			config.set("Prikazy.Manager.Kick", "/kick");
		
		if (!config.contains("Prikazy.Cenzura"))
			config.set("Prikazy.Cenzura", "/cenzura");
		
		if (!config.contains("Prikazy.Gramatika"))
			config.set("Prikazy.Gramatika", "/gramatika");
		
		if (!config.contains("Prikazy.Help"))
			config.set("Prikazy.Help", "/help");
		
		if (!config.contains("Prikazy.VypnoutChat"))
			config.set("Prikazy.VypnoutChat", "/chat");
		
		if (!config.contains("Prikazy.Nahranost"))
			config.set("Prikazy.Nahranost", "/inf");
		
		if (!config.contains("Prikazy.Inventar"))
			config.set("Prikazy.Inventar", "/inv");
		
		if (!config.contains("Prikazy.Teleport"))
			config.set("Prikazy.Teleport", "/tp");
		
		if (!config.contains("Prikazy.Oznameni"))
			config.set("Prikazy.Oznameni", "/zprava");
		
		if (!config.contains("Prikazy.Clear"))
			config.set("Prikazy.Clear", "/clear");
		
		if (!config.contains("Prikazy.Vtip"))
			config.set("Prikazy.Vtip", "/vtip");
		
		if (!config.contains("Prikazy.Warp"))
			config.set("Prikazy.Warp", "/warp");
		
		if (!config.contains("Prikazy.Msg"))
			config.set("Prikazy.Msg", "/msg");
		
		if (!config.contains("Prikazy.Creative"))
			config.set("Prikazy.Creative", "/c");
		
		if (!config.contains("Prikazy.Survival"))
			config.set("Prikazy.Survival", "/s");
		
		if (!config.contains("MySQL.Povolit"))
			config.set("MySQL.Povolit", "ne");
		
		if (!config.contains("MySQL.hostname"))
			config.set("MySQL.hostname", "localhost");
		
		if (!config.contains("MySQL.database"))
			config.set("MySQL.database", "gs_xxxxx_1");
		
		if (!config.contains("MySQL.username"))
			config.set("MySQL.username", "gs_xxxxx_1");
		
		if (!config.contains("MySQL.password"))
			config.set("MySQL.password", "heslo");
		
		if (!config.contains("MySQL.port"))
			config.set("MySQL.port", 3306);
		
		if (!config.contains("MySQL.Cas"))
			config.set("MySQL.Cas", 30);
		
		if (!config.contains("Oznameni.Povolit"))
			config.set("Oznameni.Povolit", "ano");
		
		if (!config.contains("Oznameni.Prefix"))
			config.set("Oznameni.Prefix", "&8[&4FoE&8]");
		
		if (!config.contains("Oznameni.Suffix"))
			config.set("Oznameni.Suffix", "&4");
		
		if (!config.contains("KdyzHracSe.Pripoji.Povolit"))
			config.set("KdyzHracSe.Pripoji.Povolit", "ano");
		
		if (!config.contains("KdyzHracSe.Pripoji.Zprava"))
			config.set("KdyzHracSe.Pripoji.Zprava", "&4{JMENO}&8 se pripojil do hry!");
		
		if (!config.contains("KdyzHracSe.Odpoji.Povolit"))
			config.set("KdyzHracSe.Odpoji.Povolit", "ano");
		
		if (!config.contains("KdyzHracSe.Odpoji.Zprava"))
			config.set("KdyzHracSe.Odpoji.Zprava", "&4{JMENO}&8 se odpojil ze hry!");
		
		if (!config.contains("KdyzHracSe.Vyhodi.Povolit"))
			config.set("KdyzHracSe.Vyhodi.Povolit", "ano");
		
		if (!config.contains("KdyzHracSe.Vyhodi.Zprava"))
			config.set("KdyzHracSe.Vyhodi.Zprava", "&4{JMENO}&8 byl vyhozen!");
		
		if (!config.contains("uvitaciZprava.Povolit")) {
			config.set("uvitaciZprava.Povolit", "ano");
		}
		
		if (!config.contains("uvitaciZprava.Zprava")) {
			config.set("uvitaciZprava.Zprava", "&4Vitej {JMENO}&8 doufame ze se ti u nas na serveru bude libit.");
		}
		
		if (!config.contains("Nahranost.Povolit"))
			config.set("Nahranost.Povolit", "ano");
		
		if (!config.contains("Nahranost.PrivitaciZprava.Povolit"))
			config.set("Nahranost.PrivitaciZprava.Povolit", "ano");
		
		if (!config.contains("Nahranost.Zprava"))
			config.set("Nahranost.Zprava", "&4{JMENO}&8 na serveru jste nahral &4{TYDEN}&8 tydnu, &4{DEN} &8dnu, &4{HODIN} &8hodin, &4{MINUT} &8minut, &4{SEKUND}&8 sekund");
		
		if (!config.contains("AntiReklama.Povolit"))
			config.set("AntiReklama.Povolit", "ano");
		
		if (!config.contains("AntiReklama.WEB.Zprava"))
			config.set("AntiReklama.WEB.Zprava", "&4{JMENO}&8 byl banovan za reklamu na web!");
		
		if (!config.contains("AntiReklama.WEB.Akce"))
			config.set("AntiReklama.WEB.Akce", "");
		
		if (!config.contains("AntiReklama.WEB.Whitelist")) {
			List<String> d = new ArrayList<String>();
			d.add("www.frelania.eu");
			config.set("AntiReklama.WEB.Whitelist", d);
		}
		
		if (!config.contains("AntiReklama.IP.Akce"))
			config.set("AntiReklama.IP.Akce", "");
		
		if (!config.contains("AntiReklama.IP.Zprava"))
			config.set("AntiReklama.IP.Zprava", "&4{JMENO}&8 byl IPbanovan za reklamu!");
		
		if (!config.contains("AntiReklama.IP.Whitelist")) {
			List<String> e = new ArrayList<String>();
			e.add("93.91.250.111:27887");
			config.set("AntiReklama.IP.Whitelist", e);
		}
		
		if (!config.contains("Cenzura.Povolit"))
			config.set("Cenzura.Povolit", "ano");
		
		if (!config.contains("Cenzura.Nahrada"))
			config.set("Cenzura.Nahrada", "******");
		
		if (!config.contains("Cenzura.Zprava"))
			config.set("Cenzura.Zprava", "{JMENO} nadavat se zde nesmi!");
		
		if (!config.contains("Cenzura.Akce"))
			config.set("Cenzura.Akce", "");
		
		if (!config.contains("Cenzura.Slova")) {
			List<String> a = new ArrayList<String>();
			a.add("debil");
			a.add("kokot");
			a.add("curak");
			config.set("Cenzura.Slova", a);
		}
		
		if (!config.contains("Gramatika.Povolit"))
			config.set("Gramatika.Povolit", "ano");
		
		if (!config.contains("Gramatika.Duvody"))
			config.set("Gramatika.Duvody.mislet", "&4Vyjmenovane Slovo - Vzdycky tvrde Y! ' m&fY&4slet '");
		
		if (!config.contains("Gramatika.Vsude")) {
			List<String> b = new ArrayList<String>();
			b.add("kdiz,kdyz");
			b.add("mislet,myslet");
			config.set("Gramatika.Vsude", b);
		}
		
		if (!config.contains("Gramatika.Cele")) {
			List<String> c = new ArrayList<String>();
			c.add("us,uz");
			config.set("Gramatika.Cele", c);
		}
		
		if (!config.contains("VypnoutChat.Povolit"))
			config.set("VypnoutChat.Povolit", "ano");
		
		if (!config.contains("VypnoutChat.KdyzJeVypnutyChat"))
			config.set("VypnoutChat.KdyzJeVypnutyChat", "&4Chat je vypnuty, opravneni psat maji jen &8operatori&4!");
		
		if (!config.contains("VypnoutChat.KdyzSeVypne"))
			config.set("VypnoutChat.KdyzSeVypne", "&4{JMENO} &8zakazal chat!");
		
		if (!config.contains("VypnoutChat.KdyzSeZapne"))
			config.set("VypnoutChat.KdyzSeZapne", "&4{JMENO} &8povolil chat!");
		
		if (!config.contains("Ostatni.KdyzNemaOpravneni"))
			config.set("Ostatni.KdyzNemaOpravneni", "&4Na tuto akci nemate &8opravneni&4!");
		
		if (!config.contains("Ostatni.Upgrade"))
			config.set("Ostatni.Upgrade", "ano");
		
		if (!config.contains("VyhledavatAktualizace.Povolit"))
			config.set("VyhledavatAktualizace.Povolit", "ano");
		
		if (!config.contains("VyhledavatAktualizace.Cas"))
			config.set("VyhledavatAktualizace.Cas", 10);
		
		if (!config.contains("AdminChat.Povolit"))
			config.set("AdminChat.Povolit", "ano");
		
		if (!config.contains("AdminChat.Zprava"))
			config.set("AdminChat.Zprava", "&8[&4AdminChat&8] &e{JMENO}:&4{ZPRAVA}");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Povolit"))
			config.set("Ostatni.Nahranost.GUI.Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Tydny-Povolit"))
			config.set("Ostatni.Nahranost.GUI.Tydny-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Dny-Povolit"))
			config.set("Ostatni.Nahranost.GUI.Dny-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Hodiny-Povolit"))
			config.set("Ostatni.Nahranost.GUI.Hodiny-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.PocetHracu-Povolit"))
			config.set("Ostatni.Nahranost.GUI.PocetHracu-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoHracu-Povolit"))
			config.set("Ostatni.Nahranost.GUI.ZabitoHracu-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoMobu-Povolit"))
			config.set("Ostatni.Nahranost.GUI.ZabitoMobu-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoZvirat-Povolit"))
			config.set("Ostatni.Nahranost.GUI.ZabitoZvirat-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.PocetSmrti-Povolit"))
			config.set("Ostatni.Nahranost.GUI.PocetSmrti-Povolit", "ano");
		
		if (!config.contains("Ostatni.Nahranost.GUI.iConomy-Povolit"))
			config.set("Ostatni.Nahranost.GUI.iConomy-Povolit", "ne");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Nadpis"))
			config.set("Ostatni.Nahranost.GUI.Nadpis", "&4F&8o&4E");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Tydny"))
			config.set("Ostatni.Nahranost.GUI.Tydny", "Nahrano Tydnu:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Dny"))
			config.set("Ostatni.Nahranost.GUI.Dny", "Nahrano Dnu:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.Hodiny"))
			config.set("Ostatni.Nahranost.GUI.Hodiny", "Nahrano Hodin:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.PocetHracu"))
			config.set("Ostatni.Nahranost.GUI.PocetHracu", "Online:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.iConomy"))
			config.set("Ostatni.Nahranost.GUI.iConomy", "Penize:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoHracu"))
			config.set("Ostatni.Nahranost.GUI.ZabitoHracu", "Zabito Hracu:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoMobu"))
			config.set("Ostatni.Nahranost.GUI.ZabitoMobu", "Zabito Mobu:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.ZabitoZvirat"))
			config.set("Ostatni.Nahranost.GUI.ZabitoZvirat", "Zabito Zvirat:");
		
		if (!config.contains("Ostatni.Nahranost.GUI.PocetSmrti"))
			config.set("Ostatni.Nahranost.GUI.PocetSmrti", "Umrti:");
		
		if (!config.contains("TP.Povolit"))
			config.set("TP.Povolit", "ano");
		
		if (!config.contains("TP.Zprava.Uspesne"))
			config.set("TP.Zprava.Uspesne", "&4uspesne &8jste se teleportoval k &4{JMENO}");
		
		if (!config.contains("TP.Zprava.Offline"))
			config.set("TP.Zprava.Offline", "&4Hrac {JMENO} &8neni online! Zkusime najit jeho posledni pozici.");
		
		if (!config.contains("TP.Zprava.NeniZaznamenan"))
			config.set("TP.Zprava.NeniZaznamenan", "&8Litujeme ale &4{JMENO}&8 neni zde zaznamenan.");
		
		if (!config.contains("AntiSpam.Povolit"))
			config.set("AntiSpam.Povolit", "ano");
		
		if (!config.contains("AntiSpam.Zprava"))
			config.set("AntiSpam.Zprava", "&4{JMENO} &8Musite pockat &4{SEKUND} &8sekundy.");
		
		if (!config.contains("AntiSpam.PockatSekund"))
			config.set("AntiSpam.PockatSekund", 3);
		
		if (!config.contains("AntiSpam.Duplikace.Povolit"))
			config.set("AntiSpam.Duplikace.Povolit", "ano");
		
		if (!config.contains("AntiSpam.Duplikace.Zprava"))
			config.set("AntiSpam.Duplikace.Zprava", "&4Nemuzete poslat 2x stejnou zpravu&8!");
		
		if (!config.contains("Rezervace.Povolit"))
			config.set("Rezervace.Povolit", "ano");
		
		if (!config.contains("Rezervace.Zprava"))
			config.set("Rezervace.Zprava", "&4{JMENO} &8se pripojil a vy jste byl vyhozen ze hry.");
		
		if (!config.contains("Inventar.Povolit"))
			config.set("Inventar.Povolit", "ano");
		
		if (!config.contains("Manager.Povolit"))
			config.set("Manager.Povolit", "ano");
		
		if (!config.contains("Manager.Ban.Zprava"))
			config.set("Manager.Ban.Zprava", "&4{TARGET} &8byl zabanovan &4{JMENO}&8 z duvodu &4{DUVOD}&8.");
		
		if (!config.contains("Manager.Unban.Zprava"))
			config.set("Manager.Unban.Zprava", "&4{TARGET} &8byl unbanovan &4{JMENO}&8 z duvodu &4{DUVOD}&8.");
		
		if (!config.contains("Manager.Kick.Zprava"))
			config.set("Manager.Kick.Zprava", "&4{TARGET} &8byl vyhozen &4{JMENO}&8 z duvodu &4{DUVOD}&8.");
		
		if (!config.contains("Vtipy.Povolit"))
			config.set("Vtipy.Povolit", "ano");
		
		if (!config.contains("Vtipy.Interval"))
			config.set("Vtipy.Interval", 1);
		
		if (!config.contains("Vtipy.Format"))
			config.set("Vtipy.Format", "&e{VTIP}&f");
		
		if (!config.contains("zpravaAdminum.Povolit"))
			config.set("zpravaAdminum.Povolit", "ano");
		
		if (!config.contains("capsLock.Povolit"))
			config.set("capsLock.Povolit", "ano");
		
		if (!config.contains("capsLock.Zprava"))
			config.set("capsLock.Zprava", "&4Zachovej klidnou hlavu a pis malima pismenkama.");
		
		if (!config.contains("capsLock.Akce"))
			config.set("capsLock.Akce", "");
		
		if (!config.contains("clearChat.Povolit"))
			config.set("clearChat.Povolit", "ano");
		
		if (!config.contains("umrtiZpravy.Povolit"))
			config.set("umrtiZpravy.Povolit", "ano");
		
		if (!umrtiZpravy.contains("Sebevrazda.BLOCK_EXPLOSION"))
			umrtiZpravy.set("Sebevrazda.BLOCK_EXPLOSION", "&4{JMENO} &8zabil vybuch.");
		
		if (!umrtiZpravy.contains("Sebevrazda.DROWNING"))
			umrtiZpravy.set("Sebevrazda.DROWNING", "&4{JMENO} &8se utopil.");
		
		if (!umrtiZpravy.contains("Sebevrazda.FALL"))
			umrtiZpravy.set("Sebevrazda.FALL", "&4{JMENO} &8spadl z vysky.");
		
		if (!umrtiZpravy.contains("Sebevrazda.FALLING_BLOCK"))
			umrtiZpravy.set("Sebevrazda.FALLING_BLOCK", "&4{JMENO} &8zavalily bloky.");
		
		if (!umrtiZpravy.contains("Sebevrazda.FIRE"))
			umrtiZpravy.set("Sebevrazda.FIRE", "&4{JMENO} &8uhorel.");
		
		if (!umrtiZpravy.contains("Sebevrazda.FIRE_TICK"))
			umrtiZpravy.set("Sebevrazda.FIRE_TICK", "&4{JMENO} &8uhorel.");
		
		if (!umrtiZpravy.contains("Sebevrazda.LAVA"))
			umrtiZpravy.set("Sebevrazda.LAVA", "&4{JMENO} &8spadl do lavy.");
		
		if (!umrtiZpravy.contains("Sebevrazda.LIGHTNING"))
			umrtiZpravy.set("Sebevrazda.LIGHTNING", "&4{JMENO} &8zabil blesk.");
		
		if (!umrtiZpravy.contains("Sebevrazda.MAGIC"))
			umrtiZpravy.set("Sebevrazda.MAGIC", "&4{JMENO} &8zabila magie.");
		
		if (!umrtiZpravy.contains("Sebevrazda.MELTING"))
			umrtiZpravy.set("Sebevrazda.MELTING", "&4{JMENO} &8se roztal.");
		
		if (!umrtiZpravy.contains("Sebevrazda.POISON"))
			umrtiZpravy.set("Sebevrazda.POISON", "&4{JMENO} &8se otravil.");
		
		if (!umrtiZpravy.contains("Sebevrazda.PROJECTILE"))
			umrtiZpravy.set("Sebevrazda.PROJECTILE", "&4{JMENO} &8byl sestrelen.");
		
		if (!umrtiZpravy.contains("Sebevrazda.STARVATION"))
			umrtiZpravy.set("Sebevrazda.STARVATION", "&4{JMENO} &8umrel hlady.");
		
		if (!umrtiZpravy.contains("Sebevrazda.SUFFOCATION"))
			umrtiZpravy.set("Sebevrazda.SUFFOCATION", "&4{JMENO} &8se udusil.");
		
		if (!umrtiZpravy.contains("Sebevrazda.SUICIDE"))
			umrtiZpravy.set("Sebevrazda.SUICIDE", "&4{JMENO} &8spachal sebevrazdu.");
		
		if (!umrtiZpravy.contains("Sebevrazda.VOID"))
			umrtiZpravy.set("Sebevrazda.VOID", "&4{JMENO} &8spadl do nicoty.");
		
		if (!umrtiZpravy.contains("Sebevrazda.WITHER"))
			umrtiZpravy.set("Sebevrazda.WITHER", "&4{JMENO} &8zemrel witherem.");
		
		if (!umrtiZpravy.contains("Monsters.Creeper"))
			umrtiZpravy.set("Monsters.Creeper", "&4{JMENO} &8byl zabit &4sycakem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Zombie"))
			umrtiZpravy.set("Monsters.Zombie", "&4{JMENO} &8byl zabit &4zombikem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Skeleton"))
			umrtiZpravy.set("Monsters.Skeleton", "&4{JMENO} &8byl zabit &4kostlivcem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Spider"))
			umrtiZpravy.set("Monsters.Spider", "&4{JMENO} &8byl zabit &4pavoukem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Wither"))
			umrtiZpravy.set("Monsters.Wither", "&4{JMENO} &8byl zabit &4witherem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Wolf"))
			umrtiZpravy.set("Monsters.Wolf", "&4{JMENO} &8byl zabit &4vlkem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Ghast"))
			umrtiZpravy.set("Monsters.Ghast", "&4{JMENO} &8byl zabit &4ghastem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Explosive"))
			umrtiZpravy.set("Monsters.Explosive", "&4{JMENO} &8byl zabit &4explozi&8.");
		
		if (!umrtiZpravy.contains("Monsters.PigZombie"))
			umrtiZpravy.set("Monsters.PigZombie", "&4{JMENO} &8byl zabit &4prasecim zombikem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Slime"))
			umrtiZpravy.set("Monsters.Slime", "&4{JMENO} &8byl zabit &4slizounem&8.");
		
		if (!umrtiZpravy.contains("Monsters.SmallFireball"))
			umrtiZpravy.set("Monsters.SmallFireball", "&4{JMENO} &8byl zabit &4ohnivou kouli&8.");
		
		if (!umrtiZpravy.contains("Monsters.Witch"))
			umrtiZpravy.set("Monsters.Witch", "&4{JMENO} &8byl zabit &4witchem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Enderman"))
			umrtiZpravy.set("Monsters.Enderman", "&4{JMENO} &8byl zabit &4endrmenem&8.");
		
		if (!umrtiZpravy.contains("Monsters.EnderDragon"))
			umrtiZpravy.set("Monsters.EnderDragon", "&4{JMENO} &8byl zabit &4drakem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Blaze"))
			umrtiZpravy.set("Monsters.Blaze", "&4{JMENO} &8byl zabit &4blazem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Silverfish"))
			umrtiZpravy.set("Monsters.Silverfish", "&4{JMENO} &8byl zabit &4silverfishem&8.");
		
		if (!umrtiZpravy.contains("Monsters.Giant"))
			umrtiZpravy.set("Monsters.Giant", "&4{JMENO} &8byl zabit &4giantem&8.");
		
		if (!umrtiZpravy.contains("Monsters.CaveSpider"))
			umrtiZpravy.set("Monsters.CaveSpider", "&4{JMENO} &8byl zabit &4pavoukem&8.");
		
		if (!config.contains("whiteList.Povolit"))
			config.set("whiteList.Povolit", "ano");
		
		if (!config.contains("whiteList.Zprava"))
			config.set("whiteList.Zprava", "&4Nejste na Whitelistu!!");
		
		if (!config.contains("autoZpravy.Povolit"))
			config.set("autoZpravy.Povolit", "ano");
		
		if (!config.contains("autoZpravy.Interval"))
			config.set("autoZpravy.Interval", 60);
		
		if (!config.contains("autoZpravy.Prefix"))
			config.set("autoZpravy.Prefix", "&8[&4FoE&8]");
		
		if (!config.contains("autoZpravy.Zpravy")) {
			List<String> e = new ArrayList<String>();
			e.add("{PREFIX} {JMENO} vis ze u nas na serveru muzes umrit hlady ?");
			e.add("{PREFIX} kupte si u nas nejakou vec a podporte tim server.");
			config.set("autoZpravy.Zpravy", e);
		}
		
		if (!config.contains("Warp.Povolit"))
			config.set("Warp.Povolit", "ano");
		
		if (!config.contains("Warp.NemaOpravneni"))
			config.set("Warp.NemaOpravneni", "Nemate opravneni na tento warp!");
		
		if (!config.contains("Msg.Povolit"))
			config.set("Msg.Povolit", "ano");
		
		if (!config.contains("Msg.Zprava.jeOffline"))
			config.set("Msg.Zprava.jeOffline", "{JMENO} je offline.");
		
		if (!config.contains("Msg.Format"))
			config.set("Msg.Format", "&8[&4{JMENO}&8 -> &4{TARGET}&8]&f {ZPRAVA}");
		
		if (!config.contains("herniRezimy.Povolit"))
			config.set("herniRezimy.Povolit", "ano");
		
		if (!config.contains("herniRezimy.Zpravy.Creative"))
			config.set("herniRezimy.Zpravy.Creative", "&4{JMENO} &8zmenil svuj herni rezim na &4Creative&8.");
		
		if (!config.contains("herniRezimy.Zpravy.Survival"))
			config.set("herniRezimy.Zpravy.Survival", "&4{JMENO} &8zmenil svuj herni rezim na &4Survival&8.");
		
		if (!config.contains("herniRezimy.Zpravy.MaCreative"))
			config.set("herniRezimy.Zpravy.MaCreative", "&4Jiz&8 mas &4creative&8!");
		
		if (!config.contains("herniRezimy.Zpravy.MaSurvival"))
			config.set("herniRezimy.Zpravy.MaSurvival", "&4Jiz&8 mas &4survival&8!");
		
		saveConfig(umrtiZpravy, umrtiZpravyFile);
		saveConfig(config, configFile);
		loaded = true;
	}
	
	public void saveConfig(YamlConfiguration config, File configFile) {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
