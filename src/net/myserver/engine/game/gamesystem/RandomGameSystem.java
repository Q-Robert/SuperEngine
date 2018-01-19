package net.myserver.engine.game.gamesystem;

import java.util.Random;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;

public class RandomGameSystem extends GameSystem{

	public RandomGameSystem(SuperEngine engine) {
		super(engine);
	}

	@Override
	public Game getNextGame() {
		int random = new Random().nextInt(getGames().size());
		return getGames().get(random);	
	}

}
