package eu.frelania.foe.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.Listener;

import eu.frelania.foe.main.FoE;

public class OnPlayerQuit implements Listener {

	private FoE foe;

	public OnPlayerQuit(FoE plugin) {
		foe = plugin;
	}

	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent event) {
		foe.logDebug("OnPlayerQuit - Player: " + event.getPlayer().getName());

		Player player = event.getPlayer();
		foe.joinedUsers.get(player.getName()).unloadPlayer();
		foe.joinedUsers.remove(player.getName());
	}

}
