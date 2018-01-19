package net.myserver.engine.module.common;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerQuitEvent;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.GameSolo;
import net.myserver.engine.module.Module;

public class PlayerQuitModule extends Module {
	
	private GameSolo game;

	public PlayerQuitModule(SuperEngine engine, GameSolo game) 
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
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(game.getPlayers().contains(event.getPlayer()))
		{
		game.setDead(event.getPlayer());
		}
	}

}
