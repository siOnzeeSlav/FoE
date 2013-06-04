package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class onInventoryDrag implements Listener {
	
	public FoE	p;
	
	public onInventoryDrag(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryDragEvent event) {
		Inventory inv = event.getInventory();
		String title = inv.getTitle();
		
		try {
			if (title.startsWith("Inventar ")) {
				
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
		
	}
}
