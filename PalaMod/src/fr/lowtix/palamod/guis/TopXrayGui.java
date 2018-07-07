package fr.lowtix.palamod.guis;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.users.ModUser;
import fr.lowtix.palamod.utils.Gui;
import fr.lowtix.palamod.utils.ItemBuilder;

public class TopXrayGui extends Gui {

	public TopXrayGui(Player player) {
		super("§eTop §6X-Ray", 6, player);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void drawScreen() {
		
		for(int i = 0; i < 9; i++) {
			setItem(i, new ItemBuilder(Material.IRON_FENCE).setName(" ").build());
		}
		
		setItem(4, new ItemBuilder(Material.GOLD_BLOCK).setName("§e§nClassement des tricheurs potentiels X-Ray").setLore(new String[] {
				"§f §r",
				"§7Joueur(s) connecté(s): §e" + Bukkit.getOnlinePlayers().length,
				"§7Session(s): §b"+ PalaMod.getInstance().getModUsers().size(),
				"§7Moyenne calculée: §6§o"+PalaMod.getInstance().average+" point(s)"
		}).build());
		
		int pos = 1;
		for(Entry<ModUser, Double> entry : PalaMod.getInstance().top_xray) {
			ModUser user = entry.getKey();
			double points = entry.getValue();
			
			if(user.getPlayer() == null || !user.getPlayer().isOnline()) {
				return;
			}
			
			setItem((pos + 8), new ItemBuilder(Material.SKULL_ITEM).setName("§7Joueur: §e§l"+user.getPlayer().getName()).setLore(new String[] {
					"§7Point(s) lors du classement: §c" + points,
					"§7Point(s) actuel(s): §b"+ user.getPoints(),
					"§7Position: §f#§a"+ pos,
			}).build());
			
			pos++;
		}
		
	}

}
