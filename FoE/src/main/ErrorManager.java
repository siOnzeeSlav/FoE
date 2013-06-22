package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;

public class ErrorManager {
	
	public String	version	= new ConfigManager().config.getString("verze");
	
	public void postError(Exception e) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		Error(writer.toString());
	}
	
	public void Error(String message) {
		try {
			File u = new File("plugins/FoE/errors.log");
			FileWriter fw = new FileWriter(u, true);
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String time = sdf.format(date);
			pw.println("================== " + time + " - FoE: " + version + "\n" + "CB: " + Bukkit.getVersion() + "\n" + message + "\n==================\n");
			pw.flush();
			pw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
