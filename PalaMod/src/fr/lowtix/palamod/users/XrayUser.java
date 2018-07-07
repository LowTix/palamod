package fr.lowtix.palamod.users;

import org.bukkit.Location;

import fr.lowtix.palamod.enumeration.XrayBlocks;

public class XrayUser {
	
	private ModUser user;
	private XrayBlocks xrayBlock;
	
	private int amount;
	private long lastMinedTime;
	private Location lastMinedLocation;
	
	public XrayUser(ModUser user, XrayBlocks block) {
		
		this.user = user;
		this.xrayBlock = block;
		
		amount = 0;
		lastMinedTime = System.currentTimeMillis();
		lastMinedLocation = user.getPlayer().getLocation().getBlock().getLocation();
		
	}

	public ModUser getUser() {
		return user;
	}

	public void setUser(ModUser user) {
		this.user = user;
	}

	public XrayBlocks getXrayBlock() {
		return xrayBlock;
	}

	public void setXrayBlock(XrayBlocks xrayBlock) {
		this.xrayBlock = xrayBlock;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getLastMinedTime() {
		return lastMinedTime;
	}

	public void setLastMinedTime(long lastMinedTime) {
		this.lastMinedTime = lastMinedTime;
	}

	public Location getLastMinedLocation() {
		return lastMinedLocation;
	}

	public void setLastMinedLocation(Location lastMinedLocation) {
		this.lastMinedLocation = lastMinedLocation;
	}

	public long getElapsedSeconds(long now) {
		return ((now - getLastMinedTime())/1000);
	}
	
}
