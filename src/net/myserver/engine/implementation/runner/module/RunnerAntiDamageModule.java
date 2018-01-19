package net.myserver.engine.implementation.runner.module;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class RunnerAntiDamageModule extends Module {

	public RunnerAntiDamageModule(SuperEngine engine) 
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
	public void onDamage(EntityDamageEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockbreak(BlockBreakEvent event) 
	{
			  event.setCancelled(true);
	}

}
