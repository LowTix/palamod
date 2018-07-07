package fr.lowtix.palamod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.lowtix.palamod.commands.Commands;
import fr.lowtix.palamod.enumeration.XrayBlocks;
import fr.lowtix.palamod.listeners.Listeners;
import fr.lowtix.palamod.tasks.XrayTask;
import fr.lowtix.palamod.users.ModUser;

public class PalaMod extends JavaPlugin {
	
	/*
	 * Plugin name: PalaMod
	 * Author: LowTix_
	 * At: 07/07/2018
	 */
	
	private static PalaMod instance;
	
	private HashMap<UUID, ModUser> users = new HashMap<UUID, ModUser>();
	
	public HashMap<Material, XrayBlocks> blocksToCheck = new HashMap<Material, XrayBlocks>();
	public ArrayList<Entry<ModUser, Double>> top_xray = new ArrayList<Entry<ModUser, Double>>();
	public double average = 0.0;

	@Override
	public void onEnable() {
		instance = this;
		
		for(XrayBlocks blocks : XrayBlocks.values()) {
			blocksToCheck.put(blocks.getType(), blocks);
		}
		
		new Listeners().init();
		new Commands().init();
		new XrayTask().runTaskTimerAsynchronously(this, 10, 1200);
	}
	
	public static PalaMod getInstance() {
		return instance;
	}
	
	public boolean containsModUser(UUID uuid) {
		return this.users.containsKey(uuid);
	}
	
	public void addModUser(ModUser user) {
		this.users.put(user.getPlayer().getUniqueId(), user);
	}
	
	public void deleteGameUser(UUID uuid) {
		if(containsModUser(uuid)) {
			this.users.remove(uuid);
		}
	}
	
	public ModUser getModUser(UUID uuid) {
		if(containsModUser(uuid)) {
			return this.users.get(uuid);
		}
		
		return new ModUser(Bukkit.getPlayer(uuid));
	}
	
	public ModUser getModUser(Player player) {
		return getModUser(player.getUniqueId());
	}
	
	public HashMap<UUID, ModUser> getModUsers(){
		return this.users;
	}
	
}
