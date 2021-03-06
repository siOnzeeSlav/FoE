package main.commands;

import java.io.File;
import java.sql.ResultSet;

import main.ConfigManager;
import main.ErrorManager;
import main.FeaturesManager;
import main.MySQL;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class cmdFOE implements CommandExecutor {
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	public MySQL			mysql;
	
	public cmdFOE() {
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager();
		if (fm.mysqlIsEnabled)
			mysql = new MySQL();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("FoE")) && (args.length == 0)) {
			try {
				sender.sendMessage("------ " + ChatColor.GOLD + fm.version + ChatColor.WHITE + " ------");
				sender.sendMessage(cm.config.getString("Prikazy.AdminChat") + " [TEXT]  " + ChatColor.GOLD + "Psani do adminchatu.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Ban") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Zakaze hracovy pristup na server.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Unban") + "  [HRAC] [DUVOD]  " + ChatColor.GOLD + "Povoli hracovy pristup na server.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Kick") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Vyhodi hrace ze serveru.");
				sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " ADD [SLOVO]  " + ChatColor.GOLD + "Prida slovo do cenzury.");
				sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " DEL [SLOVO]  " + ChatColor.GOLD + "Odstrani slovo z cenzury.");
				sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " ADD [CELE nebo VSUDE] [SLOVO] [SLOVO] [DuVOD]  " + ChatColor.GOLD + "Prida slovo do gramatiky.");
				sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " DEL [CELE nebo VSUDE] [SLOVO] [SLOVO]  " + ChatColor.GOLD + "Odstrani slovo z gramatiky.");
				sender.sendMessage(cm.config.getString("Prikazy.Help") + " ZPRaVA]  " + ChatColor.GOLD + "Napise zpravu adminum.");
				sender.sendMessage(cm.config.getString("Prikazy.VypnoutChat") + "   " + ChatColor.GOLD + "Vypne a Zapne chat.");
				sender.sendMessage(cm.config.getString("Prikazy.Nahranost") + " [HRac]  " + ChatColor.GOLD + "Zobrazi jak dlouho hrac hraje na serveru.");
				sender.sendMessage(cm.config.getString("Prikazy.Inventar") + " [HRac]  " + ChatColor.GOLD + "Zobrazi co ma hrac v inventari.");
				sender.sendMessage(cm.config.getString("Prikazy.Teleport") + " [HRac]  " + ChatColor.GOLD + "Teleportuje na hrace ktery je online.");
				sender.sendMessage(cm.config.getString("Prikazy.Oznameni") + " [TEXT]  " + ChatColor.GOLD + "Napise oznameni s nastavenim v configu.");
				//sender.sendMessage(cm.config.getString("Prikazy.Reload") + " [TEXT]  " + ChatColor.GOLD + "Znovunacte nastaveni configu..");
				sender.sendMessage("/foe mysqlupdate  " + ChatColor.GOLD + "Aktualizuje kompletne databazi MySQL FoE.");
			} catch (Exception e) {
				err.postError(e);
			}
		}
		if ((cmd.getName().equalsIgnoreCase("FoE")) && args.length >= 1 && (args[0].equalsIgnoreCase("mysqlupdate")) && sender.isOp()) {
			try {
				if (fm.mysqlIsEnabled) {
					if (mysql.isOpen()) {
						File a = new File("plugins/FoE/uzivatele");
						File[] soubory = a.listFiles();
						for (File f : soubory) {
							YamlConfiguration aa = YamlConfiguration.loadConfiguration(f);
							String jmeno = odstranitYML(f.getName());
							long nahranost = aa.getLong("Nahrano");
							ResultSet rs = mysql.query("SELECT `hrac` FROM `FoE_Uzivatele` WHERE `hrac` = '" + jmeno + "'");
							if (rs.next()) {
								mysql.query("UPDATE `FoE_Uzivatele` SET `nahranost` = '" + nahranost + "' WHERE `hrac` = '" + jmeno + "'");
							} else {
								mysql.query("INSERT INTO `FoE_Uzivatele` (hrac,nahranost) VALUES ('" + jmeno + "', '" + nahranost + "') ON DUPLICATE KEY UPDATE `nahranost` = '" + nahranost + "'");
							}
						}
					}
					sender.sendMessage(ChatColor.GREEN + "Databaze byla uspesne aktualizovana.");
				} else {
					sender.sendMessage("MySQL neni povoleno!");
				}
			} catch (Exception e) {
				err.postError(e);
			}
		}
		
		if ((cmd.getName().equalsIgnoreCase("FoE")) && args.length >= 1 && (args[0].equalsIgnoreCase("reload")) && sender.isOp()) {
			try {
				cm.configFile = new File("");
				cm.config = YamlConfiguration.loadConfiguration(cm.configFile);
				
			} catch (Exception e) {
				err.postError(e);
			}
		}
		
		return false;
	}
	
	public String odstranitYML(String zCeho) {
		return zCeho = zCeho.replaceAll(".yml", "");
	}
}