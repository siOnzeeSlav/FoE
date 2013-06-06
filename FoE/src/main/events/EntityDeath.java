package main.events;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Monster;
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

public class EntityDeath implements Listener {
	public FoE					p;
	
	public File					umrtiZpravyFile	= new File("plugins/FoE/umrtiZpravy.yml");
	public YamlConfiguration	umrtiZpravy		= YamlConfiguration.loadConfiguration(umrtiZpravyFile);
	
	public EntityDeath(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		try {
			Entity entity = event.getEntity();
			if (p.umrtiZpravyPovolit) {
				if (entity != null) {
					if (entity instanceof Monster) {
						Monster monster = (Monster) entity;
						Entity killer = monster.getKiller();
						if (killer == null)
							return;
						String killerName = killer.getType().getName();
						p.uzivatel(killerName);
						p.uziv.set("ZabitoMobu", p.uziv.getInt("ZabitoMobu") + 1);
						p.saveConfig(p.uziv, p.uzivFile);
						p.aktualizovatGUI(killerName);
					}
					if (entity instanceof Player) {
						Player player = (Player) entity;
						String playerName = player.getName();
						p.uzivatel(playerName);
						p.uziv.set("PocetSmrti", p.uziv.getInt("PocetSmrti") + 1);
						p.saveConfig(p.uziv, p.uzivFile);
						p.aktualizovatGUI(player);
						EntityDamageEvent EDevent = player.getLastDamageCause();
						Entity killer = null;
						if (EDevent instanceof EntityDamageByEntityEvent) {
							killer = ((EntityDamageByEntityEvent) EDevent).getDamager();
						}
						if (killer != null) {
							if (killer.getType() != null) {
								String targetName = killer.getType().getName();
								p.uzivatel(targetName);
								p.uziv.set("ZabitoHracu", p.uziv.getInt("ZabitoHracu") + 1);
								p.saveConfig(p.uziv, p.uzivFile);
								p.aktualizovatGUI(targetName);
								if (!(killer instanceof Animals) && (!(killer instanceof Player))) {
									if (killer instanceof Creeper) {
										String message = umrtiZpravy.getString("Creeper");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Zombie) {
										String message = umrtiZpravy.getString("Zombie");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Skeleton) {
										String message = umrtiZpravy.getString("Skeleton");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Spider) {
										String message = umrtiZpravy.getString("Spider");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Wither) {
										String message = umrtiZpravy.getString("Wither");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Wolf) {
										String message = umrtiZpravy.getString("Wolf");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Ghast) {
										String message = umrtiZpravy.getString("Ghast");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Explosive) {
										String message = umrtiZpravy.getString("Explosive");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof PigZombie) {
										String message = umrtiZpravy.getString("PigZombie");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Slime) {
										String message = umrtiZpravy.getString("Slime");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof SmallFireball) {
										String message = umrtiZpravy.getString("SmallFireball");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Witch) {
										String message = umrtiZpravy.getString("Witch");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Enderman) {
										String message = umrtiZpravy.getString("Enderman");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof EnderDragon) {
										String message = umrtiZpravy.getString("EnderDragon");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Blaze) {
										String message = umrtiZpravy.getString("Blaze");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Silverfish) {
										String message = umrtiZpravy.getString("Silverfish");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof Giant) {
										String message = umrtiZpravy.getString("Giant");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									} else if (killer instanceof CaveSpider) {
										String message = umrtiZpravy.getString("CaveSpider");
										Bukkit.broadcastMessage(replace(message, playerName, targetName));
									}
								} else if (killer instanceof Player) {
									Player target = (Player) killer;
									p.uzivatel(targetName);
									p.uziv.set("ZabitoHracu", p.uziv.getInt("ZabitoHracu") + 1);
									p.saveConfig(p.uziv, p.uzivFile);
									
									switch (getItem(target)) {
										case ACTIVATOR_RAIL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case AIR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ANVIL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case APPLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ARROW:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BAKED_POTATO:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BEACON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BED:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BEDROCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BED_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BIRCH_WOOD_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BLAZE_POWDER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BLAZE_ROD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOAT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOOK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOOKSHELF:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOOK_AND_QUILL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOW:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BOWL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BREAD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BREWING_STAND:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BREWING_STAND_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BRICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BRICK_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BROWN_MUSHROOM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BUCKET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case BURNING_FURNACE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CACTUS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CAKE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CAKE_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CARROT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CARROT_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CARROT_STICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CAULDRON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CAULDRON_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CHAINMAIL_BOOTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CHAINMAIL_CHESTPLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CHAINMAIL_HELMET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CHAINMAIL_LEGGINGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CHEST:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CLAY:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CLAY_BALL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CLAY_BRICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COAL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COAL_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COBBLESTONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COBBLESTONE_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COBBLE_WALL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COCOA:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COMMAND:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COMPASS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COOKED_BEEF:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COOKED_CHICKEN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COOKED_FISH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case COOKIE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case CROPS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DAYLIGHT_DETECTOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DEAD_BUSH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DETECTOR_RAIL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_AXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_BOOTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_CHESTPLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_HELMET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_HOE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_LEGGINGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_PICKAXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIAMOND_SPADE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIODE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIODE_BLOCK_OFF:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIODE_BLOCK_ON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DIRT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DISPENSER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DOUBLE_STEP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DRAGON_EGG:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case DROPPER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EGG:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EMERALD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EMERALD_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EMERALD_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EMPTY_MAP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENCHANTED_BOOK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENCHANTMENT_TABLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENDER_CHEST:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENDER_PEARL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENDER_PORTAL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENDER_PORTAL_FRAME:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ENDER_STONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EXPLOSIVE_MINECART:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EXP_BOTTLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case EYE_OF_ENDER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FEATHER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FENCE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FENCE_GATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FERMENTED_SPIDER_EYE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FIRE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FIREBALL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FIREWORK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FIREWORK_CHARGE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FISHING_ROD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FLINT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FLINT_AND_STEEL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FLOWER_POT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FLOWER_POT_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case FURNACE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GHAST_TEAR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GLASS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GLASS_BOTTLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GLOWING_REDSTONE_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GLOWSTONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GLOWSTONE_DUST:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLDEN_APPLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLDEN_CARROT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_AXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_BOOTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_CHESTPLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_HELMET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_HOE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_INGOT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_LEGGINGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_NUGGET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_PICKAXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_PLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_RECORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_SPADE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GOLD_SWORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GRASS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GRAVEL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GREEN_RECORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case GRILLED_PORK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case HOPPER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case HOPPER_MINECART:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case HUGE_MUSHROOM_1:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case HUGE_MUSHROOM_2:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ICE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case INK_SACK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_AXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_BOOTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_CHESTPLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_DOOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_DOOR_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_FENCE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_HELMET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_HOE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_INGOT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_LEGGINGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_PICKAXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_PLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_SPADE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case IRON_SWORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ITEM_FRAME:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case JACK_O_LANTERN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case JUKEBOX:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case JUNGLE_WOOD_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LADDER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LAPIS_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LAPIS_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LAVA:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LAVA_BUCKET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEATHER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEATHER_BOOTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEATHER_CHESTPLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEATHER_HELMET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEATHER_LEGGINGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEAVES:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LEVER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LOCKED_CHEST:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case LOG:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MAGMA_CREAM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MAP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MELON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MELON_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MELON_SEEDS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MELON_STEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MILK_BUCKET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MINECART:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MOB_SPAWNER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MONSTER_EGG:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MONSTER_EGGS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MOSSY_COBBLESTONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MUSHROOM_SOUP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case MYCEL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHERRACK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_BRICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_BRICK_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_BRICK_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_FENCE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_STALK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_STAR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NETHER_WARTS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case NOTE_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case OBSIDIAN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PAINTING:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PAPER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PISTON_BASE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PISTON_EXTENSION:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PISTON_MOVING_PIECE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PISTON_STICKY_BASE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POISONOUS_POTATO:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PORK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PORTAL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POTATO:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POTATO_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POTION:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POWERED_MINECART:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case POWERED_RAIL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PUMPKIN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PUMPKIN_PIE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PUMPKIN_SEEDS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case PUMPKIN_STEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case QUARTZ:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case QUARTZ_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case QUARTZ_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case QUARTZ_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RAILS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RAW_BEEF:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RAW_CHICKEN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RAW_FISH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case REDSTONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case REDSTONE_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case REDSTONE_COMPARATOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case REDSTONE_ORE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case REDSTONE_WIRE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RED_MUSHROOM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case RED_ROSE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case ROTTEN_FLESH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SADDLE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SAND:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SANDSTONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SANDSTONE_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SAPLING:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SEEDS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SHEARS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SIGN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SKULL_ITEM:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SLIME_BALL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SMOOTH_BRICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SMOOTH_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SNOW:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SNOW_BALL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SNOW_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SOIL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SOUL_SAND:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SPECKLED_MELON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SPIDER_EYE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SPONGE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SPRUCE_WOOD_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STEP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STICK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_AXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_BUTTON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_HOE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_PICKAXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_PLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_SPADE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STONE_SWORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STORAGE_MINECART:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case STRING:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SUGAR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SUGAR_CANE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SUGAR_CANE_BLOCK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case SULPHUR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case THIN_GLASS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TNT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TORCH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TRAPPED_CHEST:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TRAP_DOOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TRIPWIRE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case TRIPWIRE_HOOK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case VINE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WALL_SIGN:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WATCH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WATER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WATER_BUCKET:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WATER_LILY:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WEB:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WHEAT:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOODEN_DOOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_AXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_BUTTON:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_DOOR:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_DOUBLE_STEP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_HOE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_PICKAXE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_PLATE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_SPADE:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_STAIRS:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_STEP:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOD_SWORD:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WOOL:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WORKBENCH:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case WRITTEN_BOOK:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										case YELLOW_FLOWER:
											poslatZpravu(umrtiZpravy.getString("Predmet." + getItem(target)), playerName, targetName, getItemName(target));
											break;
										default:
											break;
									}
								}
							}
							
						} else {
							switch (EDevent.getCause()) {
								case BLOCK_EXPLOSION:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case DROWNING:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case FALL:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case FALLING_BLOCK:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case FIRE:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case LAVA:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case LIGHTNING:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case MAGIC:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case MELTING:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case POISON:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case PROJECTILE:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case STARVATION:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case SUFFOCATION:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case SUICIDE:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case VOID:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								case WITHER:
									Bukkit.broadcastMessage(replaceColorsAndPlayer(umrtiZpravy.getString("Sebevrazda." + EDevent.getCause()), playerName));
									break;
								default:
									break;
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
	
	public String replaceColorsAndPlayer(String message, String playerName) {
		if (message.matches(".*\\{JMENO}.*"))
			message = message.replaceAll("\\{JMENO}", playerName);
		message = message.replaceAll("(&([a-fk-or0-9]))", "§$2");
		return message;
	}
	
	public void poslatZpravu(String somethingLikeNode, String playerName, String targetName, String itemName) {
		if (!umrtiZpravy.contains(somethingLikeNode)) {
			umrtiZpravy.set(somethingLikeNode, "&4{TARGET} &8byl zabit &4{JMENO} &8s pøedmìtem &4{ITEM}&8.");
			p.saveConfig(umrtiZpravy, umrtiZpravyFile);
		}
		Bukkit.broadcastMessage(replacePredmet(umrtiZpravy.getString(somethingLikeNode), playerName, targetName, itemName));
	}
	
	public Material getItem(Player target) {
		return target.getItemInHand().getType();
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
