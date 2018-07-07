package fr.lowtix.palamod.listeners;

import org.bukkit.Bukkit;

import org.bukkit.plugin.PluginManager;

import fr.lowtix.palamod.PalaMod;

public class Listeners {

	public void init() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new DeathListener(), PalaMod.getInstance());
		pm.registerEvents(new BlockListener(), PalaMod.getInstance());
	}

}
