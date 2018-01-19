package net.myserver.engine.util;

import net.md_5.bungee.api.ChatColor;

public class ChatUtil {
	
	public static String sendGameMessage(String msg)
	{
		return ChatColor.GRAY + "[" + ChatColor.BOLD + ChatColor.RED + "GAME" + ChatColor.GRAY + "] " + ChatColor.WHITE + msg;
	}

}
