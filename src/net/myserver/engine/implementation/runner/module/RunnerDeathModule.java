package net.myserver.engine.implementation.runner.module;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.module.Module;

public class RunnerDeathModule extends Module {
	
	private GameSolo game;

	public RunnerDeathModule(SuperEngine engine, GameSolo game) 
	{
		super(engine);
		this.game = game;
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
	public void onPlayerDeath(PlayerMoveEvent event)
	{
		if(event.getPlayer().getLocation().getY() < 60)
		{
			game.setDead(event.getPlayer());
		}
	}

}
