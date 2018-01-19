package net.myserver.engine.server;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.myserver.engine.SuperEngine;

public class SettingsListener implements Listener {
	
	private SuperEngine engine;
	
	public SettingsListener(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	@EventHandler
	public void move(PlayerMoveEvent event)
	{
		if((Boolean) ServerSettings.PLAYER_MOVE.getValue())
			return;
		if(event.getPlayer().isOp())
			return;
	    Location from = event.getFrom();
	    Location to = event.getTo();
	    double x=Math.floor(from.getX());
	    double z=Math.floor(from.getZ());
	    if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z)
	    {
	        x+=.5;
	        z+=.5;
	        event.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
	    }
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		if(engine.getRankManager().hasRank(event.getPlayer()))
		{
			event.setFormat(engine.getRankManager().getRank(event.getPlayer()).getPrefix() + " " + ChatColor.GOLD + event.getPlayer().getName() + " " + ChatColor.WHITE + event.getMessage());
			return;
		}
		if(event.getPlayer().isOp())
			event.setFormat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OP " + ChatColor.GOLD + event.getPlayer().getName() + " " + ChatColor.WHITE + event.getMessage());
		else
			event.setFormat(ChatColor.DARK_GRAY + event.getPlayer().getName() + " " + ChatColor.GRAY + event.getMessage());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		event.setJoinMessage(ChatColor.GREEN + "+ " + player.getName());
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		event.setQuitMessage(ChatColor.RED + "- " + player.getName());
	}
}
