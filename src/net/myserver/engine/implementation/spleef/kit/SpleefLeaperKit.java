package net.myserver.engine.implementation.spleef.kit;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.myserver.engine.game.kit.Kit;

public class SpleefLeaperKit extends Kit{

	public SpleefLeaperKit() 
	{
		super("Leaper", new ItemStack(Material.IRON_AXE), "Leaper Axe", 15);
	}

	@Override
	public void onUseAbility(Player player) 
	{
		Vector vec = player.getLocation().getDirection().normalize().multiply(1.5);
		player.setVelocity(vec);
		player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
		player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
	}

}
