package net.myserver.engine.game;

import net.myserver.engine.SuperEngine;

public class GameTeam extends Game{
	
	//SORRY! No teams in this version :/

	public GameTeam(String gameTitle, String gameDescription, int maxPlayers, String worldName, SuperEngine engine) 
	{
		super(gameTitle, gameDescription, maxPlayers, worldName, engine);
	}

	@Override
	public void teleportToGame() 
	{
	}

}
