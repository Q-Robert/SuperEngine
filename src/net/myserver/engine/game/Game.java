package net.myserver.engine.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.countdown.EndGameCountdown;
import net.myserver.engine.game.countdown.PreGameCountdown;
import net.myserver.engine.game.kit.Kit;
import net.myserver.engine.game.kit.KitModule;
import net.myserver.engine.module.Module;
import net.myserver.engine.module.common.AntiMoveModule;
import net.myserver.engine.nms.GameTitle;
import net.myserver.engine.util.ChatUtil;

public abstract class Game {
	
	private String gameTitle;
	private String gameDescription;
	private ArrayList<Module> modules = new ArrayList<Module>();
	private HashMap<Player, Kit> playerKit = new HashMap<Player, Kit>();
	private ArrayList<Kit> kits = new ArrayList<Kit>();
	private int maxPlayers;
	private String worldName;
	private SuperEngine engine;
	private AntiMoveModule antiMoveModule;
	
	public Game(String gameTitle, String gameDescription, int maxPlayers, String worldName, SuperEngine engine)
	{
		this.gameTitle = gameTitle;
		this.gameDescription = gameDescription;
		this.maxPlayers = maxPlayers;
		this.worldName = worldName;
		this.engine = engine;
		Bukkit.createWorld(new WorldCreator(worldName));
		addModule(new KitModule(engine, this));
	}
	
	public abstract void teleportToGame();
	
	public void preStart()
	{
		teleportToGame();
		antiMoveModule = new AntiMoveModule(getEngine());
		antiMoveModule.onEnable();		
		new PreGameCountdown(getEngine(), this);
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
		player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
		new GameTitle(player, EnumTitleAction.TITLE, getGameTitle(), 80, 100, 40, ChatColor.AQUA);
		new GameTitle(player, EnumTitleAction.SUBTITLE, getGameDescription(), 80, 100, 40, ChatColor.YELLOW);
		if(!playerKit.containsKey(player))
		{
			setKit(player, kits.get(0));
		}
		player.getInventory().addItem(getKit(player).getItem());
		}
	}
	
	public void start()
	{
		antiMoveModule.onDisable();	
		getEngine().getLobby().disableModules();
		for(Module module : modules)
		{
			module.onEnable();
		}
	}
	
	public void end(String winnerName)
	{
		new EndGameCountdown(getEngine(), this);
		getEngine().getLobby().enableModules();
		for(Module module : modules)
		{
			module.onDisable();
		}
		Bukkit.broadcastMessage(ChatUtil.sendGameMessage(winnerName + " has won the game!"));
		for(Player players : Bukkit.getServer().getOnlinePlayers())
		{
		new GameTitle(players, EnumTitleAction.TITLE, winnerName, 80, 100, 40, ChatColor.AQUA);
		new GameTitle(players, EnumTitleAction.SUBTITLE, "Has won the game!", 80, 100, 40, ChatColor.YELLOW);
		}
	}
	
	public void stop()
	{
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
		player.getInventory().clear();
		player.teleport(engine.getLobby().getSpawn());
		player.getWorld().playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
		player.setGameMode(GameMode.SURVIVAL);
		getEngine().getScoreboardManager().getScoreboard(player).editLine(13, ChatColor.GRAY + "30");
		}
		getEngine().getRollbackManager().rollback(getWorldName());
		getEngine().getGameManager().setNewGame();
		getEngine().getLobby().startCountdown();
	}
	
	public void addModule(Module module)
	{
		modules.add(module);
	}
	
	public void addKit(Kit kit)
	{
		kits.add(kit);
	}
	
	public void setKit(Player player, Kit kit)
	{
		playerKit.put(player, kit);
		engine.getScoreboardManager().getScoreboard(player).editLine(16, ChatColor.GRAY + kit.getName());
	}
	
	public Kit getKit(Player player)
	{
		return playerKit.get(player);
	}
	
	public ArrayList<Kit> getKits()
	{
		return kits;
	}
	
	public void setMaxPlayers(int maxPlayerCount)
	{
		maxPlayers = maxPlayerCount;
	}
	
	public int getMaxPlayers()
	{
		return maxPlayers;
	}
	
	public String getGameTitle()
	{
		return gameTitle;
	}
	
	public String getGameDescription()
	{
		return gameDescription;
	}

	public SuperEngine getEngine()
	{
		return engine;
	}
	
	public String getWorldName()
	{
		return worldName;
	}
}
