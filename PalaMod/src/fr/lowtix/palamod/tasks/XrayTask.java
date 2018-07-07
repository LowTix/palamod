package fr.lowtix.palamod.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.scheduler.BukkitRunnable;

import fr.lowtix.palamod.PalaMod;
import fr.lowtix.palamod.users.ModUser;

public class XrayTask extends BukkitRunnable {

	/*
	 * Asynchronous Task
	 * Classification of players in relation to their XRay points
	 */
	
	@Override
	public void run() {
		
		PalaMod.getInstance().top_xray.clear();
		
		HashMap<ModUser, Double> points = new HashMap<ModUser, Double>();
		
		double average = (PalaMod.getInstance().average/10);
		
		for(ModUser users : PalaMod.getInstance().getModUsers().values()) {
			if(users.getPlayer() != null && users.getPlayer().isOnline()) {
				
				average += users.getPoints();
				average /= 2;
				
				points.put(users, users.getPoints());
			}
		}
		
		average = Math.floor(average * 100)/100;
		PalaMod.getInstance().average = average;
		
		ArrayList<Entry<ModUser, Double>> top = new ArrayList<Entry<ModUser, Double>>();
		top.addAll(points.entrySet());
		Collections.sort(top, new Comparator<Entry<ModUser, Double>>() {

			@Override
			public int compare(Entry<ModUser, Double> o1, Entry<ModUser, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});
		
		for (int i = 0; i < 45; i++) {
			
			if(top.size() > i && top.get(i) != null) {
				PalaMod.getInstance().top_xray.add(top.get(i));
			}
			
		}
		
	}

}
