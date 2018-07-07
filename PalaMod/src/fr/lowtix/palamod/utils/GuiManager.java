package fr.lowtix.palamod.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class GuiManager {
    @SuppressWarnings("rawtypes")
	private static HashMap<String, Class> openGuis = new HashMap<>();

    public static Gui openGui(Gui gui) {
        openPlayer(gui.getPlayer(), gui.getClass());
        gui.open();
        return gui;
    }

    @SuppressWarnings("rawtypes")
	public static void openPlayer(Player p, Class gui) {
        if (openGuis.containsKey(p.getName())) {
            openGuis.remove(p.getName());
            openGuis.put(p.getName(), gui);
        }
        else {
            openGuis.put(p.getName(), gui);
        }
    }

    public static void closePlayer(Player p) {
    	p.getOpenInventory().close();
        if (openGuis.containsKey(p.getName())) {
            openGuis.remove(p.getName());
        }
    }

    public static boolean isPlayer(Player p) {
        if (openGuis.containsKey(p.getName())) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
	public static boolean isOpened(Class clas) {
        for (Class cla : openGuis.values()) {
            if (cla.equals(clas)) {
                return true;
            }
        }
        return false;
    }
}