package eu.frelania.foe.events;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;

import eu.frelania.foe.main.FoE;
import eu.frelania.foe.utils.Replace;

public class OnEntityDeath implements Listener {

	private FoE foe;

	public OnEntityDeath(FoE plugin) {
		foe = plugin;
	}

	@EventHandler
	public void EntityDeathEvent(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity != null) {
			if (entity instanceof Monster) {
				Monster monster = (Monster) entity;
				if (monster.getKiller() instanceof Player) {
					foe.logDebug("OnEntityDeath.EntityDeathEvent() - Entity: " + monster.getType().toString() + ", Killed by: " + monster.getKiller().getName());

					foe.joinedUsers.get(monster.getKiller().getName()).hasKilledMonster();
				}
			}
			if (entity instanceof Animals) {
				Animals animal = (Animals) entity;
				if (animal.getKiller() instanceof Player) {
					foe.logDebug("OnEntityDeath.EntityDeathEvent() - Entity: " + animal.getType().toString() + ", Killed by: " + animal.getKiller().getName());

					foe.joinedUsers.get(animal.getKiller().getName()).hasKilledAnimal();
				}
			}
			if (entity instanceof Player) {
				Player player = (Player) entity;
				Entity killer = null;

				EntityDamageEvent entityDamagerEvent = player.getLastDamageCause();
				if (entityDamagerEvent != null && entityDamagerEvent instanceof EntityDamageByEntityEvent) {
					killer = ((EntityDamageByEntityEvent) entityDamagerEvent).getDamager();
				}
				if (killer != null) {
					// Hrace zabila jina entita
					if (killer instanceof Player) {
						// Hr·Ë zabit jin˝m hr·Ëem
						foe.joinedUsers.get(player.getKiller().getName()).hasKilledPlayer();
						String itemName = player.getKiller().getItemInHand().getType().toString();
						foe.logDebug("OnEntityDeath.EntityDeathEvent() - Player: " + player.getName() + ", Killed by: " + player.getKiller().getName());

						foe.getServer().broadcastMessage(Replace.killedMsg(foe.getConfigManager().deathMsgs.getString("smrt.hrac"), player.getName(), player.getKiller().getName(), itemName));
					} else {
						if (!(killer instanceof Animals)) {
							// Hr·Ë zabit monstrem (!Player && !Animals)
							String monster = killer.getType().getName().toLowerCase();
							foe.logDebug("OnEntityDeath.EntityDeathEvent() - Player: " + player.getName() + ", Killed by: " + monster);

							if (!foe.getConfigManager().deathMsgs.contains("smrt.monster." + monster)) {
								foe.getConfigManager().deathMsgs.set("smrt.monster." + monster, "[FoE] Zprava vystihujici duvod smrti {JMENO} nebyla nalezena!");
								try {
									foe.getConfigManager().deathMsgs.save(foe.getConfigManager().deathMsgsFile);
								} catch (IOException e) {
									foe.getErrorMananger().log(Level.WARNING, e, "V plugins/FoE/smrtZpravy.yml chybi \"smrt.monster." + monster + "\" - Novy druh moba?");
								}
							}

							foe.getServer().broadcastMessage(Replace.playerName(foe.getConfigManager().deathMsgs.getString("smrt.monster." + monster), player.getName()));
						}
					}
				} else {
					// Hrac se sam zabil (svou hlouposti)
					String reason = entityDamagerEvent.getCause().toString().toLowerCase();
					foe.logDebug("OnEntityDeath.EntityDeathEvent() - Player: " + player.getName() + ", Reason: " + reason);

					if (!foe.getConfigManager().deathMsgs.contains("smrt.sebevrazda." + reason)) {
						foe.getConfigManager().deathMsgs.set("smrt.sebevrazda." + reason, "[FoE] Zprava vystihujici duvod smrti {JMENO} nebyla nalezena!");
						try {
							foe.getConfigManager().deathMsgs.save(foe.getConfigManager().deathMsgsFile);
						} catch (IOException e) {
							foe.getErrorMananger().log(Level.WARNING, e, "V plugins/FoE/smrtZpravy.yml chybi \"smrt.sebevrazda." + reason + "\" - Novy druh smrti?");
						}
					}

					foe.getServer().broadcastMessage(Replace.playerName(foe.getConfigManager().deathMsgs.getString("smrt.sebevrazda." + reason), player.getName()));
				}
			}
		}
	}

}
