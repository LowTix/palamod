package fr.lowtix.palamod.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.guis.InvBackupGui;
import fr.lowtix.palamod.users.ModUser;
import fr.lowtix.palamod.users.SavedInventory;
import fr.lowtix.palamod.utils.GuiManager;

public class InvBackupCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			ModUser user = PalaMod.getInstance().getModUser(player);

			if(args.length == 0 || (args.length >= 1 && args[0].equalsIgnoreCase("help"))) {
				player.sendMessage("§8§m-----------------------------------------");
				player.sendMessage("§8❯ §7Commandes:");
				player.sendMessage("  §8• §b/invbackup view <pseudo> §7§ovoir l'inventaire backup");
				player.sendMessage("  §8• §b/invbackup give <pseudo> §7§ovous give l'inventaire backup du joueur");
				player.sendMessage("  §8• §b/invbackup giveplayer <pseudo> §7§ogive l'inventaire backup au joueur");
				player.sendMessage("  §8• §b/invbackup clear <pseudo> §7§odétruire l'inventaire backup");
				player.sendMessage("§8§m-----------------------------------------");
				
				return true;
			} else if(args.length == 2) {
				
				String name = args[1];
				if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) {
					player.sendMessage("§8[§4✖§8] §cCe joueur n'est pas en ligne.");
					return true;
				}
				
				Player target = Bukkit.getPlayer(name);
				ModUser targetUser = PalaMod.getInstance().getModUser(target);
				
				if(args[0].equalsIgnoreCase("view")) {
					GuiManager.openGui(new InvBackupGui(player, target));
				} else if(args[0].equalsIgnoreCase("give")) {
					
					user.getSavedInventory().save();
					
					player.sendMessage("§8[§2✔§8] §7Votre inventaire a été sauvegardé, give de l'inventaire de §e"+target.getName()+"§7.");
					give(targetUser.getSavedInventory(), player.getInventory());
					
				} else if(args[0].equalsIgnoreCase("giveplayer")) {
					
					player.sendMessage("§8[§2✔§8] §7Vous avez redonné la backup inventaire de §e"+target.getName()+"§7.");
					give(targetUser.getSavedInventory(), player.getInventory());
					
				} else if(args[0].equalsIgnoreCase("clear")) {
					
					player.sendMessage("§8[§2✔§8] §7Vous avez clear la backup inventaire de §e"+target.getName()+"§7.");
					targetUser.getSavedInventory().setup();
				}
				
				
				
			}
			
		}
		
		return true;
	}
	
	private void give(SavedInventory from, PlayerInventory to) {
		
		to.setHelmet(from.getHelmet());
		to.setChestplate(from.getChestplate());
		to.setLeggings(from.getLeggings());
		to.setBoots(from.getBoots());
		
		to.setContents(from.getContents());
		
	}

}
