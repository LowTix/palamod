package fr.lowtix.palamod.users;

import java.util.HashMap;

import org.bukkit.entity.Player;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.enumeration.ModerationRanks;
import fr.lowtix.palamod.enumeration.XrayBlocks;

public class ModUser {
	
	private Player player;
	private long sessionTime;
	private ModerationRanks rank;
	
	private HashMap<XrayBlocks, XrayUser> xrayUser = new HashMap<>();
	private double points;
	
	private SavedInventory savedInventory;
	
	public ModUser(Player player) {
		
		this.player = player;
		this.sessionTime = System.currentTimeMillis();
		this.rank = ModerationRanks.getRankFromPermissions(player);
		
		for(XrayBlocks blocks : XrayBlocks.values()) {
			xrayUser.put(blocks, new XrayUser(this, blocks));
		}
		
		savedInventory = new SavedInventory(this);
		savedInventory.save();
		
		PalaMod.getInstance().addModUser(this);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public long getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(long sessionTime) {
		this.sessionTime = sessionTime;
	}

	public ModerationRanks getRank() {
		return rank;
	}

	public void setRank(ModerationRanks rank) {
		this.rank = rank;
	}

	public HashMap<XrayBlocks, XrayUser> getXrayUser() {
		return xrayUser;
	}

	public void setXrayUser(HashMap<XrayBlocks, XrayUser> xrayUser) {
		this.xrayUser = xrayUser;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
		this.points = (Math.floor(this.points * 1000)/1000);
	}

	public SavedInventory getSavedInventory() {
		return savedInventory;
	}

	public void setSavedInventory(SavedInventory savedInventory) {
		this.savedInventory = savedInventory;
	}
	
	
}
