package fr.lowtix.palamod.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.users.ModUser;

public class DeathListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent event) {
		
		Player player = event.getEntity();
		ModUser user = PalaMod.getInstance().getModUser(player);
		
		user.getSavedInventory().save();
		
	}

}
