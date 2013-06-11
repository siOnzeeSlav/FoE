package main.commands;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;

import main.ConfigManager;
import main.FoE;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class cmdFOE implements CommandExecutor {
	public FoE				plugin;
	public ConfigManager	cm	= new ConfigManager();
	
	public cmdFOE(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("FoE")) && (args.length == 0)) {
			try {
				sender.sendMessage("------ " + ChatColor.GOLD + plugin.getDescription().getVersion() + ChatColor.WHITE + " ------");
				sender.sendMessage(cm.config.getString("Prikazy.AdminChat") + " [TEXT]  " + ChatColor.GOLD + "Psaní do adminchatu.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Ban") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Zakáže hráèovy pøístup na server.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Unban") + "  [HRAC] [DUVOD]  " + ChatColor.GOLD + "Povolí hráèovy pøístup na server.");
				sender.sendMessage(cm.config.getString("Prikazy.Manager.Kick") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Vyhodí hráèe ze serveru.");
				sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " ADD [SLOVO]  " + ChatColor.GOLD + "Pøidá slovo do cenzury.");
				sender.sendMessage(cm.config.getString("Prikazy.Cenzura") + " DEL [SLOVO]  " + ChatColor.GOLD + "Odstraní slovo z cenzury.");
				sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " ADD [CELE nebo VSUDE] [SLOVO] [SLOVO] [DÙVOD]  " + ChatColor.GOLD + "Pøidá slovo do gramatiky.");
				sender.sendMessage(cm.config.getString("Prikazy.Gramatika") + " DEL [CELE nebo VSUDE] [SLOVO] [SLOVO]  " + ChatColor.GOLD + "Odstraní slovo z gramatiky.");
				sender.sendMessage(cm.config.getString("Prikazy.Help") + " ZPRÁVA]  " + ChatColor.GOLD + "Napíše zprávu adminùm.");
				sender.sendMessage(cm.config.getString("Prikazy.VypnoutChat") + "   " + ChatColor.GOLD + "Vypne a Zapne chat.");
				sender.sendMessage(cm.config.getString("Prikazy.Nahranost") + " [HRÁÈ]  " + ChatColor.GOLD + "Zobrazí jak dlouho hráè hraje na serveru.");
				sender.sendMessage(cm.config.getString("Prikazy.Inventar") + " [HRÁÈ]  " + ChatColor.GOLD + "Zobrazí co má hráè v inventáøi.");
				sender.sendMessage(cm.config.getString("Prikazy.Teleport") + " [HRÁÈ]  " + ChatColor.GOLD + "Teleportuje na hráèe který je online.");
				sender.sendMessage(cm.config.getString("Prikazy.Oznameni") + " [TEXT]  " + ChatColor.GOLD + "Napíše oznámení s nastavením v configu.");
				sender.sendMessage(cm.config.getString("Prikazy.Reload") + " [TEXT]  " + ChatColor.GOLD + "Znovunaète nastavení configu..");
				sender.sendMessage("/foe mysqlupdate  " + ChatColor.GOLD + "Aktualizuje kompletnì databázi MySQL FoE.");
			} catch (Exception e) {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		if ((cmd.getName().equalsIgnoreCase("FoE")) && args.length >= 1 && (args[0].equalsIgnoreCase("mysqlupdate")) && sender.isOp()) {
			try {
				if (plugin.mysqlPovolit) {
					if (plugin.mysql.isOpen()) {
						File a = new File("plugins/FoE/uzivatele");
						File[] soubory = a.listFiles();
						for (File f : soubory) {
							YamlConfiguration aa = YamlConfiguration.loadConfiguration(f);
							String jmeno = odstranitYML(f.getName());
							long nahranost = aa.getLong("Nahrano");
							ResultSet rs = plugin.mysql.query("SELECT `hrac` FROM `FoE_Uzivatele` WHERE `hrac` = '" + jmeno + "'");
							if (rs.next()) {
								plugin.mysql.query("UPDATE `FoE_Uzivatele` SET `nahranost` = '" + nahranost + "' WHERE `hrac` = '" + jmeno + "'");
							} else {
								plugin.mysql.query("INSERT INTO `FoE_Uzivatele` (hrac,nahranost) VALUES ('" + jmeno + "', '" + nahranost + "') ON DUPLICATE KEY UPDATE `nahranost` = '" + nahranost + "'");
							}
						}
					}
					sender.sendMessage(ChatColor.GREEN + "Databáze byla úspìšnì aktualizována.");
				} else {
					sender.sendMessage("MySQL není povoleno!");
				}
			} catch (Exception e) {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		
		if ((cmd.getName().equalsIgnoreCase("FoE")) && args.length >= 1 && (args[0].equalsIgnoreCase("reload")) && sender.isOp()) {
			try {
				cm.configFile = new File("");
				cm.config = YamlConfiguration.loadConfiguration(cm.configFile);
				
			} catch (Exception e) {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		
		return false;
	}
	
	public String odstranitYML(String zCeho) {
		return zCeho = zCeho.replaceAll(".yml", "");
	}
}