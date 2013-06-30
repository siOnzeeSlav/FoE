package eu.frelania.foe.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class ErrorManager {

	private FoE foe;

	public ErrorManager(FoE plugin) {
		foe = plugin;
	}

	public void log(Level level, Exception e) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		e.printStackTrace(printWriter);
		if(level.intValue() < Level.SEVERE.intValue()){
			error(writer.toString());
		}
		foe.getLogger().log(level, writer.toString());
	}

	public void log(Level level, Exception e, String reason) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		e.printStackTrace(printWriter);
		
		String msg = writer.toString() + "\nPravdepodobne duvodem je: " + reason;
		if(level.intValue() < Level.SEVERE.intValue()){
			error(msg);
		}
		foe.getLogger().log(level, msg);
	}

	public void log(Level level, String msg) {
		if(level.intValue() >= 900){
			error(msg);
		}
		foe.getLogger().log(level, msg);
	}

	private void error(String message) {
		try {
			File u = new File("plugins/FoE/errors.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("######################################################################################");
			pw.println(" Time: " + time);
			pw.println(" FoE: " + foe.getDescription().getVersion());
			pw.println(" CraftBukkit: " + Bukkit.getVersion());
			pw.println(" Bukkit: " + Bukkit.getBukkitVersion());
			pw.println("--------------------------------------------------------------------------------------");
			pw.println("\n" + message + "\n");
			pw.println("--------------------------------------------------------------------------------------");
			pw.println(" Nahlaste prosim tuto chybu co nejdrive na: http://grief.cz/FoE-errors - Diky siOnzee");
			pw.println(" Tema pluginu na FH foru: http://grief.cz/FoE");
			pw.println("");
			pw.flush();
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
