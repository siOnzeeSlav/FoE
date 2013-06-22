package main.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class onPlayerCommands implements Listener {
	
	@EventHandler
	public void onPlayerPreProcessCommand(PlayerCommandPreprocessEvent event) {
		String vysledek = "";
		String args[] = event.getMessage().split(" ");
		for (int i = 1; i < args.length; i++) {
			vysledek = (vysledek + (i > 1 ? " " : "") + args[i]);
		}
		
		if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.AdminChat"))) {
			if (adminChatPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "acmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Ban"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "bancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Unban"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "unbancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Kick"))) {
			if (managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "kickcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Cenzura"))) {
			if (cenzuraPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "cenzuracmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Gramatika"))) {
			if (gramatikaPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "gramatikacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Help"))) {
			if (zpravaAdminum) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "helpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.VypnoutChat"))) {
			if (vypnoutChatPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "chatcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Nahranost"))) {
			if (nahranostPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "infcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Inventar"))) {
			if (inventarPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "invcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Teleport"))) {
			if (teleportPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "tpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Oznameni"))) {
			if (oznameniPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "zpravacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Clear"))) {
			if (clearChat) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "clearcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Vtip"))) {
			if (jokesPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "vtipcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Warp"))) {
			if (warpPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "warpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Msg"))) {
			if (msgPovolit) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "msgcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Creative"))) {
			if (herniRezimy) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "creativecmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Survival"))) {
			if (herniRezimy) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "survivalcmd " + vysledek);
				event.setCancelled(true);
			}
		}
	}
	
}
