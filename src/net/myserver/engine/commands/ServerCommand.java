package net.myserver.engine.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.server.DataType;
import net.myserver.engine.server.ServerRank;
import net.myserver.engine.server.ServerSettings;

public class ServerCommand implements CommandExecutor{
	
	private SuperEngine engine;
	
	public ServerCommand(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("server"))
		{
			if(!sender.isOp())
			{
				sender.sendMessage(ChatColor.RED + "You don't have permission to run this command.");
				return false;
			}
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.RED + "Not enough arguments.");
				return false;
			}
			if(args[0].equalsIgnoreCase("settings"))
			{
			ServerSettings key = ServerSettings.valueOf(args[1].toUpperCase());
			Object value = null;
			if(key.getType() == DataType.STRING)
				value = String.valueOf(args[2]);
			if(key.getType() == DataType.INT)
				value = Integer.valueOf(args[2]);
			if(key.getType() == DataType.BOOLEAN)
				value = Boolean.valueOf(args[2]);
			key.setValue(value);
			for(Player players : Bukkit.getServer().getOnlinePlayers())
				players.getWorld().playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
			Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "[Settings] " + ChatColor.GOLD + "" + ChatColor.BOLD +
					String.valueOf(key) + ChatColor.AQUA + " has been set to: " + ChatColor.RED + ChatColor.BOLD + String.valueOf(value) + ChatColor.AQUA + ".");
			}
			else if(args[0].equalsIgnoreCase("setrank"))
			{
				Player player = Bukkit.getPlayerExact(args[1]);
				if(player == null)
				{
					sender.sendMessage(ChatColor.RED + "Player not found!");
					return false;
				}
				ServerRank rank = ServerRank.valueOf(args[2].toUpperCase());
				sender.sendMessage(ChatColor.GREEN + "Succesfully promoted: " + player.getName());
				player.sendMessage(ChatColor.AQUA + "You have been promoted to " + ChatColor.GRAY + "[" + ChatColor.GREEN + "" + rank.getPrefix() + ChatColor.GRAY + "]");
				player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
				engine.getRankManager().setRank(player, rank);
			}
		}
		return false;
	}
	

}
