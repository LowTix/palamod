package fr.lowtix.palamod.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.lowtix.palamod.PalaMod;

public abstract class Gui implements Listener {
    private Inventory inventory;
    public Player player;

    public Player getPlayer() {
		return player;
	}

	public Gui(String name, int size, Player player) {
        this.inventory = Bukkit.createInventory(null, size * 9, name);
        this.player = player;
    }

    public abstract void drawScreen();

    public void open() {
        player.openInventory(inventory);
        drawScreen();
        player.updateInventory();
        Bukkit.getPluginManager().registerEvents(this, PalaMod.getInstance());
        onOpen();
    }

    public void close() {
        player.closeInventory();
    }

    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    public void clearInventory() {
        inventory.clear();
    }

    @EventHandler
    public void onPlayerInventory(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().equals(inventory)) {
            if(e.getCurrentItem() != null) {
                if(e.getCurrentItem().getType() != Material.AIR) {
                    onClick(e.getSlot(), e.getCurrentItem(), e.getClick());
                }
            }
            e.setCancelled(true);
        }
    }

    public void onOpen() {}

    public void onClick(int position, ItemStack item, ClickType click) {}

    public void onClose() {}

    @EventHandler
    public void onPlayerInventory(InventoryCloseEvent e) {
        onClose();
        if (!GuiManager.isOpened(getClass())) {
            HandlerList.unregisterAll(this);
        }
    }
}