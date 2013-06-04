package main.commands;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.ResultSet;

import main.FoE;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class cmdFOE implements CommandExecutor {
	public FoE	plugin;
	
	public cmdFOE(FoE plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender odesilatel, Command prikaz, String label, String[] args) {
		if ((prikaz.getName().equalsIgnoreCase("FoE")) && (args.length == 0)) {
			try {
				odesilatel.sendMessage("------ " + ChatColor.GOLD + plugin.getDescription().getVersion() + ChatColor.WHITE + " ------");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.AdminChat") + " [TEXT]  " + ChatColor.GOLD + "Psaní do adminchatu.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Manager.Ban") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Zakáže hráèovy pøístup na server.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Manager.Unban") + "  [HRAC] [DUVOD]  " + ChatColor.GOLD + "Povolí hráèovy pøístup na server.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Manager.Kick") + " [HRAC] [DUVOD]  " + ChatColor.GOLD + "Vyhodí hráèe ze serveru.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Cenzura") + " ADD [SLOVO]  " + ChatColor.GOLD + "Pøidá slovo do cenzury.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Cenzura") + " DEL [SLOVO]  " + ChatColor.GOLD + "Odstraní slovo z cenzury.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " ADD [CELE nebo VSUDE] [SLOVO] [SLOVO] [DÙVOD]  " + ChatColor.GOLD + "Pøidá slovo do gramatiky.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Gramatika") + " DEL [CELE nebo VSUDE] [SLOVO] [SLOVO]  " + ChatColor.GOLD + "Odstraní slovo z gramatiky.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Help") + " ZPRÁVA]  " + ChatColor.GOLD + "Napíše zprávu adminùm.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.VypnoutChat") + "   " + ChatColor.GOLD + "Vypne a Zapne chat.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Nahranost") + " [HRÁÈ]  " + ChatColor.GOLD + "Zobrazí jak dlouho hráè hraje na serveru.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Inventar") + " [HRÁÈ]  " + ChatColor.GOLD + "Zobrazí co má hráè v inventáøi.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Teleport") + " [HRÁÈ]  " + ChatColor.GOLD + "Teleportuje na hráèe který je online.");
				odesilatel.sendMessage(plugin.config.getString("Prikazy.Oznameni") + " [TEXT]  " + ChatColor.GOLD + "Napíše oznámení s nastavením v configu.");
				odesilatel.sendMessage("/foe mysqlupdate  " + ChatColor.GOLD + "Aktualizuje kompletnì databázi MySQL FoE.");
			} catch (Exception e) {
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				plugin.Error(writer.toString());
			}
		}
		if ((prikaz.getName().equalsIgnoreCase("FoE")) && args.length >= 1 && (args[0].equalsIgnoreCase("mysqlupdate")) && odesilatel.isOp()) {
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
					odesilatel.sendMessage(ChatColor.GREEN + "Databáze byla úspìšnì aktualizována.");
				} else {
					odesilatel.sendMessage("MySQL není povoleno!");
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
	
	public String odstranitYML(String zCeho) {
		return zCeho = zCeho.replaceAll(".yml", "");
	}
}