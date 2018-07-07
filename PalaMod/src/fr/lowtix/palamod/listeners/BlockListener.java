package fr.lowtix.palamod.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.enumeration.XrayBlocks;
import fr.lowtix.palamod.users.ModUser;
import fr.lowtix.palamod.users.XrayUser;

public class BlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		if(PalaMod.getInstance().blocksToCheck.containsKey(event.getBlock().getType())) {
			
			Player player = event.getPlayer();
			ModUser user = PalaMod.getInstance().getModUser(player);
			
			Block block = event.getBlock();
			
			XrayBlocks xrayBlock = PalaMod.getInstance().blocksToCheck.get(block.getType());
			XrayUser xrayUser = user.getXrayUser().get(xrayBlock);
			
			if(xrayBlock.getPoints() > 0 && block.getLocation().distance(xrayUser.getLastMinedLocation()) > 5) {
				
				user.setPoints(user.getPoints() + xrayBlock.getPoints());
				
				xrayUser.setAmount(xrayUser.getAmount() + 1);
				xrayUser.setLastMinedLocation(block.getLocation());
			} else if(xrayBlock.getPoints() <= 0) {
				user.setPoints(user.getPoints() + xrayBlock.getPoints());
				
				xrayUser.setAmount(xrayUser.getAmount() + 1);
				xrayUser.setLastMinedLocation(block.getLocation());
			}
			
			xrayUser.setLastMinedTime(System.currentTimeMillis());
			
		}
		
	}

}
