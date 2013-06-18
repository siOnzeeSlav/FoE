package main.commands;

import java.io.File;

import main.BanManager;
import main.ConfigManager;
import main.ErrorManager;
import main.FoE;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class cmdINF implements CommandExecutor {
	public FoE				plugin;
	public BanManager		bm	= new BanManager();
	public ConfigManager	cm	= new ConfigManager();
	public ErrorManager		err	= new ErrorManager();
	
	public cmdINF(FoE plugin) {
		this.plugin = plugin;
	}
	
	public void getInfo(Player sender, String targetName) {
		Player target = Bukkit.getPlayer(targetName);
		if (target != null) {
			if (targetName == target.getName()) {
				plugin.uzivatel(targetName);
				sender.sendMessage("----- " + target.getDisplayName() + " -----");
				long[] cas = plugin.spravnyFormat(System.currentTimeMillis() - plugin.nahranyCas.get(target.getName()) + plugin.uziv.getLong("Nahrano"));
				sender.sendMessage("Nahrano: " + cas[4] + " tydnu " + cas[3] + " dnu " + cas[2] + " hodin " + cas[1] + " minut " + cas[0] + " sekund");
				if (sender.isOp())
					sender.sendMessage("IP: " + plugin.uziv.get("IP"));
				if (sender.isOp())
					sender.sendMessage("lastIP: " + target.getAddress().getHostName());
				if (bm.isBanned(targetName)) {
					sender.sendMessage("Ban: Ano");
					sender.sendMessage("Duvod: " + plugin.uziv.getString("banReason"));
				}
				sender.sendMessage("Zabito Hracu: " + plugin.uziv.getInt("ZabitoHracu"));
				sender.sendMessage("Zabito Mobu: " + plugin.uziv.getInt("ZabitoMobu"));
				sender.sendMessage("Zabio Zvirat: " + plugin.uziv.getInt("ZabitoZvirat"));
				sender.sendMessage("Pocet umrti: " + plugin.uziv.getInt("PocetSmrti"));
			} else {
				getInfoOfflinePlayer(sender, targetName);
			}
		} else {
			getInfoOfflinePlayer(sender, targetName);
		}
	}
	
	public void getInfoOfflinePlayer(Player sender, String targetName) {
		plugin.uzivFile = new File("plugins/FoE/uzivatele/" + targetName + ".yml");
		plugin.uziv = YamlConfiguration.loadConfiguration(plugin.uzivFile);
		if (!plugin.uzivFile.exists()) {
			sender.sendMessage("Tento hrac neexistuje!");
		} else {
			sender.sendMessage("----- " + targetName + " -----");
			long[] cas = plugin.spravnyFormat(plugin.uziv.getLong("Nahrano"));
			sender.sendMessage("Nahrano: " + cas[4] + " tydnu " + cas[3] + " dnu " + cas[2] + " hodin " + cas[1] + " minut " + cas[0] + " sekund");
			if (sender.isOp())
				sender.sendMessage("IP: " + plugin.uziv.get("IP"));
			if (sender.isOp())
				sender.sendMessage("lastIP: " + plugin.uziv.get("lastIP"));
			if (bm.isBanned(targetName)) {
				sender.sendMessage("Ban: Ano");
				sender.sendMessage("Duvod: " + plugin.uziv.getString("banReason"));
			}
			sender.sendMessage("Zabito Hracu: " + plugin.uziv.getInt("ZabitoHracu"));
			sender.sendMessage("Zabito Mobu: " + plugin.uziv.getInt("ZabitoMobu"));
			sender.sendMessage("Zabio Zvirat: " + plugin.uziv.getInt("ZabitoZvirat"));
			sender.sendMessage("Pocet umrti: " + plugin.uziv.getInt("PocetSmrti"));
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("infcmd")) {
			try {
				Player player = (Player) sender;
				String playerName = player.getName();
				if (sender.hasPermission("FoE.Informace")) {
					if (args.length < 1) {
						getInfo(player, playerName);
					} else if (args.length > 0) {
						getInfo(player, args[0]);
					}
				} else {
					sender.sendMessage(plugin.nahradit(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		
		return false;
	}
}