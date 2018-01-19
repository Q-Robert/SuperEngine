package net.myserver.engine.implementation.freemode;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.module.common.AntiDamageSettingsModule;

public class GameFreeMode extends GameSolo {

	public GameFreeMode(SuperEngine engine) {
		super("FreeMode", "PARRTYY! FISSAA", 4, "freemode", engine);
		spawns[0] = new Location(Bukkit.getWorld(getWorldName()), 0, 0, 0);
		spawns[1] = new Location(Bukkit.getWorld(getWorldName()), 0, 0, 0);
		spawns[2] = new Location(Bukkit.getWorld(getWorldName()), 0, 0, 0);
		spawns[3] = new Location(Bukkit.getWorld(getWorldName()), 0, 0, 0);
		addModule(new AntiDamageSettingsModule(engine));
		
	}

}
