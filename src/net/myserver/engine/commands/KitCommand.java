package net.myserver.engine.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.kit.Kit;
import net.myserver.engine.util.ChatUtil;

public class KitCommand implements CommandExecutor{
	
	private SuperEngine engine;
	
	public KitCommand(SuperEngine engine)
	{
		this.engine = engine;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(cmd.getName().equalsIgnoreCase("kit"))
		{
			Player player = (Player) sender;
			if(player.getWorld().getName() != engine.getLobby().getWorldName())
			{
				player.sendMessage(ChatUtil.sendGameMessage("You can't choose a kit if you are not in the lobby."));
				return false;
			}
			if(args.length < 1)
			{
				String kits = ChatColor.GREEN + "Available kits for this game are: " + ChatColor.AQUA;
				for(Kit kit : engine.getGameManager().getGame().getKits())
				{
					kits = kits + kit.getName() + " ";
				}
				player.sendMessage(kits);
				return false;
			}
			for(Kit kit : engine.getGameManager().getGame().getKits())
			{
				if(args[0].equalsIgnoreCase(kit.getName()))
				{
					engine.getGameManager().getGame().setKit(player, kit);
					player.sendMessage(ChatUtil.sendGameMessage("You selected kit: " + kit.getName() + "."));
					return false;
				}
			}
			player.sendMessage(ChatUtil.sendGameMessage("Kit not found."));
		}
		return false;
			}
	

}
