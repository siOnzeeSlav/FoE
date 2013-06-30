package eu.frelania.foe.main;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import com.iCo6.system.Account;
import com.iCo6.system.Accounts;
import eu.frelania.foe.utils.Replace;

public class GuiManager {
	private FoE foe;
	private PlayerManager player;
	public Score guiWeeks, guiDays, guiHours, guiPlayers, guiKilledPlayers, guiKilledMobs, guiKilledAnimals, guiDeaths, guiIConomy;

	public GuiManager(FoE plugin, PlayerManager playerManager) {
		foe = plugin;
		player = playerManager;
		
		foe.logDebug("GuiManager created for player: " + player.playerName);
		createGui();
	}

	public void createGui() {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = board.registerNewObjective("FoE_Gui_" + player.playerName, "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(Replace.playerName(foe.getConfigManager().config.getString("nahrano.gui.nadpis"), player.playerName));

		if (foe.getFeaturesManager().guiWeeks) {
			guiWeeks = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.tydny.zprava")));
			guiWeeks.setScore(0);
		}
		if (foe.getFeaturesManager().guiDays) {
			guiDays = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.dny.zprava")));
			guiDays.setScore(1);
		}
		if (foe.getFeaturesManager().guiHours) {
			guiHours = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.hodiny.zprava")));
			guiHours.setScore(2);
		}
		if (foe.getFeaturesManager().guiPlayers) {
			guiPlayers = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.pocetHracu.zprava")));
			guiPlayers.setScore(Bukkit.getOnlinePlayers().length);
		}
		if (foe.getFeaturesManager().guiKilledPlayers) {
			guiKilledPlayers = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.zabitoHracu.zprava")));
			guiKilledPlayers.setScore(player.getKilledPlayers());
		}
		if (foe.getFeaturesManager().guiKilledMobs) {
			guiKilledMobs = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.zabitoMobu.zprava")));
			guiKilledMobs.setScore(player.getKilledMobs());
		}
		if (foe.getFeaturesManager().guiKilledAnimals) {
			guiKilledAnimals = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.zabitoZvirat.zprava")));
			guiKilledAnimals.setScore(player.getKilledAnimals());
		}
		if (foe.getFeaturesManager().guiDeaths) {
			guiDeaths = objective.getScore(Bukkit.getOfflinePlayer(foe.getConfigManager().config.getString("nahrano.gui.pocetSmrti.zprava")));
			guiDeaths.setScore(player.getDeaths());
		}
		if (foe.getFeaturesManager().guiIConomy) {
			Account account = new Accounts().get(player.playerName);
			Double money = account.getHoldings().getBalance();
			int intMoney = money.intValue();
			guiIConomy.setScore(intMoney);
		}
		
		player.player.setScoreboard(board);
	}
}
