package net.myserver.engine.module;

import org.bukkit.event.Listener;

import net.myserver.engine.SuperEngine;

public abstract class Module implements Listener {
	
	private SuperEngine engine;
	
	public Module(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	public abstract void onEnable();
	public abstract void onDisable();
	
	public SuperEngine getEngine()
	{
		return engine;
	}

}
