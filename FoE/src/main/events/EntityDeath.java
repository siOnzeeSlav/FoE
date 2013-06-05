package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeath implements Listener {
	public FoE	p;
	
	public EntityDeath(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		try {
			if (p.umrtiZpravyPovolit) {
				Entity entity = event.getEntity();
				if (entity != null) {
					if (entity instanceof Player) {
						Player player = (Player) entity;
						String playerName = player.getName();
						EntityDamageEvent EDevent = player.getLastDamageCause();
						Entity killer = null;
						if (EDevent instanceof EntityDamageByEntityEvent) {
							killer = ((EntityDamageByEntityEvent) EDevent).getDamager();
						}
						String mobName = "BezeJmena";
						if (killer.getType() != null) {
							mobName = killer.getType().getName();
						}
						Player target = (Player) killer;
						String targetName = target.getName();
						if (killer instanceof Creeper) {
							String message = p.config.getString("umrtiZpravy.Creeper");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Zombie) {
							String message = p.config.getString("umrtiZpravy.Zombie");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Skeleton) {
							String message = p.config.getString("umrtiZpravy.Skeleton");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Spider) {
							String message = p.config.getString("umrtiZpravy.Spider");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Wither) {
							String message = p.config.getString("umrtiZpravy.Wither");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Wolf) {
							String message = p.config.getString("umrtiZpravy.Wolf");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Ghast) {
							String message = p.config.getString("umrtiZpravy.Ghast");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Explosive) {
							String message = p.config.getString("umrtiZpravy.Explosive");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof PigZombie) {
							String message = p.config.getString("umrtiZpravy.PigZombie");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Slime) {
							String message = p.config.getString("umrtiZpravy.Slime");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof SmallFireball) {
							String message = p.config.getString("umrtiZpravy.SmallFireball");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Witch) {
							String message = p.config.getString("umrtiZpravy.Witch");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Enderman) {
							String message = p.config.getString("umrtiZpravy.Enderman");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof EnderDragon) {
							String message = p.config.getString("umrtiZpravy.EnderDragon");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Blaze) {
							String message = p.config.getString("umrtiZpravy.Blaze");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Silverfish) {
							String message = p.config.getString("umrtiZpravy.Silverfish");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						} else if (killer instanceof Giant) {
							String message = p.config.getString("umrtiZpravy.Giant");
							Bukkit.broadcastMessage(replace(message, playerName, mobName));
						}
						if (killer instanceof Player) {
							if (target.getItemInHand() == new ItemStack(Material.DIAMOND_SWORD)) {
								Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet.DIAMOND_SWORD"), playerName, targetName));
							}
						}
						// Zadost #008.
					}
				}
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
	}
	
	public void checkConfig() {
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD")) {
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		}
	}
	
	public String replacePredmet(String message, String playerName, String targetName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		if (message.matches(".*\\{TARGET}.*"))
			message = message.replaceAll("\\{TARGET}", targetName);
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public String replace(String message, String playerName, String mobName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		if (message.matches(".*\\{MOB}.*"))
			message = message.replaceAll("\\{MOB}", mobName);
		
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		
		return message;
		
	}
}
