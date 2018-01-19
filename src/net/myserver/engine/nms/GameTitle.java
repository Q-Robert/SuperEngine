package net.myserver.engine.nms;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class GameTitle 
{

    public GameTitle(Player player, EnumTitleAction titleType, String text, int fadeInTime, int showTime, int fadeOutTime, ChatColor color)
    {
        IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\",color:" + color.name().toLowerCase() + "}");
    
        if(titleType != EnumTitleAction.TITLE && titleType != EnumTitleAction.SUBTITLE) return;
        PacketPlayOutTitle title = new PacketPlayOutTitle(titleType, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInTime, showTime ,fadeOutTime);
    
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }

}
 