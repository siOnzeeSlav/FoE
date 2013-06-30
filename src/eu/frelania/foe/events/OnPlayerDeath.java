package eu.frelania.foe.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.Listener;

import eu.frelania.foe.main.FoE;

public class OnPlayerDeath implements Listener {

	private FoE foe;

	public OnPlayerDeath(FoE plugin) {
		foe = plugin;
	}

	@EventHandler
	public void PlayerDeathEvent(PlayerDeathEvent event) {
		foe.logDebug("OnPlayerDeath.PlayerDeathEvent() - Player: " + event.getEntity().getName() + ", event: " + event.getEventName());
		event.setDeathMessage(foe.getConfigManager().deathMsgs.getString("smrt.sebevrazda.fall"));
	}

}
