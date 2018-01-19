package net.myserver.engine.implementation.runner.module;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class RunnerBlockDropModule extends Module {
	
	RunnerBlockTask task;

	public RunnerBlockDropModule(SuperEngine engine) 
	{
		super(engine);
		task = new RunnerBlockTask(getEngine());
	}

	@Override
	public void onEnable() 
	{
		Bukkit.getPluginManager().registerEvents(this, getEngine());
		Bukkit.getScheduler().runTaskLater(getEngine(), new Runnable()
		{
			public void run()
			{
				task.startTask();	
			}
		}, 20l);
	}

	@Override
	public void onDisable() 
	{
		HandlerList.unregisterAll(this);
		task.cancelTask();
	}
	
	@EventHandler
    public void onLand(EntityChangeBlockEvent event) 
	{
        if (event.getEntityType().equals(EntityType.FALLING_BLOCK))
            event.setCancelled(true);
    }
}
