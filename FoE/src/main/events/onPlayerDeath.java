package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
	public FoE	p;
	
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
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
	}
}
