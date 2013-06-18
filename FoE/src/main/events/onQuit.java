package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuit implements Listener {
	public FoE				p;
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public onQuit(FoE plugin) {
		this.p = plugin;
	}
	
	public void delayedQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		try {
			if (p.nahranostPovolit) {
				p.odRegistrovatHrace(playerName);
			}
			if (p.guiPovolit) {
				for (Player PL : Bukkit.getOnlinePlayers()) {
					p.aktualizovatGUI(PL.getName());
				}
			}
			if (p.teleportPovolit) {
				p.ulozitPozici(player);
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	@EventHandler
	public void Quit(final PlayerQuitEvent event) {
		try {
			if (p.kdyzHracSeOdpojiPovolit)
				event.setQuitMessage(p.nahradit(cm.config.getString("KdyzHracSe.Odpoji.Zprava"), event.getPlayer().getName()));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					delayedQuit(event);
				}
			});
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
