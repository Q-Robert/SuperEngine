package net.myserver.engine.game.countdown;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;

public class EndGameCountdown extends BukkitRunnable{
	
	private Game game;
	private int remaining;
	private int countdownId;
	
	@SuppressWarnings("deprecation")
	public EndGameCountdown(SuperEngine engine, Game game)
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
			float f = 0 + new Random().nextFloat() * (2.5F - 0);
			player.getWorld().playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, 5);
			player.getWorld().playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, 5);
			player.getWorld().playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, 5);
			player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
			player.getWorld().playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 2, f);
			player.getWorld().playSound(player.getLocation(), Sound.FIREWORK_BLAST, 2, f);
		}
		remaining--;
		if(remaining == 0)
		{
			Bukkit.getScheduler().cancelTask(countdownId);
			game.stop();
		}
	}

}
