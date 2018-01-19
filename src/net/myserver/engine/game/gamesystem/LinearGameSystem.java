package net.myserver.engine.game.gamesystem;

import java.util.HashMap;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;

public class LinearGameSystem extends GameSystem {

	public LinearGameSystem(SuperEngine engine) {
		super(engine);
		int i = 0;
		for(Game game : getGames())
		{
			gameId.put(i, game);
			i++;
		}
	}
	
	private HashMap<Integer, Game> gameId = new HashMap<Integer, Game>();
	private int currentGameId = 0;

	@Override
	public Game getNextGame() {
		currentGameId++;
		if(currentGameId == getGames().size())
		{
			currentGameId = 0;
		}
		return gameId.get(currentGameId);
	}
}
