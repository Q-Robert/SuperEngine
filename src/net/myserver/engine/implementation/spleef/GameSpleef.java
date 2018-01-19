package net.myserver.engine.implementation.spleef;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.implementation.spleef.kit.SpleefFreezerKit;
import net.myserver.engine.implementation.spleef.kit.SpleefLeaperKit;
import net.myserver.engine.implementation.spleef.module.SpleefDamageModule;
import net.myserver.engine.implementation.spleef.module.SpleefDeathModule;
import net.myserver.engine.implementation.spleef.module.SpleefInteractModule;
import net.myserver.engine.module.common.NoFoodChangeModule;

public class GameSpleef extends GameSolo {
	
	public GameSpleef(SuperEngine engine)
	{
		super("Spleef", "Break blocks to make your enemy fall!", 4, "spleef", engine);
		spawns[0] = new Location(Bukkit.getWorld(getWorldName()), 6, 63, -76);
		spawns[1] = new Location(Bukkit.getWorld(getWorldName()), -4, 63, -86);
		spawns[2] = new Location(Bukkit.getWorld(getWorldName()), 7, 63, -96);
		spawns[3] = new Location(Bukkit.getWorld(getWorldName()), 17, 63, -86);
		addModule(new SpleefInteractModule(engine));
		addModule(new SpleefDeathModule(engine, this));
		addModule(new SpleefDamageModule(engine));
		addModule(new NoFoodChangeModule(engine));
		addKit(new SpleefLeaperKit());
		addKit(new SpleefFreezerKit());
	}
}
