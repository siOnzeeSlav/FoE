package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.ConfigManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class onKick implements Listener {
	public FoE				p;
	public ConfigManager	cm	= new ConfigManager();
	
	public onKick(FoE plugin) {
		this.p = plugin;
	}
	
	public void delayedKick(PlayerKickEvent event) {
		
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
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
		
	}
	
	@EventHandler
	public void Kick(final PlayerKickEvent event) {
		try {
			if (p.kdyzHracSeVyhodiPovolit)
				event.setLeaveMessage(p.nahradit(cm.config.getString("KdyzHracSe.Vyhodi.Zprava"), event.getPlayer().getName()));
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					delayedKick(event);
				}
			});
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
		
	}
}
