package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
	public ErrorManager		err;
	public ConfigManager	cm;
	public FeaturesManager	fm;
	
	public onPlayerDeath() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager();
	}
	
	@EventHandler
	public void onDeath(final PlayerDeathEvent event) {
		try {
			if (fm.umrtiZpravyIsEnabled) {
				event.setDeathMessage("");
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
