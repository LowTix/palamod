package fr.lowtix.palamod.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lowtix.palamod.guis.TopXrayGui;
import fr.lowtix.palamod.utils.GuiManager;

public class XRayTopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(sender instanceof Player) {
			
			// TODO: Check le rank
			
			GuiManager.openGui(new TopXrayGui((Player) sender));
			
		}
		
		return true;
	}

}
