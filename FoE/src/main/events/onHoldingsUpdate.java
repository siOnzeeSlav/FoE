package main.events;

import main.ErrorManager;
import main.FoE;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.iCo6.system.events.HoldingsUpdate;

public class onHoldingsUpdate implements Listener {
	public FoE			p;
	public ErrorManager	err	= new ErrorManager();
	
	public onHoldingsUpdate(FoE plugin) {
		this.p = plugin;
	}
	
	@EventHandler
	public void onBalance(HoldingsUpdate event) {
		try {
			p.aktualizovatGUI(event.getAccountName());
		} catch (Exception e) {
			err.postError(e);
		}
	}
}
