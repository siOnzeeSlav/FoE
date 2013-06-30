package eu.frelania.foe.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	private FoE foe;
	public File configFile = new File("plugins/FoE/config.yml");
	public File deathMsgsFile = new File("plugins/FoE/smrtZpravy.yml");
	public YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	public YamlConfiguration deathMsgs = YamlConfiguration.loadConfiguration(deathMsgsFile);
	public boolean loaded = false;

	public ConfigManager(FoE plugin) {
		foe = plugin;
		setVersion(foe.getDescription().getVersion());
		loadConfig();
	}

	public void loadConfig() {
		if (!config.contains("debugMode")) config.set("debugMode", false);

		if (!config.contains("prikazy.chat.adminChat")) config.set("prikazy.chat.adminChat", "/a");
		if (!config.contains("prikazy.chat.vycistitChat")) config.set("prikazy.chat.vycistitChat", "/vycistit");
		if (!config.contains("prikazy.chat.vypnoutChat")) config.set("prikazy.chat.vypnoutChat", "/chat");
		if (!config.contains("prikazy.chat.cenzura")) config.set("prikazy.chat.cenzura", "/cenzura");
		if (!config.contains("prikazy.chat.gramatika")) config.set("prikazy.chat.gramatika", "/gramatika");
		if (!config.contains("prikazy.manager.ban")) config.set("prikazy.manager.ban", "/ban");
		if (!config.contains("prikazy.manager.unban")) config.set("prikazy.manager.unban", "/unban");
		if (!config.contains("prikazy.manager.kick")) config.set("prikazy.manager.kick", "/kick");
		if (!config.contains("prikazy.help")) config.set("prikazy.help", "/help");
		if (!config.contains("prikazy.nahrano")) config.set("prikazy.nahrano", "/inf");
		if (!config.contains("prikazy.inventar")) config.set("prikazy.inventar", "/inv");
		if (!config.contains("prikazy.teleport.pozadavek")) config.set("prikazy.teleport.request", "/teleport");
		if (!config.contains("prikazy.teleport.potvrdit")) config.set("prikazy.teleport.accept", "/yop");
		if (!config.contains("prikazy.teleport.zamitnout")) config.set("prikazy.teleport.reject", "/nope");
		if (!config.contains("prikazy.oznamit")) config.set("prikazy.broadcast", "/oznamit");
		if (!config.contains("prikazy.vtip")) config.set("prikazy.joke", "/vtip");
		if (!config.contains("prikazy.warp")) config.set("prikazy.warp", "/warp");
		if (!config.contains("prikazy.msg")) config.set("prikazy.zprava", "/msg");
		if (!config.contains("prikazy.gamemode.creative")) config.set("prikazy.gamemode.creative", "/c");
		if (!config.contains("prikazy.gamemode.survival")) config.set("prikazy.gamemode.survival", "/s");

		if (!config.contains("mysql.povolit")) config.set("mysql.povolit", "ne");
		if (!config.contains("mysql.hostname")) config.set("mysql.hostname", "localhost");
		if (!config.contains("mysql.database")) config.set("mysql.database", "database");
		if (!config.contains("mysql.uzivatel")) config.set("mysql.uzivatel", "uzivatel");
		if (!config.contains("mysql.heslo")) config.set("mysql.heslo", "heslo");
		if (!config.contains("mysql.port")) config.set("mysql.port", 3306);
		if (!config.contains("mysql.intervalNacitaniZDatabaze")) config.set("mysql.intervalNacitaniZDatabaze", 30);

		if (!config.contains("oznameni.povolit")) config.set("oznameni.povolit", "ano");
		if (!config.contains("oznameni.prefix")) config.set("oznameni.prefix", "&8[&4FoE&8]");
		if (!config.contains("oznameni.suffix")) config.set("oznameni.suffix", "&4");

		if (!config.contains("kdyzSeHrac.pripoji.povolit")) config.set("kdyzSeHrac.pripoji.povolit", "ano");
		if (!config.contains("kdyzSeHrac.pripoji.zprava")) config.set("kdyzSeHrac.pripoji.zprava", "&4{NICK}&8 se pøipojil!");
		if (!config.contains("kdyzSeHrac.odpoji.povolit")) config.set("kdyzSeHrac.odpoji.povolit", "ano");
		if (!config.contains("kdyzSeHrac.odpoji.zprava")) config.set("kdyzSeHrac.odpoji.zprava", "&4{NICK}&8 se odpojil!");
		if (!config.contains("kdyzSeHrac.kick.povolit")) config.set("kdyzSeHrac.kick.povolit", "ano");
		if (!config.contains("kdyzSeHrac.kick.zprava")) config.set("kdyzSeHrac.kick.zprava", "&4{NICK}&8 byl vykopnut!");

		if (!config.contains("smrt.povolitVlastniZpravy")) config.set("smrt.povolitVlastniZpravy", "ano");
		if (!deathMsgs.contains("smrt.hrac")) deathMsgs.set("smrt.hrac", "&4{TARGET} &8byl zabit &4{JMENO} &8s predmetem &4{ITEM}&8.");
		if (!deathMsgs.contains("smrt.sebevrazda.block_explosion")) deathMsgs.set("smrt.sebevrazda.block_explosion", "&4{JMENO} &8zabil vybuch.");
		if (!deathMsgs.contains("smrt.sebevrazda.drowning")) deathMsgs.set("smrt.sebevrazda.drowning", "&4{JMENO} &8se utopil.");
		if (!deathMsgs.contains("smrt.sebevrazda.fall")) deathMsgs.set("smrt.sebevrazda.fall", "&4{JMENO} &8spadl z vysky.");
		if (!deathMsgs.contains("smrt.sebevrazda.falling_block")) deathMsgs.set("smrt.sebevrazda.falling_block", "&4{JMENO} &8zavalily bloky.");
		if (!deathMsgs.contains("smrt.sebevrazda.fire")) deathMsgs.set("smrt.sebevrazda.fire", "&4{JMENO} &8uhorel.");
		if (!deathMsgs.contains("smrt.sebevrazda.fire_tick")) deathMsgs.set("smrt.sebevrazda.fire_tick", "&4{JMENO} &8uhorel.");
		if (!deathMsgs.contains("smrt.sebevrazda.lava")) deathMsgs.set("smrt.sebevrazda.lava", "&4{JMENO} &8spadl do lavy.");
		if (!deathMsgs.contains("smrt.sebevrazda.lightning")) deathMsgs.set("smrt.sebevrazda.lightning", "&4{JMENO} &8zabil blesk.");
		if (!deathMsgs.contains("smrt.sebevrazda.magic")) deathMsgs.set("smrt.sebevrazda.magic", "&4{JMENO} &8zabila magie.");
		if (!deathMsgs.contains("smrt.sebevrazda.melting")) deathMsgs.set("smrt.sebevrazda.melting", "&4{JMENO} &8se roztal.");
		if (!deathMsgs.contains("smrt.sebevrazda.poison")) deathMsgs.set("smrt.sebevrazda.poison", "&4{JMENO} &8se otravil.");
		if (!deathMsgs.contains("smrt.sebevrazda.projectile")) deathMsgs.set("smrt.sebevrazda.projectile", "&4{JMENO} &8byl sestrelen.");
		if (!deathMsgs.contains("smrt.sebevrazda.starvation")) deathMsgs.set("smrt.sebevrazda.starvation", "&4{JMENO} &8umrel hlady.");
		if (!deathMsgs.contains("smrt.sebevrazda.suffocation")) deathMsgs.set("smrt.sebevrazda.suffocation", "&4{JMENO} &8se udusil.");
		if (!deathMsgs.contains("smrt.sebevrazda.suicide")) deathMsgs.set("smrt.sebevrazda.suicide", "&4{JMENO} &8spachal sebevrazdu.");
		if (!deathMsgs.contains("smrt.sebevrazda.viod")) deathMsgs.set("smrt.sebevrazda.viod", "&4{JMENO} &8spadl do nicoty.");
		if (!deathMsgs.contains("smrt.sebevrazda.wither")) deathMsgs.set("smrt.sebevrazda.wither", "&4{JMENO} &8zemrel witherem.");
		if (!deathMsgs.contains("smrt.monster.Creeper")) deathMsgs.set("smrt.monster.creeper", "&4{JMENO} &8byl zabit &4sycakem&8.");
		if (!deathMsgs.contains("smrt.monster.Zombie")) deathMsgs.set("smrt.monster.zombie", "&4{JMENO} &8byl zabit &4zombikem&8.");
		if (!deathMsgs.contains("smrt.monster.Skeleton")) deathMsgs.set("smrt.monster.skeleton", "&4{JMENO} &8byl zabit &4kostlivcem&8.");
		if (!deathMsgs.contains("smrt.monster.Spider")) deathMsgs.set("smrt.monster.spider", "&4{JMENO} &8byl zabit &4pavoukem&8.");
		if (!deathMsgs.contains("smrt.monster.Wither")) deathMsgs.set("smrt.monster.wither", "&4{JMENO} &8byl zabit &4witherem&8.");
		if (!deathMsgs.contains("smrt.monster.Wolf")) deathMsgs.set("smrt.monster.wolf", "&4{JMENO} &8byl zabit &4vlkem&8.");
		if (!deathMsgs.contains("smrt.monster.Ghast")) deathMsgs.set("smrt.monster.ghast", "&4{JMENO} &8byl zabit &4ghastem&8.");
		if (!deathMsgs.contains("smrt.monster.Explosive")) deathMsgs.set("smrt.monster.explosive", "&4{JMENO} &8byl zabit &4explozi&8.");
		if (!deathMsgs.contains("smrt.monster.PigZombie")) deathMsgs.set("smrt.monster.pigzombie", "&4{JMENO} &8byl zabit &4prasecim zombikem&8.");
		if (!deathMsgs.contains("smrt.monster.Slime")) deathMsgs.set("smrt.monster.slime", "&4{JMENO} &8byl zabit &4slizounem&8.");
		if (!deathMsgs.contains("smrt.monster.SmallFireball")) deathMsgs.set("smrt.monster.smallfireball", "&4{JMENO} &8byl zabit &4ohnivou kouli&8.");
		if (!deathMsgs.contains("smrt.monster.Witch")) deathMsgs.set("smrt.monster.witch", "&4{JMENO} &8byl zabit &4witchem&8.");
		if (!deathMsgs.contains("smrt.monster.Enderman")) deathMsgs.set("smrt.monster.enderman", "&4{JMENO} &8byl zabit &4endrmenem&8.");
		if (!deathMsgs.contains("smrt.monster.EnderDragon")) deathMsgs.set("smrt.monster.enderdragon", "&4{JMENO} &8byl zabit &4drakem&8.");
		if (!deathMsgs.contains("smrt.monster.Blaze")) deathMsgs.set("smrt.monster.blaze", "&4{JMENO} &8byl zabit &4blazem&8.");
		if (!deathMsgs.contains("smrt.monster.Silverfish")) deathMsgs.set("smrt.monster.silverfish", "&4{JMENO} &8byl zabit &4silverfishem&8.");
		if (!deathMsgs.contains("smrt.monster.Giant")) deathMsgs.set("smrt.monster.giant", "&4{JMENO} &8byl zabit &4obrem&8.");
		if (!deathMsgs.contains("smrt.monster.CaveSpider")) deathMsgs.set("smrt.monster.cavespider", "&4{JMENO} &8byl zabit &4pavoukem&8.");

		if (!config.contains("nahrano.povolit")) config.set("nahrano.povolit", "ano");
		if (!config.contains("nahrano.uvitaciZprava.povolit")) config.set("nahrano.uvitaciZprava.povolit", "ne");
		if (!config.contains("nahrano.uvitaciZprava.zprava")) config.set("nahrano.uvitaciZprava.zprava", "&4{JMENO}&8 na serveru jste nahral &4{TYDEN}&8 tydnu, &4{DEN} &8dnu, &4{HODIN} &8hodin, &4{MINUT} &8minut, &4{SEKUND}&8 sekund");

		if (!config.contains("chat.uvitaciZprava.povolit")) config.set("chat.uvitaciZprava.povolit", "ano");
		if (!config.contains("chat.uvitaciZprava.zprava")) {
			List<String> a = new ArrayList<String>();
			a.add("&4Vitej {JMENO}&8 doufame ze se ti u nas na serveru bude libit.");
			a.add("&8Jiz jsi zde stravil &7{NAHRANO}");
			config.set("chat.uvitaciZprava.zprava", a);
		}

		if (!config.contains("chat.vycistitChat.povolit")) config.set("chat.vycistitChat.povolit", "ano");

		if (!config.contains("chat.adminChat.povolit")) config.set("chat.adminChat.povolit", "ano");
		if (!config.contains("chat.adminChat.zprava")) config.set("chat.adminChat.zprava", "&8[&4AdminChat&8] &e{JMENO}:&4{ZPRAVA}");

		if (!config.contains("chat.antiReklama.povolit")) config.set("chat.antiReklama.povolit", "ano");
		if (!config.contains("chat.antiReklama.link.zprava")) config.set("chat.antiReklama.link.zprava", "&4{JMENO}&8 byl banovan za reklamu na web!");
		if (!config.contains("chat.antiReklama.link.provest")) config.set("chat.antiReklama.link.provest", "");
		if (!config.contains("chat.antiReklama.link.whitelist")) {
			List<String> b = new ArrayList<String>();
			b.add("www.frelania.eu");
			config.set("antiReklama.link.whitelist", b);
		}
		if (!config.contains("chat.antiReklama.ip.zprava")) config.set("chat.antiReklama.ip.zprava", "&4{JMENO}&8 byl banovan za IP reklamu!");
		if (!config.contains("chat.antiReklama.ip.provest")) config.set("chat.antiReklama.ip.provest", "");
		if (!config.contains("chat.antiReklama.ip.whitelist")) {
			List<String> c = new ArrayList<String>();
			c.add("93.91.250.111:27887");
			config.set("AntiReklama.ip.whitelist", c);
		}

		if (!config.contains("chat.antiSpam.povolit")) config.set("chat.antiSpam.povolit", "ano");
		if (!config.contains("chat.antiSpam.zprava")) config.set("chat.antiSpam.zprava", "&4{JMENO} &8Musite pockat &4{SEKUND} &8sekundy.");
		if (!config.contains("chat.antiSpam.vyckatSekund")) config.set("chat.antiSpam.vyckatSekund", 3);
		if (!config.contains("chat.antiSpam.duplikace.povolit")) config.set("chat.antiSpam.duplikace.povolit", "ano");
		if (!config.contains("chat.antiSpam.duplikace.zprava")) config.set("chat.antiSpam.duplikace.zprava", "&4Nemuzete poslat 2x stejnou zpravu&8!");

		if (!config.contains("chat.cenzura.povolit")) config.set("chat.cenzura.povolit", "ano");
		if (!config.contains("chat.cenzura.nahrada")) config.set("chat.cenzura.nahrada", "******");
		if (!config.contains("chat.cenzura.zprava")) config.set("chat.cenzura.zprava", "{JMENO} nadavat se zde nesmi!");
		if (!config.contains("chat.cenzura.provest")) config.set("chat.cenzura.provest", "");
		if (!config.contains("chat.cenzura.slovnik")) {
			List<String> d = new ArrayList<String>();
			d.add("debil");
			d.add("kokot");
			d.add("curak");
			config.set("chat.cenzura.words", d);
		}

		if (!config.contains("chat.gramatika.povolit")) config.set("chat.gramatika.povolit", "ano");
		if (!config.contains("chat.gramatika.duvody")) config.set("chat.gramatika.duvody.mislet", "&4Vyjmenovane Slovo - Vzdycky tvrde Y! ' m&fY&4slet '");
		if (!config.contains("chat.gramatika.slovnik.castecnaSlova")) {
			List<String> e = new ArrayList<String>();
			e.add("kdiz,kdyz");
			e.add("mislet,myslet");
			config.set("chat.gramatika.slovnik.castecnaSlova", e);
		}
		if (!config.contains("chat.gramatika.slovnik.celySlova")) {
			List<String> f = new ArrayList<String>();
			f.add("us,uz");
			config.set("chat.gramatika.slovnik.celySlova", f);
		}

		if (!config.contains("chat.vypnoutChat.povolit")) config.set("chat.vypnoutChat.povolit", "ano");
		if (!config.contains("chat.vypnoutChat.kdyzJeVypnut")) config.set("chat.vypnoutChat.kdyzJeVypnut", "&4Chat je vypnuty, opravneni psat maji jen &8operatori&4!");
		if (!config.contains("chat.vypnoutChat.priVypnuti")) config.set("chat.vypnoutChat.priVypnuti", "&4{JMENO} &8zakazal chat!");
		if (!config.contains("chat.vypnoutChat.priZapnuti")) config.set("chat.vypnoutChat.priZapnuti", "&4{JMENO} &8povolil chat!");

		if (!config.contains("chat.capsLock.povolit")) config.set("chat.capsLock.povolit", "ano");
		if (!config.contains("chat.capsLock.zprava")) config.set("chat.capsLock.zprava", "&4Zachovej klidnou hlavu a pis malima pismenkama.");
		if (!config.contains("chat.capsLock.provest")) config.set("chat.capsLock.provest", "");

		if (!config.contains("updater.povolit")) config.set("updater.povolit", "ano");
		if (!config.contains("updater.vyhledatAktualizace")) config.set("updater.vyhledatAktualizace", 10);

		if (!config.contains("nahrano.gui.povolit")) config.set("nahrano.gui.povolit", "ano");
		if (!config.contains("nahrano.gui.nadpis")) config.set("nahrano.gui.nadpis", "&4F&8o&4E");
		if (!config.contains("nahrano.gui.tydnu.zprava")) config.set("nahrano.gui.tydnu.zprava", "Nahrano Tydnu:");
		if (!config.contains("nahrano.gui.tydnu.zobrazit")) config.set("nahrano.gui.tydnu.zobrazit", "ano");
		if (!config.contains("nahrano.gui.dny.zprava")) config.set("nahrano.gui.dny.zprava", "Nahrano Dnu:");
		if (!config.contains("nahrano.gui.dny.zobrazit")) config.set("nahrano.gui.dny.zobrazit", "ano");
		if (!config.contains("nahrano.gui.hodiny.zprava")) config.set("nahrano.gui.hodiny.zprava", "Nahrano Hodin:");
		if (!config.contains("nahrano.gui.hodiny.zobrazit")) config.set("nahrano.gui.hodiny.zobrazit", "ano");
		if (!config.contains("nahrano.gui.pocetHracu.zobrazit")) config.set("nahrano.gui.pocetHracu.zobrazit", "ano");
		if (!config.contains("nahrano.gui.pocetHracu.zprava")) config.set("nahrano.gui.pocetHracu.zprava", "Online:");
		if (!config.contains("nahrano.gui.zabitoHracu.zobrazit")) config.set("nahrano.gui.zabitoHracu.zobrazit", "ano");
		if (!config.contains("nahrano.gui.zabitoHracu.zprava")) config.set("nahrano.gui.zabitoHracu.zprava", "Zabito Hracu:");
		if (!config.contains("nahrano.gui.zabitoMobu.zobrazit")) config.set("nahrano.gui.zabitoMobu.zobrazit", "ano");
		if (!config.contains("nahrano.gui.zabitoMobu.zprava")) config.set("nahrano.gui.zabitoMobu.zprava", "Zabito Mobu:");
		if (!config.contains("nahrano.gui.zabitoZvirat.zobrazit")) config.set("nahrano.gui.zabitoZvirat.zobrazit", "ano");
		if (!config.contains("nahrano.gui.zabitoZvirat.zprava")) config.set("nahrano.gui.zabitoZvirat.zprava", "Zabito Zvirat:");
		if (!config.contains("nahrano.gui.pocetSmrti.zobrazit")) config.set("nahrano.gui.pocetSmrti.zobrazit", "ano");
		if (!config.contains("nahrano.gui.pocetSmrti.zprava")) config.set("nahrano.gui.pocetSmrti.zprava", "Umrti:");
		if (!config.contains("nahrano.gui.iConomy.zobrazit")) config.set("nahrano.gui.iConomy.zobrazit", "ne");
		if (!config.contains("nahrano.gui.iConomy.zprava")) config.set("nahrano.gui.iConomy.zprava", "Penize:");

		if (!config.contains("teleport.povolit")) config.set("teleport.povolit", "ano");
		if (!config.contains("teleport.pozadavky")) config.set("teleport.pozadavky", "ano");
		if (!config.contains("teleport.tpKdyzNeniOnline")) config.set("teleport.tpKdyzNeniOnline", "ne");
		if (!config.contains("teleport.zprava.uspech")) config.set("teleport.zprava.uspech", "&4uspesne &8jste se teleportoval k &4{JMENO}");
		if (!config.contains("teleport.zprava.uspech")) config.set("teleport.zprava.uspechOffline", "&4uspesne &8jste se teleportoval k poslední pozici &4{JMENO}");
		if (!config.contains("teleport.zprava.hracOffline")) config.set("teleport.zprava.hracOffline", "&4Hrac {JMENO} &8neni online! Zkusime najit jeho posledni pozici.");
		if (!config.contains("teleport.zprava.hracovaPoziceNeulozena")) config.set("teleport.zprava.NeniZaznamenan", "&8Litujeme, ale nenašli jsme poslední polohu &4{JMENO}&8.");

		if (!config.contains("rezervaceMist.povolit")) config.set("rezervaceMist.povolit", "ano");
		if (!config.contains("rezervaceMist.nechatVyhrazenoSlotu")) config.set("rezervaceMist.nechatVyhrazenoSlotu", 0);
		if (!config.contains("rezervaceMist.zprava")) config.set("rezervaceMist.zprava", "&4{JMENO} &8se pripojil a vy jste byl vyhozen ze hry.");

		if (!config.contains("inventar.povolit")) config.set("inventar.povolit", "ano");

		if (!config.contains("manager.povolit")) config.set("manager.povolit", "ano");
		if (!config.contains("manager.ban.zprava")) config.set("manager.ban.zprava", "&4{TARGET} &8byl zabanovan &4{JMENO}&8 z duvodu &4{DUVOD}&8.");
		if (!config.contains("manager.unban.zprava")) config.set("manager.unban.zprava", "&4{TARGET} &8byl unbanovan &4{JMENO}&8 z duvodu &4{DUVOD}&8.");
		if (!config.contains("manager.kick.zprava")) config.set("manager.kick.zprava", "&4{TARGET} &8byl vyhozen &4{JMENO}&8 z duvodu &4{DUVOD}&8.");

		if (!config.contains("vtipy.povolit")) config.set("vtipy.povolit", "ano");
		if (!config.contains("vtipy.interval")) config.set("vtipy.interval", 1);
		if (!config.contains("vtipy.format")) config.set("vtipy.format", "&e{VTIP}&f");

		if (!config.contains("whiteList.povolit")) config.set("whiteList.povolit", "ne");
		if (!config.contains("whiteList.zprava")) config.set("whiteList.zprava", "&4Nejste na Whitelistu!!");

		if (!config.contains("autoZpravy.povolit")) config.set("autoZpravy.povolit", "ano");
		if (!config.contains("autoZpravy.interval")) config.set("autoZpravy.interval", 60);
		if (!config.contains("autoZpravy.prefix")) config.set("autoZpravy.prefix", "&8[&4FoE&8]");
		if (!config.contains("autoZpravy.zpravy")) {
			List<String> g = new ArrayList<String>();
			g.add("{PREFIX} {JMENO} vis ze u nas na serveru muzes umrit hlady ?");
			g.add("{PREFIX} kupte si u nas nejakou vec a podporte tim server.");
			config.set("autoZpravy.zpravy", g);
		}

		if (!config.contains("warp.povolit")) config.set("warp.povolit", "ano");
		if (!config.contains("warp.nedostatecneOpravneni")) config.set("warp.nedostatecneOpravneni", "Nemate opravneni na tento warp!");
		if (!config.contains("warp.zprava.vytvorit")) config.set("warp.zprava.vytvorit", "&4Vytvoril&8 jste warp s nazvem &4{WARP}&8 a popisem &4{POPIS}&8.");
		if (!config.contains("warp.zprava.odstranit")) config.set("warp.zprava.odstranit", "&4Odstranil&8 jste warp &4{WARP}&8.");
		if (!config.contains("warp.zprava.prazdno")) config.set("warp.zprava.prazdno", "&4Nebyl &8nalezen zadny &4warp&8.");
		if (!config.contains("warp.zprava.neexistuje")) config.set("warp.zprava.neexistuje", "&4warp&8 s nazvem&4 {WARP}&8 neexistuje.");
		if (!config.contains("warp.zprava.existuje")) config.set("warp.zprava.existuje", "&4warp&8 s nazvem&4 {WARP}&8 je jiz pouzit.");

		if (!config.contains("msg.povolit")) config.set("msg.povolit", "ano");
		if (!config.contains("msg.zprava.jeOffline")) config.set("msg.zprava.jeOffline", "{JMENO} je offline.");
		if (!config.contains("msg.format")) config.set("msg.format", "&8[&4{JMENO}&8 -> &4{TARGET}&8]&f {ZPRAVA}");

		if (!config.contains("gamemode.povolit")) config.set("gamemode.povolit", "ano");
		if (!config.contains("gamemode.zpravy.creative")) config.set("gamemode.zpravy.creative", "&4{JMENO} &8zmenil svuj herni rezim na &4Creative&8.");
		if (!config.contains("gamemode.zpravy.survival")) config.set("gamemode.zpravy.survival", "&4{JMENO} &8zmenil svuj herni rezim na &4Survival&8.");
		if (!config.contains("gamemode.zpravy.adventure")) config.set("gamemode.zpravy.adventure", "&4{JMENO} &8zmenil svuj herni rezim na &4Adventure&8.");
		if (!config.contains("gamemode.zpravy.jizMaCreative")) config.set("gamemode.zpravy.jizMaCreative", "&4Jiz&8 mas &4creative&8!");
		if (!config.contains("gamemode.zpravy.jizMaSurvival")) config.set("gamemode.zpravy.jizMaSurvival", "&4Jiz&8 mas &4survival&8!");
		if (!config.contains("gamemode.zpravy.jizMaAdventure")) config.set("gamemode.zpravy.jizMaAdventure", "&4Jiz&8 mas &4adventure&8!");

		if (!config.contains("help.povolit")) config.set("help.povolit", "ano");

		if (!config.contains("ostatni.nedostatecneOpravneni")) config.set("ostatni.nedostatecneOpravneni", "&4Na tuto akci nemate &8opravneni&4!");
		if (!config.contains("ostatni.prevestConfig")) config.set("ostatni.prevestConfig", "ne");

		saveConfig(config, configFile);
		saveConfig(deathMsgs, deathMsgsFile);
		loaded = true;
	}

	public void setVersion(String version) {
		if (config.contains("version") && config.getString("version") != version) {
			foe.getErrorMananger().log(Level.INFO, "Byla zjistena zmena verze pluginu. Prosim, zkontrolujte nastaveni.");
		}
		config.set("version", version);
	}

	private void saveConfig(YamlConfiguration config, File configFile) {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
