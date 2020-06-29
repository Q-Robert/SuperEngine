package net.myserver.engine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.myserver.engine.commands.GameCommand;
import net.myserver.engine.commands.KitCommand;
import net.myserver.engine.commands.ServerCommand;
import net.myserver.engine.game.gamesystem.LinearGameSystem;
import net.myserver.engine.implementation.GameManager;
import net.myserver.engine.lobby.Lobby;
import net.myserver.engine.rollback.RollbackManager;
import net.myserver.engine.scoreboard.ScoreboardManager;
import net.myserver.engine.server.RankManager;
import net.myserver.engine.server.SettingsListener;

public class SuperEngine extends JavaPlugin {
	
	/**
	 * @author Inferides / QR de Preter
	 */
	
	private Plugin plugin;
	private Lobby lobby;
	private GameManager gameManager;
	private ScoreboardManager scoreboardManager;
	private RollbackManager rollbackManager;
	private RankManager rankManager;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		scoreboardManager = new ScoreboardManager();
		gameManager = new GameManager(this);
		rollbackManager = new RollbackManager(this);
		rankManager = new RankManager();
		getGameManager().setGameSystem(new LinearGameSystem(this));
		getGameManager().setNewGame();
		lobby = new Lobby(this, new Location(Bukkit.getWorld("lobby"), 12, 46, -23), "lobby");
		getCommand("game").setExecutor(new GameCommand(this));
		getCommand("kit").setExecutor(new KitCommand(this));
		getCommand("server").setExecutor(new ServerCommand(this));
		Bukkit.getPluginManager().registerEvents(new SettingsListener(this), this);
	}
	
	public Plugin getInstance()
	{
		return plugin;
	}
	
	public Lobby getLobby()
	{
		return lobby;
	}
	
	public GameManager getGameManager()
	{
		return gameManager;
	}
	
	public ScoreboardManager getScoreboardManager()
	{
		return scoreboardManager;
	}
	
	public RollbackManager getRollbackManager()
	{
		return rollbackManager;
	}
	
	public RankManager getRankManager()
	{
		return rankManager;
	}

}
