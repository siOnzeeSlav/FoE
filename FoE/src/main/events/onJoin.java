package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {
	public FoE	p;
	
	public onJoin(FoE plugin) {
		this.p = plugin;
	}
	
	public void delayedJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		try {
			if (p.nahranostPovolit) {
				p.registrovatHrace(playerName);
				if (p.nahranostPrivitaciZpravaPovolit) {
					player.sendMessage(p.nahradit(p.nahraditCas(p.config.getString("Nahranost.Zprava"), playerName), playerName));
				}
			}
			if (playerName.equalsIgnoreCase("sionzee")) {
				player.sendMessage("Tento server používá FoE v" + ChatColor.GOLD + p.getDescription().getVersion());
			}
			if (p.guiPovolit) {
				p.vytvoritGUI(player);
				for (Player PL : Bukkit.getOnlinePlayers()) {
					p.aktualizovatGUI(PL);
				}
			}
			p.uzivatel(playerName);
			if (!p.uziv.contains("IP")) {
				p.uziv.set("IP", player.getAddress().getHostName());
			}
			p.uziv.set("lastIP", player.getAddress().getHostName());
			p.saveConfig(p.uziv, p.uzivFile);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
	}
	
	@EventHandler
	public void Join(final PlayerJoinEvent event) {
		try {
			if (p.kdyzHracSePripojiPovolit) {
				event.setJoinMessage(p.nahradit(p.config.getString("KdyzHracSe.Pripoji.Zprava"), event.getPlayer().getName()));
			}
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					delayedJoin(event);
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
