package net.myserver.engine.implementation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;
import net.myserver.engine.game.gamesystem.GameSystem;

public class GameManager {
	
	private SuperEngine engine;
	private Game game;
	private GameSystem gameSystem;
	
	public GameManager(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	public void setNewGame()
	{
		setGame(gameSystem.getNextGame());
	}
	
	public void setGame(Game game)
	{
		this.game = game;
		for(Player players : Bukkit.getServer().getOnlinePlayers())
		{
			engine.getScoreboardManager().getScoreboard(players).editName(game.getGameTitle());
			engine.getScoreboardManager().getScoreboard(players).editLine(16, ChatColor.GRAY + "None");
		}
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setGameSystem(GameSystem gameSystem)
	{
		this.gameSystem = gameSystem;
	}
	
	public GameSystem getGameSystem()
	{
		return gameSystem;
	}
}
