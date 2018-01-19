package net.myserver.engine.server;

import org.bukkit.ChatColor;

public enum ServerRank {
	
	VIP("VIP"),
	COHOST("Co-Host"),
	HOST("Host");
	
	private String display;
	
	ServerRank(String display)
	{
		this.display = display;
	}
	
	public String getPrefix()
	{
		return ChatColor.BOLD + "" + ChatColor.DARK_GREEN + display.toUpperCase(); 
	}

}
