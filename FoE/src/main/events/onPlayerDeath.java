package main.events;

import main.ErrorManager;
import main.FoE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
	public FoE			p;
	public ErrorManager	err	= new ErrorManager();
	
	public onPlayerDeath(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onDeath(final PlayerDeathEvent event) {
		try {
			if (p.umrtiZpravyPovolit) {
				event.setDeathMessage("");
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
