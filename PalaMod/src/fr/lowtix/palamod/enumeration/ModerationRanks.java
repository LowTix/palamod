package fr.lowtix.palamod.enumeration;

import org.bukkit.entity.Player;

public enum ModerationRanks {
	
	ADMINISTRATOR("Administrateur", 100),
	RESPONSABLE("Responsable", 40),
	SUPER_MODERATOR("SuperModérateur", 30),
	MODERATOR("Modérateur", 20),
	CHAT_MODERATOR("ModérateurTchat", 10),
	NONE("Rien", 0);
	
	private String prefix;
	private int power;
	
	private ModerationRanks(String prefix, int power) {
		this.prefix = prefix;
		this.power = power;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getPower() {
		return power;
	}
	
	public boolean isEquals(ModerationRanks rank) {
		return getPower() == rank.getPower();
	}
	
	public boolean isBetter(ModerationRanks rank) {
		return getPower() > rank.getPower();
	}
	
	public boolean isLower(ModerationRanks rank) {
		return getPower() < rank.getPower();
	}
	
	public static ModerationRanks getRankFromPermissions(Player player) {
		
		ModerationRanks result = NONE;
		
		for(ModerationRanks ranks : values()) {
			
			if(player.hasPermission("paladium.mod.rank."+ranks.name().toLowerCase())) {
				result = ranks;
			}
			
		}
		
		return result;
		
	}
	
}
