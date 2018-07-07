package fr.lowtix.palamod.guis;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.users.ModUser;
import fr.lowtix.palamod.users.SavedInventory;
import fr.lowtix.palamod.utils.Gui;
import fr.lowtix.palamod.utils.ItemBuilder;

public class InvBackupGui extends Gui {

	private ModUser target;
	
	public InvBackupGui(Player player, Player target) {
		super("§eBackup Inventaire", 6, player);
		this.target = PalaMod.getInstance().getModUser(target);
	}

	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 9; i++) {
			setItem(i, new ItemBuilder(Material.IRON_FENCE).setName(" ").build());
		}
		
		SavedInventory inv = target.getSavedInventory();
		
		setItem(4, new ItemBuilder(Material.SKULL_ITEM).setName("§eBackup de §6" + target.getPlayer().getName()).setLore(new String[] {
				"§f §r",
				"§7Enregistré il y a: §e" + inv.getElapsedMinutes() + " minute(s)"
		}).build());
		
		setItem(9, inv.getHelmet());
		setItem(10, inv.getChestplate());
		setItem(11, inv.getLeggings());
		setItem(12, inv.getBoots());
		
		int slot = 18;
		for(ItemStack item : inv.getContents()) {
			setItem(slot, item);
			slot++;
		}
		
	}

}
