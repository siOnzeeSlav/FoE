package main;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SchedulerManager {
	
	public int				minutesLeft		= 0;
	public int				minutesLeft2	= 0;
	public int				minutesLeft3	= 0;
	public int				minutesLeft4	= 0;
	public int				minutesLeft5	= 0;
	public Plugin			plugin;
	public Replaces			replace;
	public ConfigManager	cm;
	public ErrorManager		err;
	public FeaturesManager	fm;
	
	public SchedulerManager(Plugin plugin) {
		plugin = this.plugin;
		replace = new Replaces();
		cm = new ConfigManager();
		err = new ErrorManager();
		fm = new FeaturesManager(cm);
	}
	
	public void minute2() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				minutesLeft2 -= 1;
				if (minutesLeft2 > 0) {
					minute2();
				}
				
				if (minutesLeft2 == 0) {
					fm.zkontrolovatVerziPluginu();
					startLoop2(fm.vyhledavatAktualizaceCas);
				}
			}
		}, 1200L);
	}
	
	public void minute4() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				minutesLeft4 -= 1;
				if (minutesLeft4 > 0) {
					minute4();
				}
				
				if (minutesLeft4 == 0) {
					try {
						Bukkit.broadcastMessage(replace.Joke(cm.config.getString("jokes.Format")));
						startLoop4(fm.jokesInterval);
					} catch (Exception e) {
						err.postError(e);
					}
				}
			}
		}, 1200L);
	}
	
	public void startLoop4(int length) {
		minutesLeft4 = length;
		minute4();
	}
	
	public void startLoop2(int length) {
		minutesLeft2 = length;
		minute2();
	}
	
	public void minute5() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				minutesLeft5 -= 1;
				if (minutesLeft5 > 0) {
					minute5();
				}
				
				if (minutesLeft5 == 0) {
					try {
						List<String> list = cm.config.getStringList("autoZpravy.Zpravy");
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (!p.hasPermission("FoE.AutoZpravy.Bypass")) {
								Random rnd = new Random();
								p.sendMessage(replace.AutoMessage(list.get(rnd.nextInt(list.size())), p.getName()));
							}
						}
						startLoop5(fm.autoZpravyInterval);
					} catch (Exception e) {
						err.postError(e);
					}
				}
			}
		}, 20L);
	}
	
	public void startLoop5(int length) {
		minutesLeft5 = length;
		minute5();
	}
	
}
