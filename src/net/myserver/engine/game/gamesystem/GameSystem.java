package net.myserver.engine.game.gamesystem;

import java.util.ArrayList;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;
import net.myserver.engine.implementation.freemode.GameFreeMode;
import net.myserver.engine.implementation.runner.GameRunner;
import net.myserver.engine.implementation.spleef.GameSpleef;

public abstract class GameSystem {
	
	private SuperEngine engine;
	private ArrayList<Game> games = new ArrayList<Game>();
	
	public GameSystem(SuperEngine engine)
	{
		this.engine = engine;
		getGames().add(new GameRunner(getEngine()));
		getGames().add(new GameSpleef(getEngine()));
		getGames().add(new GameFreeMode(getEngine()));
	}
	
	public abstract Game getNextGame();
	
	public SuperEngine getEngine()
	{
		return engine;
	}
	
	public ArrayList<Game> getGames()
	{
		return games;
	}
}
