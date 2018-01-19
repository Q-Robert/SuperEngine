package net.myserver.engine.game.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;
import net.myserver.engine.util.ChatUtil;

public class PreGameCountdown extends BukkitRunnable{
	
	private Game game;
	private int remaining;
	private int countdownId;
	
	@SuppressWarnings("deprecation")
	public PreGameCountdown(SuperEngine engine, Game game)
	{
		this.game = game;
		remaining = 10;
		countdownId = Bukkit.getScheduler().scheduleSyncRepeatingTask(engine, this, 20l, 20l);
	}
	
	public void setTime(int remaining)
	{
		this.remaining = remaining;
	}

	@Override
	public void run() {
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
			player.sendMessage(ChatUtil.sendGameMessage(remaining + " seconds until the game begins!"));
			player.getWorld().playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
		}
		remaining--;
		if(remaining == 0)
		{
			Bukkit.broadcastMessage(ChatUtil.sendGameMessage("The game has started. Good luck!"));
			Bukkit.getServer().getScheduler().cancelTask(countdownId);	
			game.start();
		}
	}

}
