package net.myserver.engine.game.kit;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.myserver.engine.SuperEngine;

public class KitTask extends BukkitRunnable{
	
	private SuperEngine engine;
	private int taskId;
	private HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	public KitTask(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	@SuppressWarnings("deprecation")
	public void startTask()
	{
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(engine, this, 20l, 20l);
	}
	
	public void startCooldown(Player player, int time)
	{
		cooldown.put(player, time);
	}

	public void cancelTask()
	{
		Bukkit.getServer().getScheduler().cancelTask(taskId);	
	}

	@Override
	public void run() {
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
			if(!cooldown.containsKey(player))
				return;
			cooldown.put(player, getRemaining(player)-1);
			if(getRemaining(player) == 0)
				cooldown.remove(player);
		}
	}
	
	public int getRemaining(Player player)
	{
		return cooldown.get(player);
	}
	
	public boolean isCoolingdown(Player player)
	{
		if(cooldown.containsKey(player))
			return true;
		return false;
	}

}
