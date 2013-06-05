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
							switch (getItem(target)) {
								case ACTIVATOR_RAIL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case AIR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ANVIL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case APPLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ARROW:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BAKED_POTATO:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BEACON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BED:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BEDROCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BED_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BIRCH_WOOD_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BLAZE_POWDER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BLAZE_ROD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOAT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOOK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOOKSHELF:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOOK_AND_QUILL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOW:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BOWL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BREAD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BREWING_STAND:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BREWING_STAND_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BRICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BRICK_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BROWN_MUSHROOM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BUCKET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case BURNING_FURNACE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CACTUS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CAKE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CAKE_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CARROT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CARROT_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CARROT_STICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CAULDRON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CAULDRON_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CHAINMAIL_BOOTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CHAINMAIL_CHESTPLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CHAINMAIL_HELMET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CHAINMAIL_LEGGINGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CHEST:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CLAY:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CLAY_BALL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CLAY_BRICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COAL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COAL_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COBBLESTONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COBBLESTONE_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COBBLE_WALL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COCOA:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COMMAND:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COMPASS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COOKED_BEEF:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COOKED_CHICKEN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COOKED_FISH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case COOKIE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case CROPS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DAYLIGHT_DETECTOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DEAD_BUSH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DETECTOR_RAIL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_AXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_BOOTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_CHESTPLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_HELMET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_HOE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_LEGGINGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_PICKAXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIAMOND_SPADE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIODE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIODE_BLOCK_OFF:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIODE_BLOCK_ON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DIRT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DISPENSER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DOUBLE_STEP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DRAGON_EGG:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case DROPPER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EGG:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EMERALD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EMERALD_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EMERALD_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EMPTY_MAP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENCHANTED_BOOK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENCHANTMENT_TABLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENDER_CHEST:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENDER_PEARL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENDER_PORTAL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENDER_PORTAL_FRAME:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ENDER_STONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EXPLOSIVE_MINECART:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EXP_BOTTLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case EYE_OF_ENDER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FEATHER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FENCE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FENCE_GATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FERMENTED_SPIDER_EYE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FIRE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FIREBALL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FIREWORK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FIREWORK_CHARGE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FISHING_ROD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FLINT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FLINT_AND_STEEL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FLOWER_POT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FLOWER_POT_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case FURNACE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GHAST_TEAR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GLASS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GLASS_BOTTLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GLOWING_REDSTONE_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GLOWSTONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GLOWSTONE_DUST:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLDEN_APPLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLDEN_CARROT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_AXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_BOOTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_CHESTPLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_HELMET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_HOE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_INGOT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_LEGGINGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_NUGGET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_PICKAXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_PLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_RECORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_SPADE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GOLD_SWORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GRASS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GRAVEL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GREEN_RECORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case GRILLED_PORK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case HOPPER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case HOPPER_MINECART:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case HUGE_MUSHROOM_1:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case HUGE_MUSHROOM_2:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ICE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case INK_SACK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_AXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_BOOTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_CHESTPLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_DOOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_DOOR_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_FENCE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_HELMET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_HOE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_INGOT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_LEGGINGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_PICKAXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_PLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_SPADE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case IRON_SWORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ITEM_FRAME:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case JACK_O_LANTERN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case JUKEBOX:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case JUNGLE_WOOD_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LADDER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LAPIS_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LAPIS_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LAVA:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LAVA_BUCKET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEATHER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEATHER_BOOTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEATHER_CHESTPLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEATHER_HELMET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEATHER_LEGGINGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEAVES:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LEVER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LOCKED_CHEST:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case LOG:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MAGMA_CREAM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MAP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MELON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MELON_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MELON_SEEDS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MELON_STEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MILK_BUCKET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MINECART:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MOB_SPAWNER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MONSTER_EGG:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MONSTER_EGGS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MOSSY_COBBLESTONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MUSHROOM_SOUP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case MYCEL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHERRACK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_BRICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_BRICK_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_BRICK_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_FENCE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_STALK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_STAR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NETHER_WARTS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case NOTE_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case OBSIDIAN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PAINTING:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PAPER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PISTON_BASE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PISTON_EXTENSION:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PISTON_MOVING_PIECE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PISTON_STICKY_BASE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POISONOUS_POTATO:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PORK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PORTAL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POTATO:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POTATO_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POTION:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POWERED_MINECART:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case POWERED_RAIL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PUMPKIN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PUMPKIN_PIE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PUMPKIN_SEEDS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case PUMPKIN_STEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case QUARTZ:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case QUARTZ_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case QUARTZ_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case QUARTZ_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RAILS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RAW_BEEF:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RAW_CHICKEN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RAW_FISH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case REDSTONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case REDSTONE_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case REDSTONE_COMPARATOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case REDSTONE_ORE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case REDSTONE_WIRE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RED_MUSHROOM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case RED_ROSE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case ROTTEN_FLESH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SADDLE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SAND:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SANDSTONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SANDSTONE_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SAPLING:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SEEDS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SHEARS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SIGN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SKULL_ITEM:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SLIME_BALL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SMOOTH_BRICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SMOOTH_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SNOW:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SNOW_BALL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SNOW_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SOIL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SOUL_SAND:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SPECKLED_MELON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SPIDER_EYE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SPONGE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SPRUCE_WOOD_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STEP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STICK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_AXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_BUTTON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_HOE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_PICKAXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_PLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_SPADE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STONE_SWORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STORAGE_MINECART:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case STRING:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SUGAR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SUGAR_CANE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SUGAR_CANE_BLOCK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case SULPHUR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case THIN_GLASS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TNT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TORCH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TRAPPED_CHEST:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TRAP_DOOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TRIPWIRE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case TRIPWIRE_HOOK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case VINE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WALL_SIGN:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WATCH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WATER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WATER_BUCKET:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WATER_LILY:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WEB:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WHEAT:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOODEN_DOOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_AXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_BUTTON:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_DOOR:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_DOUBLE_STEP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_HOE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_PICKAXE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_PLATE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_SPADE:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_STAIRS:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_STEP:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOD_SWORD:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WOOL:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WORKBENCH:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case WRITTEN_BOOK:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
									break;
								case YELLOW_FLOWER:
									Bukkit.broadcastMessage(replacePredmet(p.config.getString("umrtiZpravy.Predmet." + getItem(target)), playerName, targetName));
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
	
	public Material getItem(Player target) {
		return target.getItemInHand().getType();
	}
	
	public void checkConfig() {
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
		if (!p.config.contains("umrtiZpravy.Predmet.DIAMOND_SWORD"))
			p.config.set("umrtiZpravy.Predmet.DIAMOND_SWORD", "&4{TARGET} &8byl zabit &4{JMENO} &8pøedmìtem &4diamantovým meèem&8.");
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
