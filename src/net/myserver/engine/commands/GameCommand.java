package net.myserver.engine.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;
import net.myserver.engine.game.gamesystem.LinearGameSystem;
import net.myserver.engine.game.gamesystem.RandomGameSystem;
import net.myserver.engine.util.ChatUtil;

public class GameCommand implements CommandExecutor{
	
	private SuperEngine engine;
	
	public GameCommand(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("game"))
		{
			Player player = (Player) sender;
			if(!player.isOp())
			{
				player.sendMessage(ChatUtil.sendGameMessage("You don't have permission to run this command."));
				return false;
			}
			if(args.length < 1)
			{
				player.sendMessage(ChatUtil.sendGameMessage("Too few arguments."));
				return false;
			}
			if(args[0].equalsIgnoreCase("set"))
			{
				if(args.length < 2)
				{
					player.sendMessage(ChatUtil.sendGameMessage("Too few arguments."));
					return false;
				}
				for(Game game : engine.getGameManager().getGameSystem().getGames())
				{
					String gameTitle = game.getGameTitle().replace(" ", "");
					if(args[1].equalsIgnoreCase(gameTitle))
					{
						for(Player players : Bukkit.getServer().getOnlinePlayers())
						{
							players.sendMessage(ChatUtil.sendGameMessage("The game was set to: " + ChatColor.GRAY + "[" + ChatColor.RED + game.getGameTitle().toUpperCase() + ChatColor.GRAY + "] " + ChatColor.WHITE + "by an operator."));
							players.getWorld().playSound(players.getLocation(), Sound.NOTE_PLING, 1, 1);
							engine.getGameManager().setGame(game);
						}
						return false;
					}
				}
				player.sendMessage(ChatUtil.sendGameMessage("Game not found."));
			}else if(args[0].equalsIgnoreCase("start"))
			{
				if(engine.getGameManager().getGame() == null)
				{
					player.sendMessage(ChatUtil.sendGameMessage("No starting game found."));
					return false;
				}
				if(engine.getLobby().getCountdown() == null)
				{
					player.sendMessage(ChatUtil.sendGameMessage("No starting game found."));
					return false;
				}
				engine.getLobby().getCountdown().setTime(3);
				Bukkit.broadcastMessage(ChatUtil.sendGameMessage("Countdown skipped by operator."));
			}else if(args[0].equalsIgnoreCase("stop"))
			{
				if(engine.getGameManager().getGame() == null)
				{
					player.sendMessage(ChatUtil.sendGameMessage("No game found."));
					return false;
				}
				engine.getGameManager().getGame().end("NO ONE");
			}else if(args[0].equalsIgnoreCase("setlinear"))
			{
				engine.getGameManager().setGameSystem(new LinearGameSystem(engine));
				Bukkit.broadcastMessage(ChatUtil.sendGameMessage("The game system has been set to: linear."));
			}else if(args[0].equalsIgnoreCase("setrandom"))
			{
				engine.getGameManager().setGameSystem(new RandomGameSystem(engine));
				Bukkit.broadcastMessage(ChatUtil.sendGameMessage("The game system has been set to: random."));
			}
		}
		return false;
			}
	

}
