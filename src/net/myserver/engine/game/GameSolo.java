package net.myserver.engine.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.common.PlayerQuitModule;
import net.myserver.engine.util.ChatUtil;

public abstract class GameSolo extends Game{
	
	protected Location spawns[];
	private ArrayList<Player> players;
	private HashMap<Player, Integer> playerScore;
	private int maxScore;

	public GameSolo(String gameTitle, String gameDescription, int maxPlayers, String worldName, SuperEngine engine) 
	{
		super(gameTitle, gameDescription, maxPlayers, worldName, engine);
		new PlayerQuitModule(getEngine(), this);
		spawns = new Location[getMaxPlayers()];
		players = new ArrayList<Player>();
		playerScore = new HashMap<Player, Integer>();
	}
	
	@Override
	public void teleportToGame()
	{
		int i = 0;
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
			players.add(player);
			player.teleport(spawns[i]);	
			i++;
		if(i == Bukkit.getServer().getOnlinePlayers().size())
		{
			i = 0;
		}
		}
	}
	
	public void setScoreGame(int maxScore)
	{
		playerScore.clear();
		this.maxScore = maxScore;
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
			playerScore.put(player, 0);
		}
	}
	
	public void addScore(Player player, int score)
	{
		playerScore.put(player, getScore(player) + score);
		player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "SCORE: " + ChatColor.GRAY + score + "/" + maxScore);
		if(getScore(player) == maxScore)
		{
			end(player.getName());
		}
	}
	
	public void setDead(Player player)
	{
		players.remove(player);
		player.sendMessage(ChatUtil.sendGameMessage("You died."));
		player.getInventory().clear();
		player.setGameMode(GameMode.SPECTATOR);
		if(players.size() == 1)
		{
			players.clear();
			for(Player winner : players)
			end(winner.getName());
			return;
		}
		Bukkit.broadcastMessage(ChatUtil.sendGameMessage(player.getName() + " died! " + players.size() + " players are left alive."));
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public int getScore(Player player)
	{
		return playerScore.get(player);
	}

}
