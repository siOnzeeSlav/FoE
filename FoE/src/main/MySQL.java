package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	public Connection		con;
	public Statement		sta;
	public ConfigManager	cm;
	public ErrorManager		err;
	public String			hostname	= cm.config.getString("MySQL.hostname");
	public String			database	= cm.config.getString("MySQL.database");
	public String			username	= cm.config.getString("MySQL.username");
	public String			password	= cm.config.getString("MySQL.password");
	public int				port		= cm.config.getInt("MySQL.port");
	
	public MySQL() {
		try {
			cm = new ConfigManager();
			err = new ErrorManager();
			con = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
			sta = con.createStatement();
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public ResultSet query(String query) {
		try {
			if (!isOpen()) {
				open();
			}
			if (query.toLowerCase().startsWith("select")) {
				return sta.executeQuery(query);
			} else {
				sta.executeUpdate(query);
			}
		} catch (Exception e) {
			err.postError(e);
		}
		return null;
	}
	
	public void open() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, username, password);
			sta = con.createStatement();
			int val = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Uzivatele (id INT AUTO_INCREMENT, hrac VARCHAR(32), nahranost BIGINT(20), PRIMARY KEY (id))");
			int val2 = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Banlist (id INT AUTO_INCREMENT, hrac VARCHAR(32), admin VARCHAR(32), duvod text NOT NULL, datum BIGINT(20), typ VARCHAR(32), PRIMARY KEY (id))");
			int val3 = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Warpy (id INT AUTO_INCREMENT, warp VARCHAR(32), autor VARCHAR(32), datum BIGINT(20), typ VARCHAR(32),  PRIMARY KEY (id))");
			int val4 = sta.executeUpdate("CREATE TABLE IF NOT EXISTS FoE_Zpravy (id INT AUTO_INCREMENT, hrac VARCHAR(32), prijemce VARCHAR(32), zprava TEXT NOT NULL, datum BIGINT(20),  PRIMARY KEY (id))");
			if (val == 0)
				System.out.println("Tabulka 'Uzivatele' nebyla vytvorena.");
			else
				System.out.println("Tabulka 'Uzivatele' byla vytvorena.");
			
			if (val2 == 0)
				System.out.println("Tabulka 'Banlist' nebyla vytvorena.");
			else
				System.out.println("Tabulka 'Banlist' byla vytvorena.");
			
			if (val3 == 0)
				System.out.println("Tabulka 'Warpy' nebyla vytvorena.");
			else
				System.out.println("Tabulka 'Warpy' byla vytvorena.");
			
			if (val4 == 0)
				System.out.println("Tabulka 'Zpravy' nebyla vytvorena.");
			else
				System.out.println("Tabulka 'Zpravy' byla vytvorena.");
		} catch (SQLException e) {
			err.postError(e);
		}
	}
	
	public boolean isOpen() {
		try {
			if (con.isClosed()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			err.postError(e);
		}
		return false;
	}
}
