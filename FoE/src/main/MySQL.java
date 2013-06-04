package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQL {
	public FoE					plugin;
	public Connection			con;
	public Statement			sta;
	public File					configFile	= new File("plugins/FoE/config.yml");
	public YamlConfiguration	config		= YamlConfiguration.loadConfiguration(configFile);
	
	public MySQL(FoE plugin) {
		plugin = this.plugin;
	}
	
	public ResultSet query(String query) {
		try {
			if (query.toLowerCase().startsWith("select")) {
				return sta.executeQuery(query);
			} else {
				sta.executeUpdate(query);
			}
		} catch (SQLException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
		return null;
	}
	
	public void Error(String message) {
		try {
			File u = new File("plugins/FoE/errors.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("================== " + time + " - FoE: MYSQL!\n" + "CB: " + Bukkit.getVersion() + "\n" + message + "\n==================\n");
			pw.flush();
			pw.close();
			System.out.println("[FoE] ERROR!");
			System.out.println("===========================");
			System.out.println("Prekopirujte obsah souboru errors.log do prispevku.");
			System.out.println("===========================");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void open() {
		try {
			String hostname = config.getString("MySQL.hostname");
			String database = config.getString("MySQL.database");
			String username = config.getString("MySQL.username");
			String password = config.getString("MySQL.password");
			int port = config.getInt("MySQL.port");
			con = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
			sta = con.createStatement();
			int val = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Uzivatele (id INT AUTO_INCREMENT, hrac VARCHAR(32), nahranost BIGINT(20), PRIMARY KEY (id))");
			int val2 = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Banlist (id INT AUTO_INCREMENT, hrac VARCHAR(32), admin VARCHAR(32), duvod text NOT NULL, datum BIGINT(20), typ VARCHAR(32), PRIMARY KEY (id))");
			if (val == 0)
				System.out.println("Tabulka 'Uzivatele' byla zkontrolovana.");
			else
				System.out.println("Tabulka 'Uzivatele' byla vytvorena.");
			
			if (val2 == 0)
				System.out.println("Tabulka 'Banlist' byla zkontrolovana.");
			else
				System.out.println("Tabulka 'Banlist' byla vytvorena.");
		} catch (SQLException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
	}
	
	public boolean isOpen() {
		try {
			if (con.isClosed()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			Error(writer.toString());
		}
		return false;
	}
}
