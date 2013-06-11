package main.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class cmdINV implements CommandExecutor {
	public FoE	plugin;
	
	public cmdINV(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("invcmd")) {
			try {
				if (sender.hasPermission("FoE.Inventar")) {
					if (args.length > 0) {
						
						if (!(sender instanceof Player))
							return true;
						
						Player player = (Player) sender;
						Player target = Bukkit.getPlayer(args[0]);
						if (target != null) {
							if (target.getName() == player.getName()) {
								player.sendMessage("Nemuzes otevrit svuj inventar! Pouzij tlacitko E.");
							} else {
								openInv(target, player);
							}
						} else {
							sender.sendMessage(args[0] + " je offline.");
						}
					} else {
						sender.sendMessage("Musis zadat jmeno hrace.");
					}
				}
			} catch (Exception e) {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		return false;
	}
	
	public void openInv(Player target, Player viewer) {
		
		PlayerInventory targetInv = target.getInventory();
		String targetName = target.getName();
		ItemStack[] contents = new ItemStack[40];
		ItemStack[] armor = targetInv.getArmorContents();
		
		for (int i = 0; i < 36; i++)
			contents[i] = targetInv.getItem(i);
		
		for (int i = 0; i < 4; i++)
			contents[36 + i] = armor[i];
		
		Inventory inv = Bukkit.createInventory(viewer, 9 * 5, "Inventar " + targetName);
		
		for (int i = 0; i < contents.length; i++)
			inv.setItem(i, contents[i]);
		
		viewer.openInventory(inv);
		
	}
	
}