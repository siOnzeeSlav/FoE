package eu.frelania.foe.events;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.Listener;
import eu.frelania.foe.main.FoE;
import eu.frelania.foe.main.PlayerManager;

public class OnPlayerQuit implements Listener {

	private FoE foe;

	public OnPlayerQuit(FoE plugin) {
		foe = plugin;
	}

	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent event) {
		foe.logDebug("OnPlayerQuit - Player: " + event.getPlayer().getName());

		PlayerManager player = foe.joinedUsers.get(event.getPlayer().getName());
		if(player == null){
			foe.getErrorMananger().log(Level.SEVERE, "PlayerManager for player: " + event.getPlayer().getName() + " not found!\n @OnPlayerQuit:25");
		}
		player.unloadPlayer();
		foe.joinedUsers.remove(player.playerName);
	}

}
