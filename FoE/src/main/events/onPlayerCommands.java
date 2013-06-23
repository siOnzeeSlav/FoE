package main.events;

import main.ConfigManager;
import main.FeaturesManager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class onPlayerCommands implements Listener {
	
	public ConfigManager	cm;
	public FeaturesManager	fm;
	
	public onPlayerCommands() {
		cm = new ConfigManager();
		fm = new FeaturesManager();
	}
	
	@EventHandler
	public void onPlayerPreProcessCommand(PlayerCommandPreprocessEvent event) {
		String vysledek = "";
		String args[] = event.getMessage().split(" ");
		for (int i = 1; i < args.length; i++) {
			vysledek = (vysledek + (i > 1 ? " " : "") + args[i]);
		}
		
		if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.AdminChat"))) {
			if (fm.adminChatIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "acmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Ban"))) {
			if (fm.managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "bancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Unban"))) {
			if (fm.managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "unbancmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Manager.Kick"))) {
			if (fm.managerBan) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "kickcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Cenzura"))) {
			if (fm.cenzuraIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "cenzuracmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Gramatika"))) {
			if (fm.gramatikaIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "gramatikacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Help"))) {
			if (fm.zpravaAdminum) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "helpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.VypnoutChat"))) {
			if (fm.vypnoutChatIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "chatcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Nahranost"))) {
			if (fm.nahranostIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "infcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Inventar"))) {
			if (fm.inventarIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "invcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Teleport"))) {
			if (fm.teleportIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "tpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Oznameni"))) {
			if (fm.oznameniIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "zpravacmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Clear"))) {
			if (fm.clearChat) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "clearcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Vtip"))) {
			if (fm.jokesIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "vtipcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Warp"))) {
			if (fm.warpIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "warpcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Msg"))) {
			if (fm.msgIsEnabled) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "msgcmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Creative"))) {
			if (fm.herniRezimy) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "creativecmd " + vysledek);
				event.setCancelled(true);
			}
		} else if (args[0].equalsIgnoreCase(cm.config.getString("Prikazy.Survival"))) {
			if (fm.herniRezimy) {
				Bukkit.getServer().dispatchCommand(event.getPlayer(), "survivalcmd " + vysledek);
				event.setCancelled(true);
			}
		}
	}
	
}
