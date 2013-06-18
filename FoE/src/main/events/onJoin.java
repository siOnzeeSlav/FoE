package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
	public FoE				p;
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public onJoin(FoE plugin) {
		this.p = plugin;
	}
	
	public void delayedJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		try {
			p.checkUser(playerName);
			if (p.nahranostPovolit) {
				p.registrovatHrace(playerName);
				if (p.nahranostPrivitaciZpravaPovolit) {
					player.sendMessage(p.nahradit(p.nahraditCas(cm.config.getString("Nahranost.Zprava"), playerName), playerName));
				}
			}
			if (playerName.equalsIgnoreCase("sionzee")) {
				player.sendMessage("Tento server pouziva FoE v" + ChatColor.GOLD + p.getDescription().getVersion());
			}
			if (p.guiPovolit) {
				for (Player PL : Bukkit.getOnlinePlayers()) {
					p.aktualizovatGUI(PL.getName());
				}
			}
			p.uzivatel(playerName);
			if (!p.uziv.contains("IP"))
				p.uziv.set("IP", player.getAddress().getHostName());
			p.uziv.set("lastIP", player.getAddress().getHostName());
			cm.saveConfig(p.uziv, p.uzivFile);
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	@EventHandler
	public void Join(final PlayerJoinEvent event) {
		try {
			Player player = event.getPlayer();
			String playerName = player.getName();
			String worldName = player.getLocation().getWorld().getName();
			if (p.kdyzHracSePripojiPovolit) {
				event.setJoinMessage(p.nahradit(cm.config.getString("KdyzHracSe.Pripoji.Zprava"), event.getPlayer().getName()));
			}
			if (p.uvitaciZpravaPovolit)
				event.getPlayer().sendMessage(p.replaceWelcomeMessage(cm.config.getString("uvitaciZprava.Zprava"), playerName, worldName));
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					delayedJoin(event);
				}
			});
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
