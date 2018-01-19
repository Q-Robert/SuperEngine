package net.myserver.engine.lobby.module;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;
import net.myserver.engine.SuperEngine;
import net.myserver.engine.module.Module;

public class LobbyLeaveModule extends Module {

	public LobbyLeaveModule(SuperEngine engine) {
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
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		for(Player players : Bukkit.getServer().getOnlinePlayers())
			getEngine().getScoreboardManager().getScoreboard(players).editLine(19, ChatColor.GRAY + String.valueOf(Bukkit.getServer().getOnlinePlayers().size()-1) + "/"+ getEngine().getGameManager().getGame().getMaxPlayers());
		if(Bukkit.getServer().getOnlinePlayers().size() == 2)
		{
			if(getEngine().getLobby().getCountdown() != null)
			getEngine().getLobby().getCountdown().cancelTask();
			for(Player player : Bukkit.getServer().getOnlinePlayers())
				getEngine().getScoreboardManager().getScoreboard(player).editLine(13, ChatColor.GRAY + "30");
		}
	}

}
