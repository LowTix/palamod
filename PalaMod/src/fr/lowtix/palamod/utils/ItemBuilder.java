package fr.lowtix.palamod.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	private Material material;
	private int amount = 1;
	private Short damage = 0;
	private String name;
	private List<String> lore = new ArrayList<String>();
	private HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
	
	public ItemBuilder(Material material) {
		this.material = material;
	}
	
	public ItemBuilder setAmount(int amount){
		this.amount = amount;
		return this;
	}
	
	public ItemBuilder setDamage(int damage){
		this.damage = (short) damage;
		return this;
	}
	
	public ItemBuilder setName(String name){
		this.name = name;
		return this;
	}
	
	public ItemBuilder setLore(String[] lores){
		for(String lore : lores){
			this.lore.add(lore);
		}
		return this;
	}
	
	public ItemBuilder addEnchant(Enchantment enchantment, int level){
		enchantments.put(enchantment, level);
		return this;
	}
	
	public ItemBuilder addEnchants(HashMap<Enchantment, Integer> enchantments){
		for(Enchantment enchantment : enchantments.keySet()){
			this.enchantments.put(enchantment, enchantments.get(enchantment));
		}
		return this;
	}
	
	public ItemStack build(){
		ItemStack itemStack = new ItemStack(material, amount, damage);
		ItemMeta meta = itemStack.getItemMeta();
		if(!enchantments.isEmpty()){
			for(Enchantment enchantment : enchantments.keySet()){
				meta.addEnchant(enchantment, enchantments.get(enchantment), true);
			}
		}
		if(name != null && name.length() != 0){
			meta.setDisplayName(name);
		}
		if(!lore.isEmpty()){
			meta.setLore(lore);
		}
		
		itemStack.setItemMeta(meta);
		return itemStack;
	}
	
}
