package main.events;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onInventoryDrag implements Listener {
	
	public FoE	p;
	
	public onInventoryDrag(FoE plugin) {
		this.p = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryDragEvent event) {
		Inventory inv = event.getInventory();
		String title = inv.getTitle();
		
		try {
			String[] args = title.split(" ");
			if (title.startsWith("Inventar ")) {
				Player player = (Player) event.getWhoClicked();
				if (player.hasPermission("FoE.Inventar.Upravovat")) {
					Player target = Bukkit.getPlayer(args[1]);
					if (target != null) {
						Inventory targetinv = target.getInventory();
						Inventory playerinv = player.getInventory();
						playerinv.addItem(event.getCursor());
						targetinv.removeItem(event.getCursor());
						player.updateInventory();
						target.updateInventory();
						event.setCursor(new ItemStack(Material.AIR));
					}
				} else {
					event.setCancelled(true);
				}
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			p.Error(writer.toString());
		}
		
	}
}
