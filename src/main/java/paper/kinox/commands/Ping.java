package paper.kinox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("ping")){

            if(args.length == 0){

                if(player.getName().equals("Shewingum") || player.getName().equals("decemberteaxey") || player.getName().equals("ZonenPvP") ||
                        player.getName().equals("KyuremPvP") || player.getName().equals("Tringed") || player.getName().equals("Tringod") ||
                        player.getName().equals("killcold") || player.getName().equals("Nokatoh") || player.getName().equals("Yakyosu") ||
                        player.getName().equals("AkuramaUS") || player.getName().equals("ghalaq")) {
                    int ping = ((CraftPlayer) player).getHandle().ping;
                    double pingMultiplication = ping * 0.8;
                    player.sendMessage(ChatColor.GREEN+"Seu ping: "+Math.round(pingMultiplication)+"ms");
                } else {
                    int ping = ((CraftPlayer) player).getHandle().ping;
                    double pingMultiplication = ping * 0.9;
                    player.sendMessage(ChatColor.GREEN+"Seu ping: "+Math.round(pingMultiplication)+"ms");
                }


            } if(args.length >= 1){
                Player target = Bukkit.getPlayer(args[0]);

                if(target.getName().equals("Shewingum") || target.getName().equals("decemberteaxey") || target.getName().equals("ZonenPvP") ||
                        target.getName().equals("KyuremPvP") || target.getName().equals("Tringed") || target.getName().equals("Tringod") ||
                        target.getName().equals("killcold") || target.getName().equals("Nokatoh") || target.getName().equals("Yakyosu") ||
                        target.getName().equals("AkuramaUS") || target.getName().equals("ghalaq"))
                {
                    int ping = ((CraftPlayer) target).getHandle().ping;
                    double pingMultiplication = ping*0.8;
                    player.sendMessage(ChatColor.GREEN+"Ping de "+target.getName()+": "+Math.round(pingMultiplication)+"ms");

                }else {

                    int ping = ((CraftPlayer) target).getHandle().ping;
                    double pingMultiplication = ping*0.9;
                    player.sendMessage(ChatColor.GREEN+"Ping de "+target.getName()+": "+Math.round(pingMultiplication)+"ms");

                }
            }
        }
        return false;
    }
}
