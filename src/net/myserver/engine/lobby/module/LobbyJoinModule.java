package net.myserver.engine.lobby.module;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;
import net.myserver.engine.nms.GameTitle;
import net.myserver.engine.nms.TabManager;
import net.myserver.engine.scoreboard.GameScoreboard;

public class LobbyJoinModule extends Module {

	public LobbyJoinModule(SuperEngine engine) {
		super(engine);
	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, getEngine());
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		new GameTitle(player, EnumTitleAction.TITLE, "SuperEngine", 40, 100, 40, ChatColor.AQUA);
		new GameTitle(player, EnumTitleAction.SUBTITLE, "A engine written by Quinster09.", 40, 100, 40, ChatColor.YELLOW);
		new TabManager(player, ChatColor.RED + "" + ChatColor.BOLD + "QUINSTER09'S SERVER", ChatColor.AQUA + "" + ChatColor.BOLD + "ENJOY");
		player.teleport(getEngine().getLobby().getSpawn());
		setupScoreboard(player);
		for(Player players : Bukkit.getServer().getOnlinePlayers())
			getEngine().getScoreboardManager().getScoreboard(players).editLine(19, ChatColor.GRAY + String.valueOf(Bukkit.getServer().getOnlinePlayers().size() + "/4"));
		if(Bukkit.getServer().getOnlinePlayers().size() == 2)
		{
			getEngine().getLobby().startCountdown();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setupScoreboard(Player player)
	{
		GameScoreboard board = new GameScoreboard(getEngine().getGameManager().getGame().getGameTitle());
		board.addLine(ChatColor.YELLOW + "" + ChatColor.BOLD + "Players");
		board.addLine(ChatColor.GRAY + "" + Bukkit.getOnlinePlayers().size() + "/" + getEngine().getGameManager().getGame().getMaxPlayers());
		board.addBlankLine();
		board.addLine(ChatColor.GREEN + "" + ChatColor.BOLD + "Kit");
		board.addLine(ChatColor.GRAY + "None");
		board.addBlankLine();
		board.addLine(ChatColor.GOLD + "" + ChatColor.BOLD + "Starting in");
		board.addLine(ChatColor.GRAY + "30");
		getEngine().getScoreboardManager().setScoreboard(player, board);
		Team team = board.getBoard().registerNewTeam(player.getName());
		if(player.isOp())
		{
			team.setPrefix(ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "OP" + ChatColor.DARK_GRAY + "] "+ ChatColor.GRAY);
			team.addEntry(player.getName());
		}
		else
		{
			team.setPrefix(ChatColor.GRAY + "");
			team.addEntry(player.getName());
		}
		team.addPlayer(player);
	}
}
