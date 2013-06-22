package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.GUIManager;
import main.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
	public ConfigManager	cm;
	public ErrorManager		err;
	public PlayerManager	pm;
	public GUIManager		gm;
	public FeaturesManager	fm;
	public boolean			debug;
	
	public EntityDeath() {
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager(cm);
		debug = fm.debug;
	}
	
	public void addMob(String name) {
		pm = new PlayerManager(name);
		gm = new GUIManager(name);
		pm.loadPlayer();
		pm.uziv.set("ZabitoMobu", pm.getTotalKilledsMobs() + 1);
		pm.saveUser();
		gm.aktualizovatGUI();
	}
	
	public void addKill(String name) {
		pm = new PlayerManager(name);
		gm = new GUIManager(name);
		pm.loadPlayer();
		pm.uziv.set("ZabitoHracu", pm.getTotalKilledsPlayers() + 1);
		pm.saveUser();
		gm.aktualizovatGUI();
	}
	
	public void addAnimal(String name) {
		pm = new PlayerManager(name);
		gm = new GUIManager(name);
		pm.loadPlayer();
		pm.uziv.set("ZabitoZvirat", pm.getTotalKilledsAnimals() + 1);
		pm.saveUser();
		gm.aktualizovatGUI();
	}
	
	public void addDeath(String name) {
		pm = new PlayerManager(name);
		gm = new GUIManager(name);
		pm.loadPlayer();
		pm.uziv.set("PocetSmrti", pm.getTotalDeaths() + 1);
		pm.saveUser();
		gm.aktualizovatGUI();
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		try {
			Entity entity = event.getEntity();
			if (fm.umrtiZpravyIsEnabled) {
				if (entity != null) { // Entity neni null
					if (entity instanceof Monster) { // Je monster
						Monster monster = (Monster) entity; // Ziskam monstra
						if (monster.getKiller() instanceof Player) { // Ten kdo zabil monstra jestli je hrac
							String killerName = monster.getKiller().getName(); // Ziskat toho kdo zabil monstra jmeno
							if (debug)
								Bukkit.broadcastMessage(killerName + " pridavam zabitomob.");
							addMob(killerName); // Pridat jako mobkill
						}
					} else if (entity instanceof Animals) {
						Animals animal = (Animals) entity;
						if (animal.getKiller() instanceof Player) {
							String killerName = animal.getKiller().getName(); // Ziskat toho kdo zabil zvire jmeno
							if (debug)
								Bukkit.broadcastMessage(killerName + " pridavam zabitozvirat.");
							addAnimal(killerName); // Pridat jako zvirekill
						}
					} else if (entity instanceof Player) { // Je hrac
						Player player = (Player) entity; // Ziskam hrace
						String playerName = player.getName(); // Ziskam hracovo jmeno
						EntityDamageEvent EDevent = player.getLastDamageCause(); // Vezmu entitu co mu dala posledni poskozeni
						Entity killer = null; // Entity = null
						if (EDevent instanceof EntityDamageByEntityEvent) {
							killer = ((EntityDamageByEntityEvent) EDevent).getDamager(); // Ziskam killera
						}
						if (killer != null) { // Jestli nekdo zabil hrace
							if (killer.getType() != null) { // Typ kterej zabil hrace jestli existuje
								if (!(killer instanceof Animals) && (!(killer instanceof Player))) {
									String targetName = killer.getType().getName(); // Mob kterej zabil hrace
									if (debug)
										Bukkit.broadcastMessage(playerName + " pridavam umrti.");
									addDeath(playerName); // Pridam umrti hracovy
									poslatZpravuMonster(targetName, playerName); // Napisu kterej mob zabil hrace.
								} else if (killer instanceof Player) { // Jestli zabijak je hrac
									Player target = (Player) killer; // Pretypuju zabijaka na hrace
									if (target != null) { // Jestli zabijak neni null
										String targetName = target.getName(); // Vezmu zabijakovo jmeno
										if (debug)
											Bukkit.broadcastMessage(targetName + " pridavam zabito.");
										addKill(targetName); // Pridam zabijakovy Kill
										if (debug)
											Bukkit.broadcastMessage(playerName + " pridavam umrti.");
										addDeath(playerName); // Pridam mrtvemu umrti
										poslatZpravu(getItemName(target), playerName, targetName); // Napisu zpravu ze zabijak zabil hrace s predmetem
									}
								}
							}
						} else { // Killer je null takze se zabil sam
							poslatZpravuSebevrazda(EDevent.getCause().toString(), playerName); // Napisu zpravu jak se zabil
						}
					}
				}
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public void poslatZpravuSebevrazda(String cause, String playerName) {
		if (!cm.umrtiZpravy.contains("Sebevrazda." + cause)) {
			cm.umrtiZpravy.set("Sebevrazda." + cause, "&4{JMENO} &8byl zabit &4" + cause + "&8.");
			cm.saveConfig(cm.umrtiZpravy, cm.umrtiZpravyFile);
		}
		Bukkit.broadcastMessage(replaceColorsAndPlayer(cm.umrtiZpravy.getString("Sebevrazda." + cause), playerName));
	}
	
	public void poslatZpravuMonster(String targetName, String playerName) {
		if (!cm.umrtiZpravy.contains("Monsters." + targetName)) {
			//List<String> mobMessagesList = cm.umrtiZpravy.getStringList("Monsters." + targetName);
			//Random rnd = new Random();
			//if(mobMessagesList.length > 0) {
			//Bukkit.broadcastMessage(replace(cm.umrtiZpravy.getString(mobMessagesList.get(rnd.next(mobMessagesList.length))), playerName, targetName)
			//} else {
			// mobMessagesList.add("&4{JMENO} &8byl zabit &4{MOB}&8.");
			//cm.umrtiZpravy.set("Monsters." + targetName, mobMessagesList);
			//cm.saveConfig(cm.umrtiZpravy, cm.umrtiZpravyFile);
			// }
			cm.umrtiZpravy.set("Monsters." + targetName, "&4{JMENO} &8byl zabit &4{MOB}&8.");
			cm.saveConfig(cm.umrtiZpravy, cm.umrtiZpravyFile);
		}
		Bukkit.broadcastMessage(replace(cm.umrtiZpravy.getString("Monsters." + targetName), playerName, targetName));
	}
	
	public void poslatZpravu(String itemName, String playerName, String targetName) {
		if (debug)
			Bukkit.broadcastMessage(itemName + " / " + playerName + " / " + targetName);
		if (!cm.umrtiZpravy.contains(itemName)) {
			cm.umrtiZpravy.set(itemName, "&4{TARGET} &8byl zabit &4{JMENO} &8s predmetem &4{ITEM}&8.");
			cm.saveConfig(cm.umrtiZpravy, cm.umrtiZpravyFile);
		}
		Bukkit.broadcastMessage(replacePredmet(cm.umrtiZpravy.getString(itemName), playerName, targetName, itemName));
	}
	
	public String replaceColorsAndPlayer(String message, String playerName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String getItemName(Player target) {
		return target.getItemInHand().getType().toString();
	}
	
	public String replacePredmet(String message, String playerName, String targetName, String itemName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		if (message.matches(".*\\{TARGET}.*"))
			message = message.replaceAll("\\{TARGET}", targetName);
		if (message.matches(".*\\{ITEM}.*"))
			message = message.replaceAll("\\{ITEM}", itemName);
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String replace(String message, String playerName, String targetName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		if (message.matches(".*\\{MOB}.*"))
			message = message.replaceAll("\\{MOB}", targetName);
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
		
	}
}
