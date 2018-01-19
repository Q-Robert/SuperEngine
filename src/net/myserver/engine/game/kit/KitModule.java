package net.myserver.engine.game.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;

import net.myserver.engine.SuperEngine;
import net.myserver.engine.game.Game;
import net.myserver.engine.module.Module;

public class KitModule extends Module {
	
	private Game game;
	private SuperEngine engine;
	private KitTask task;
	
	public KitModule(SuperEngine engine, Game game)
	{
		super(engine);
		this.game = game;
		this.engine = engine;
		task = new KitTask(engine);
	}

	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, engine.getInstance());
		task.startTask();
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
		task.cancelTask();
	}

	@EventHandler
	public void onKitAbilitUse(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Kit kit = game.getKit(player);
		if(player.getItemInHand().getType() != kit.getItem().getType())
			return;
		if(task.isCoolingdown(player))
		{
			player.sendMessage(ChatColor.GREEN + "You must wait: " + task.getRemaining(player) + " seconds before you can use your ability again.");
			return;
		}
		kit.onUseAbility(player);
		task.startCooldown(player, kit.getCooldownTime());
	}
}
