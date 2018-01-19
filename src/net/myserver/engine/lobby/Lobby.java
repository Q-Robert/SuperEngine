package net.myserver.engine.lobby;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.countdown.LobbyCountdown;
import net.myserver.engine.lobby.module.LobbyJoinModule;
import net.myserver.engine.lobby.module.LobbyLeaveModule;
import net.myserver.engine.module.Module;
import net.myserver.engine.module.common.AntiDamageSettingsModule;
import net.myserver.engine.module.common.NoFoodChangeModule;

public class Lobby {
	
	private SuperEngine engine;
	private Location spawn;
	private String worldName;
	private ArrayList<Module> modules = new ArrayList<Module>();
	private LobbyCountdown countdown;
	
	public Lobby(SuperEngine engine, Location spawn, String worldName)
	{
		this.engine = engine;
		this.spawn = spawn;
		this.worldName = worldName;
		Bukkit.createWorld(new WorldCreator(worldName));
		addModule(new NoFoodChangeModule(engine));
		addModule(new LobbyJoinModule(engine));
		addModule(new LobbyLeaveModule(engine));
		addModule(new AntiDamageSettingsModule(engine));
		enableModules();
	}
	
	public void addModule(Module module)
	{
		modules.add(module);
	}
	
	public void enableModules()
	{
		for(Module module : modules)
		{
			module.onEnable();
		}
	}
	
	public void disableModules()
	{
		for(Module module : modules)
		{
			module.onDisable();
		}
	}
	
	public void startCountdown()
	{
		countdown = new LobbyCountdown(getEngine());
	}
	
	public SuperEngine getEngine()
	{
		return engine;
	}
	
	public Location getSpawn()
	{
		return spawn;
	}
	
	public String getWorldName()
	{
		return worldName;
	}
		
	public LobbyCountdown getCountdown()
	{
		return countdown;
	}
}
