package paper.kinox.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import paper.kinox.Kinox;

/**
 * Created by Sycatle on 29/09/2017.
 */

public class FastpotCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(Kinox.get().getConfig().get("permission").toString())) {
            if (args.length == 0) {
                sender.sendMessage("§cUso correto: /fastpot <get/set/reload> <valores>");
            } else if (args.length >= 1) {
                if (args[0].equals("get")) {
                    sender.sendMessage("§aValores de fastpot: " + Kinox.get().getFastpotValue());
                } else if (args[0].equals("set")) {
                    if (args.length >= 2) {
                        Double arg = NumberUtils.toDouble(args[1], -1D);

                        if (arg < 0D) {
                            sender.sendMessage("§cValor invalido de fastpot.");
                            return true;
                        }

                        sender.sendMessage("§aFastpot de " + Bukkit.getServerName() + " setado para " + arg + ".");
                        Kinox.get().setFastpotTo(arg);
                    } else {
                        sender.sendMessage("§cUso correto: /fastpot <set> <valores>");
                    }
                } else if (args[0].equals("reload")) {
                    Kinox.get().reloadConfig();
                    sender.sendMessage("§aFastpot configuracao foi reiniciada.");
                } else {
                    sender.sendMessage("§cUso correto: /fastpot <get/set/reload> <valores>");
                }
            } else {
                sender.sendMessage("§cUso correto: /fastpot <get/set/reload> <valores>");
            }
        } else {
            sender.sendMessage("§cSem permissao");
        }
        return false;
    }
}
