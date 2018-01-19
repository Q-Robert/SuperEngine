package net.myserver.engine.module.common;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class NoFoodChangeModule extends Module {

	public NoFoodChangeModule(SuperEngine engine) 
	{
		super(engine);
	}

	@Override
	public void onEnable() 
	{
		Bukkit.getPluginManager().registerEvents(this, getEngine());
	}

	@Override
	public void onDisable()
	{
		HandlerList.unregisterAll(this);
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event)
	{
		event.setCancelled(true);
	}

}
