package net.myserver.engine.implementation.spleef.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.myserver.engine.game.kit.Kit;

public class SpleefFreezerKit extends Kit{

	public SpleefFreezerKit() 
	{
		super("Freezer", new ItemStack(Material.FIREWORK_CHARGE), "Freeze Bomb", 15);
	}

	@Override
	public void onUseAbility(Player player) 
	{
		List<Entity> entities = player.getNearbyEntities(6.0D, 6.0D, 6.0D);
		for (Entity entity : entities)
		{
			if ((entity instanceof Player)) 
			{
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 10));
				entity.getWorld().playSound(entity.getLocation(), Sound.FIZZ, 1, 1);
			}	
		}
	}

}
