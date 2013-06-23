package main.events;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onInventoryClick implements Listener {
	
	public ErrorManager		err;
	public ConfigManager	cm;
	public FeaturesManager	fm;
	
	public onInventoryClick() {
		err = new ErrorManager();
		cm = new ConfigManager();
		fm = new FeaturesManager(cm);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try {
			if (fm.inventarIsEnabled) {
				Inventory inv = event.getInventory();
				String title = inv.getTitle();
				String[] args = title.split(" ");
				if (title.startsWith("Inventar ")) {
					Player target = Bukkit.getPlayer(args[1]);
					if (target != null) {
						Inventory invt = target.getInventory();
						Player player = (Player) event.getWhoClicked();
						if (player.hasPermission("FoE.Inventar.Upravovat")) {
							if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
								invt.removeItem(event.getCursor());
								target.updateInventory();
								player.updateInventory();
								event.setCursor(new ItemStack(Material.AIR));
							}
						} else {
							event.setCancelled(true);
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			err.postError(e);
		}
		
	}
}
