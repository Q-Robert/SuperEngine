package net.myserver.engine.implementation.runner.kit;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.myserver.engine.game.kit.Kit;

public class RunnerRabbitKit extends Kit{

	public RunnerRabbitKit() 
	{
		super("Rabbit", new ItemStack(Material.RABBIT_FOOT), "Rabit Boost", 15);
	}

	@Override
	public void onUseAbility(Player player) 
	{
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 8 * 20, 2));
		player.getWorld().playSound(player.getLocation(), Sound.SLIME_WALK, 1, 1);
	}

}
