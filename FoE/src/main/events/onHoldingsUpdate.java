package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.iCo6.system.events.HoldingsUpdate;

public class onHoldingsUpdate implements Listener {
	public FoE	p;
	
	public onHoldingsUpdate(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onBalance(HoldingsUpdate event) {
		try {
			p.aktualizovatGUI(event.getAccountName());
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
	}
}
