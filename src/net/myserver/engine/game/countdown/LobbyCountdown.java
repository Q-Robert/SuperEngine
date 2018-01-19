package net.myserver.engine.game.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import net.myserver.engine.SuperEngine;

public class LobbyCountdown extends BukkitRunnable{
	
	private SuperEngine engine;
	private int remaining;
	private int countdownId;
	
	@SuppressWarnings("deprecation")
	public LobbyCountdown(SuperEngine engine)
	{
		this.engine = engine;
		remaining = 30;
		countdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask(engine, this, 20l, 20l);
	}
	
	public void setTime(int remaining)
	{
		this.remaining = remaining;
	}

	public void cancelTask()
	{
		Bukkit.getServer().getScheduler().cancelTask(countdownId);	
	}

	@Override
	public void run() {
		for(Player player : Bukkit.getServer().getOnlinePlayers())
			engine.getScoreboardManager().getScoreboard(player).editLine(13, ChatColor.GRAY + String.valueOf(remaining));
		remaining--;
		if(remaining == 0)
		{
			Bukkit.getScheduler().cancelTask(countdownId);	
			engine.getGameManager().getGame().preStart();
			for(Player player : Bukkit.getServer().getOnlinePlayers())
				engine.getScoreboardManager().getScoreboard(player).editLine(13, ChatColor.GRAY + "Started");
		}
	}

}
