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
			if (!p.uziv.contains("IP"))
				p.uziv.set("IP", player.getAddress().getHostName());
			
			p.uziv.set("lastIP", player.getAddress().getHostName());
			
			if (!p.uziv.contains("ZabitoHracu"))
				p.uziv.set("ZabitoHracu", 0);
			
			if (p.debug)
				Bukkit.broadcastMessage(playerName + " - ZabitoHracu - " + p.uziv.getInt("ZabitoHracu"));
			
			if (!p.uziv.contains("ZabitoMobu"))
				p.uziv.set("ZabitoMobu", 0);
			
			if (p.debug)
				Bukkit.broadcastMessage(playerName + " - ZabitoMobu - " + p.uziv.getInt("ZabitoMobu"));
			
			if (!p.uziv.contains("PocetSmrti"))
				p.uziv.set("PocetSmrti", 0);
			
			if (p.debug)
				Bukkit.broadcastMessage(playerName + " - PocetSmrti - " + p.uziv.getInt("PocetSmrti"));
			
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
			Player player = event.getPlayer();
			String playerName = player.getName();
			String worldName = player.getLocation().getWorld().getName();
			if (p.kdyzHracSePripojiPovolit) {
				event.setJoinMessage(p.nahradit(p.config.getString("KdyzHracSe.Pripoji.Zprava"), event.getPlayer().getName()));
			}
			if (p.uvitaciZpravaPovolit)
				event.getPlayer().sendMessage(p.replaceWelcomeMessage(p.config.getString("uvitaciZprava.Zprava"), playerName, worldName));
			
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
