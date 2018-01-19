package net.myserver.engine.implementation.runner;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.implementation.runner.kit.RunnerLeaperKit;
import net.myserver.engine.implementation.runner.kit.RunnerRabbitKit;
import net.myserver.engine.implementation.runner.module.RunnerAntiDamageModule;
import net.myserver.engine.implementation.runner.module.RunnerBlockDropModule;
import net.myserver.engine.implementation.runner.module.RunnerDeathModule;
import net.myserver.engine.module.common.NoFoodChangeModule;

public class GameRunner extends GameSolo{

	public GameRunner(SuperEngine engine) 
	{
		super("Runner", "Run, the blocks will fall under your feet!", 4, "runner", engine);
		spawns[0] = new Location(Bukkit.getWorld(getWorldName()), -9, 84, -13);
		spawns[1] = new Location(Bukkit.getWorld(getWorldName()), -20, 84, 1);
		spawns[2] = new Location(Bukkit.getWorld(getWorldName()), 2, 84, 1);
		spawns[3] = new Location(Bukkit.getWorld(getWorldName()), -9, 84, 16);
		addModule(new RunnerAntiDamageModule(engine));
		addModule(new RunnerDeathModule(engine, this));
		addModule(new NoFoodChangeModule(engine));
		addModule(new RunnerBlockDropModule(engine));
		addKit(new RunnerLeaperKit());
		addKit(new RunnerRabbitKit());
	}

}
