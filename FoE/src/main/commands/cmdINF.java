package main.commands;

import main.BanManager;
import main.ConfigManager;
import main.ErrorManager;
import main.GUIManager;
import main.PlayerManager;
import main.Replaces;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cmdINF implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public BanManager		bm;
	public PlayerManager	pm;
	public GUIManager		gm;
	
	public cmdINF() {
		cm = new ConfigManager();
		err = new ErrorManager();
		bm = new BanManager();
	}
	
	public void getInfo(Player sender, String targetName) {
		Player target = Bukkit.getPlayer(targetName);
		if (target != null) {
			if (targetName == target.getName()) {
				pm = new PlayerManager(target);
				pm.loadPlayer();
				gm = new GUIManager(target);
				sender.sendMessage("----- " + target.getDisplayName() + " -----");
				long[] cas = gm.getCorrectFormat(System.currentTimeMillis() - pm.playedTime.get(target.getName()) + pm.getPlayerPlayedTime());
				sender.sendMessage("Nahrano: " + cas[4] + " tydnu " + cas[3] + " dnu " + cas[2] + " hodin " + cas[1] + " minut " + cas[0] + " sekund");
				if (sender.isOp())
					sender.sendMessage("IP: " + pm.uziv.get("IP"));
				if (sender.isOp())
					sender.sendMessage("lastIP: " + target.getAddress().getHostName());
				if (bm.isBanned(targetName)) {
					sender.sendMessage("Ban: Ano");
					sender.sendMessage("Duvod: " + pm.uziv.getString("banReason"));
				}
				sender.sendMessage("Zabito Hracu: " + pm.uziv.getInt("ZabitoHracu"));
				sender.sendMessage("Zabito Mobu: " + pm.uziv.getInt("ZabitoMobu"));
				sender.sendMessage("Zabio Zvirat: " + pm.uziv.getInt("ZabitoZvirat"));
				sender.sendMessage("Pocet umrti: " + pm.uziv.getInt("PocetSmrti"));
			} else {
				getInfoOfflinePlayer(sender, targetName);
			}
		} else {
			getInfoOfflinePlayer(sender, targetName);
		}
	}
	
	public void getInfoOfflinePlayer(Player sender, String targetName) {
		pm = new PlayerManager(targetName);
		pm.loadPlayer();
		gm = new GUIManager(targetName);
		if (!pm.uzivFile.exists()) {
			sender.sendMessage("Tento hrac neexistuje!");
		} else {
			sender.sendMessage("----- " + targetName + " -----");
			long[] cas = gm.getCorrectFormat(pm.uziv.getLong("Nahrano"));
			sender.sendMessage("Nahrano: " + cas[4] + " tydnu " + cas[3] + " dnu " + cas[2] + " hodin " + cas[1] + " minut " + cas[0] + " sekund");
			if (sender.isOp())
				sender.sendMessage("IP: " + pm.uziv.get("IP"));
			if (sender.isOp())
				sender.sendMessage("lastIP: " + pm.uziv.get("lastIP"));
			if (bm.isBanned(targetName)) {
				sender.sendMessage("Ban: Ano");
				sender.sendMessage("Duvod: " + pm.uziv.getString("banReason"));
			}
			sender.sendMessage("Zabito Hracu: " + pm.uziv.getInt("ZabitoHracu"));
			sender.sendMessage("Zabito Mobu: " + pm.uziv.getInt("ZabitoMobu"));
			sender.sendMessage("Zabio Zvirat: " + pm.uziv.getInt("ZabitoZvirat"));
			sender.sendMessage("Pocet umrti: " + pm.uziv.getInt("PocetSmrti"));
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
					sender.sendMessage(new Replaces(playerName).PlayerName(cm.config.getString("Ostatni.KdyzNemaOpravneni"), playerName));
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		
		return false;
	}
}