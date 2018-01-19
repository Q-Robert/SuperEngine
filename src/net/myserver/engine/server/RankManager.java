package net.myserver.engine.server;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class RankManager {
	
	private HashMap<Player, ServerRank> serverRank;
	
	public RankManager()
	{
		serverRank = new HashMap<Player, ServerRank>();
	}
	
	public void setRank(Player player, ServerRank rank)
	{
		serverRank.put(player, rank);
	}
	
	public boolean hasRank(Player player)
	{
		if(serverRank.containsKey(player))
			return true;
		return false;
	}
	
	public ServerRank getRank(Player player)
	{
		return serverRank.get(player);
	}

}
