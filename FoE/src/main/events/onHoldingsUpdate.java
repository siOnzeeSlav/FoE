package main.events;

import main.ErrorManager;
import main.GUIManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.iCo6.system.events.HoldingsUpdate;

public class onHoldingsUpdate implements Listener {
	public ErrorManager	err	= new ErrorManager();
	
	@EventHandler
	public void onBalance(HoldingsUpdate event) {
		try {
			GUIManager gm = new GUIManager(event.getAccountName());
			gm.aktualizovatGUI();
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
