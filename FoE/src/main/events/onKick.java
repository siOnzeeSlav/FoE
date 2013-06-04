package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class onKick implements Listener {
	public FoE	p;
	
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
					p.aktualizovatGUI(PL);
				}
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
				event.setLeaveMessage(p.nahradit(p.config.getString("KdyzHracSe.Vyhodi.Zprava"), event.getPlayer().getName()));
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
