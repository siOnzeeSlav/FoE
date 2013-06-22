package main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;

public class GUIManager {
	
	public PlayerManager	pm;
	public boolean			weeks, days, hours, totalPlayers, iconomy, killedPlayers, killedMobs, killedAnimals, totalDeaths;
	public ConfigManager	cm	= new ConfigManager();
	public Replaces			replace;
	public ErrorManager		err;
	
	public GUIManager(String playerName) {
		pm = new PlayerManager(Bukkit.getPlayer(playerName));
		replace = new Replaces(Bukkit.getPlayer(playerName));
		err = new ErrorManager();
	}
	
	public GUIManager(Player player) {
		pm = new PlayerManager(player);
		replace = new Replaces(player);
		err = new ErrorManager();
	}
	
	public GUIManager(Player player, boolean weeks, boolean days, boolean hours, boolean totalPlayers, boolean iconomy, boolean killedPlayers, boolean killedMobs, boolean kolledAnimals, boolean totalDeaths) {
		pm = new PlayerManager(player);
		replace = new Replaces(player);
		err = new ErrorManager();
	}
	
	public void aktualizovatGUI() {
		try {
			if (pm.getPlayer() != null) {
				pm.loadPlayer();
				long[] cas;
				if (pm.isPlayerRegistered())
					cas = getCorrectFormat((System.currentTimeMillis() - pm.playedTime.get(pm.getPlayerName())) + pm.getPlayerPlayedTime());
				else
					cas = getCorrectFormat(pm.getPlayerPlayedTime());
				
				Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective objective = board.registerNewObjective(pm.getPlayerName(), "dummy");
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
				objective.setDisplayName(replace.PlayerName(cm.config.getString("Ostatni.Nahranost.GUI.Nadpis"), pm.getPlayerName()));
				Score score = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.Tydny")));
				Score score2 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.Dny")));
				Score score3 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.Hodiny")));
				Score score4 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.PocetHracu")));
				Score score5 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.iConomy")));
				Score score6 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.ZabitoHracu")));
				Score score7 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.ZabitoMobu")));
				Score score8 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.ZabitoZvirat")));
				Score score9 = objective.getScore(Bukkit.getOfflinePlayer(cm.config.getString("Ostatni.Nahranost.GUI.PocetSmrti")));
				
				if (weeks)
					score.setScore((int) cas[4]);
				if (days)
					score2.setScore((int) cas[3]);
				if (hours)
					score3.setScore((int) cas[2]);
				if (totalPlayers)
					score4.setScore(Bukkit.getOnlinePlayers().length);
				if (iconomy) {
					Account account = new Accounts().get(pm.getPlayerName());
					Double money = account.getHoldings().getBalance();
					int intMoney = money.intValue();
					score5.setScore(intMoney);
				}
				if (killedPlayers)
					score6.setScore(pm.getTotalKilledsPlayers());
				if (killedMobs)
					score7.setScore(pm.getTotalKilledsMobs());
				if (killedAnimals)
					score8.setScore(pm.getTotalKilledsAnimals());
				if (totalDeaths)
					score9.setScore(pm.getTotalDeaths());
				pm.getPlayer().setScoreboard(board);
			}
		} catch (Exception e) {
			err.postError(e);
		}
	}
	
	public long[] getCorrectFormat(long Long) {
		long sekundy = Long / 1000L;
		long minuty = 0L;
		long hodiny = 0L;
		long dny = 0L;
		long tydny = 0L;
		
		while (sekundy > 60L) {
			minuty += 1L;
			sekundy -= 60L;
		}
		
		while (minuty > 60L) {
			hodiny += 1L;
			minuty -= 60L;
		}
		
		while (hodiny > 24L) {
			dny += 1L;
			hodiny -= 24L;
		}
		
		while (dny > 7L) {
			tydny += 1L;
			dny -= 7L;
		}
		return new long[] { sekundy, minuty, hodiny, dny, tydny };
	}
	
}
