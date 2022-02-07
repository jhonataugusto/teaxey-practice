package paper.kinox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lista implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("players")){
                if(args.length == 0){
                    player.sendMessage(ChatColor.GREEN+"Lista de jogadores online:");
                    for(Player player1 : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.GREEN+"- "+player1.getName());
                    }
                }
            }
        return false;
    }
}
