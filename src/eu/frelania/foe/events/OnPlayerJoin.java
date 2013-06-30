package eu.frelania.foe.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

import eu.frelania.foe.main.FoE;
import eu.frelania.foe.main.PlayerManager;

public class OnPlayerJoin implements Listener {

	private FoE foe;

	public OnPlayerJoin(FoE plugin) {
		foe = plugin;
	}

	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent event) {
		foe.logDebug("PlayerJoinEvent - Player: " + event.getPlayer().getName());

		Player player = event.getPlayer();
		foe.joinedUsers.put(player.getName(), new PlayerManager(foe, player));
		foe.joinedUsers.get(player.getName()).loadPlayer();
	}

}