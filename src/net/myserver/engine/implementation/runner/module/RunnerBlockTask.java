package net.myserver.engine.implementation.runner.module;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.myserver.engine.SuperEngine;

public class RunnerBlockTask extends BukkitRunnable{
	
	private SuperEngine engine;
	private int taskId;
	private ArrayList<Location> blockLocationToDrop;
	
	public RunnerBlockTask(SuperEngine engine)
	{
		this.engine = engine;
		blockLocationToDrop = new ArrayList<Location>();
	}
	
	@SuppressWarnings("deprecation")
	public void startTask()
	{
		blockLocationToDrop.clear();
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(engine, this, 1l, 1l);
	}
	
	public void cancelTask()
	{
		Bukkit.getServer().getScheduler().cancelTask(taskId);	
	}

	@Override
	public void run() 
	{
		for(Player player : Bukkit.getServer().getOnlinePlayers())
		{
			if(player.getGameMode().equals(GameMode.SPECTATOR))
				return;
			dropBlock(player.getLocation().add(0,-1,0));
			if(player.getLocation().getX() - player.getLocation().getBlockX() >= 0.75)
				dropBlock(player.getLocation().add(1,-1,0));
			if(player.getLocation().getZ() - player.getLocation().getBlockZ() >= 0.75)
				dropBlock(player.getLocation().add(0,-1,1));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void dropBlock(final Location location)
	{
		if(location.getBlock().getType() != Material.AIR && !blockLocationToDrop.contains(location))
		{
			blockLocationToDrop.add(location);
			Block block = location.getBlock();
			block.setType(Material.STAINED_CLAY);
			block.setData((byte) 14);
			Bukkit.getScheduler().runTaskLater(engine, new Runnable()	    
			{
				public void run() 
			    {
			    location.getWorld().getBlockAt(location).setType(Material.AIR); 
			    location.getWorld().spawnFallingBlock(location, Material.STAINED_CLAY, (byte) 14);
			    blockLocationToDrop.remove(location);
			    }
			}, 10l);
		}
	}
}
