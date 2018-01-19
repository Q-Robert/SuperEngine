package net.myserver.engine.nms;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
 
public class GameHotbar 
{
        private Player player;
        private String text;
 
        public GameHotbar(Player player, String text)
        {
               this.player = player;
                this.text = text;
        }
 
        public GameHotbar(String text)
        {
                this(null, text);
        }
 
        public void send()
        {
                try
                {
                        IChatBaseComponent text = ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', this.text) + "\"}");
                        PacketPlayOutChat chat = new PacketPlayOutChat(text, (byte) 2);
 
                        if (player == null)
                        {
                                for (Player players : Bukkit.getOnlinePlayers())
                                {
                                        sendPacket(players, chat);
                                }
                        }
                        else
                        {
                                sendPacket(player, chat);
                        }
                }
                catch (Exception e)
                {
                	System.out.print("Could not send game hotbar packet.");
                }      
        }
       
        @SuppressWarnings("rawtypes")
		private void sendPacket(Player player, Packet packet)
        {
                CraftPlayer craft = (CraftPlayer) player;
                EntityPlayer nms = craft.getHandle();
               
                nms.playerConnection.sendPacket(packet);
        }
}