package fr.lowtix.palamod.enumeration;

import org.bukkit.Material;

public enum XrayBlocks {
	
	DIAMOND(Material.DIAMOND_ORE, 250),
	GOLD(Material.GOLD_ORE, 175),
	STONE(Material.STONE, -0.92);
	
	private Material type;
	private double points;

	private XrayBlocks(Material type, double points) {
		this.type = type;
		this.points = points;
	}

	public Material getType() {
		return type;
	}
	
	public double getPoints() {
		return points;
	}

}
