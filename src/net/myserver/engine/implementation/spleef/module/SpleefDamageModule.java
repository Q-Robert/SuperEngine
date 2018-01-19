package net.myserver.engine.implementation.spleef.module;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class SpleefDamageModule extends Module {

	public SpleefDamageModule(SuperEngine engine) 
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
		event.setDamage(0);
	}

}
