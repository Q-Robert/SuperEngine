package net.myserver.engine.module.common;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class AntiMoveModule extends Module {

	public AntiMoveModule(SuperEngine engine) 
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
	public void move(PlayerMoveEvent event)
	{
	    Location from = event.getFrom();
	    Location to = event.getTo();
	    double x = Math.floor(from.getX());
	    double z = Math.floor(from.getZ());
	    if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z)
	    {
	        x+=.5;
	        z+=.5;
	        event.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
	    }
	}
	 
}
