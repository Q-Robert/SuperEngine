package net.myserver.engine.implementation.spleef.module;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.module.Module;

public class SpleefDeathModule extends Module {
	
	private GameSolo game;

	public SpleefDeathModule(SuperEngine engine, GameSolo game) 
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
		if(event.getPlayer().getLocation().getY() < 50 && game.getPlayers().contains(event.getPlayer()))
		{
			game.setDead(event.getPlayer());
		}
	}

}
