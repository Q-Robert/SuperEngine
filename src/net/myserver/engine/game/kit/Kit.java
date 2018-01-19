package net.myserver.engine.game.kit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Kit {
	
	private String name;
	private ItemStack item;
	private int cooldownTime;
	
	public Kit(String name, ItemStack item, String itemName, int cooldownTime)
	{
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.AQUA + itemName);
		item.setItemMeta(itemMeta);
		
		this.name = name;
		this.item = item;
		this.cooldownTime = cooldownTime;
	}
	
	public abstract void onUseAbility(Player player);
	
	public String getName()
	{
		return name;
	}
	
	public ItemStack getItem()
	{
		return item;
	}
	
	public int getCooldownTime()
	{
		return cooldownTime;
	}

}
