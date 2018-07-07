package fr.lowtix.palamod.users;

import java.util.Date;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.lowtix.palamod.utils.ItemBuilder;

public class SavedInventory {
	
	private ModUser user;
	
	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;
	
	private ItemStack[] contents;
	
	private Date savedAt;
	
	public SavedInventory(ModUser user) {
		
		this.user = user;
		setup();
	}
	
	public void setup() {
		helmet = new ItemBuilder(Material.AIR).build();
		chestplate = new ItemBuilder(Material.AIR).build();
		leggings = new ItemBuilder(Material.AIR).build();
		boots = new ItemBuilder(Material.AIR).build();
		
		contents = new ItemStack[] {new ItemBuilder(Material.AIR).build()};
		
		savedAt = new Date();
	}
	
	public void save() {
		
		Player player = user.getPlayer();
		PlayerInventory inv = player.getInventory();
		
		this.helmet = inv.getHelmet();
		this.chestplate = inv.getChestplate();
		this.leggings = inv.getLeggings();
		this.boots = inv.getBoots();
		
		this.contents = inv.getContents();
		
		savedAt = new Date();
	}
	
	public int getElapsedMinutes() {
		
		Date now = new Date();
		
		long diff = now.getTime() - getSavedAt().getTime();
	    long diffMinutes = diff / (60 * 1000) % 60;
		
	    return (int) diffMinutes;
	}

	public ModUser getUser() {
		return user;
	}

	public void setUser(ModUser user) {
		this.user = user;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public void setHelmet(ItemStack helmet) {
		this.helmet = helmet;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public void setChestplate(ItemStack chestplate) {
		this.chestplate = chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public void setLeggings(ItemStack leggings) {
		this.leggings = leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}

	public void setBoots(ItemStack boots) {
		this.boots = boots;
	}

	public ItemStack[] getContents() {
		return contents;
	}

	public void setContents(ItemStack[] contents) {
		this.contents = contents;
	}

	public Date getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(Date savedAt) {
		this.savedAt = savedAt;
	}
	
}
