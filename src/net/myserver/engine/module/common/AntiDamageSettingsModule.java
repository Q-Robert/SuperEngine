package net.myserver.engine.module.common;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;
import net.myserver.engine.server.ServerSettings;

public class AntiDamageSettingsModule extends Module {

	public AntiDamageSettingsModule(SuperEngine engine) 
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
	public void onDamage(EntityDamageByEntityEvent event)
	{
		if((boolean) ServerSettings.PVP.getValue())
			return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) 
	{
		if((boolean) ServerSettings.BREAKBLOCKS.getValue())
			return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) 
	{
		if((boolean) ServerSettings.PLACEBLOCKS.getValue())
			return;
		event.setCancelled(true);
	}

}
