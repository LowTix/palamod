package fr.lowtix.palamod.commands;

import fr.lowtix.palamod.PalaMod;

public class Commands {

	public void init() {
		
		PalaMod.getInstance().getCommand("xraytop").setExecutor(new XRayTopCommand());
		PalaMod.getInstance().getCommand("invbackup").setExecutor(new InvBackupCommand());
		
	}

}
