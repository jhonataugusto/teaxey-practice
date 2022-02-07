package paper.kinox.listeners;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.EnderChest;
import org.bukkit.scheduler.BukkitRunnable;
import paper.kinox.apis.*;

import org.bukkit.util.Vector;
import paper.kinox.Kinox;

/**
 * Created by Sycatle on 29/09/2017.
 * Edited by Kinox on 01/02/2022.
 */

public class PotionListener implements Listener {

    private Double speed = Kinox.get().getConfig().getDouble("fastpot");

    @SuppressWarnings("deprecation")
    @EventHandler
    void onProjectileLaunch(final ProjectileLaunchEvent event) {
        if (event.getEntityType() == EntityType.SPLASH_POTION) {
            final Projectile projectile = event.getEntity();

            if ((projectile.getShooter() instanceof Player) && ((Player) projectile.getShooter()).isSprinting() && ((Player) projectile.getShooter()).getName().equals("BOT_AkuramaUS")) {
                final Vector velocity = projectile.getVelocity();

                velocity.setY(velocity.getY() - speed);
                projectile.setVelocity(velocity);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    void onPotionSplash(final PotionSplashEvent event) {
        if (event.getEntity().getShooter() instanceof Player && ((Player) event.getEntity().getShooter()).getName().equals("BOT_AkuramaUS")) {
            final Player shooter = (Player) event.getEntity().getShooter();

            if (shooter.isSprinting() && event.getIntensity(shooter) > 0.6D) {
                event.setIntensity(shooter, 1.0D);
            }
        }
    }

    @EventHandler
    public void aumentarDanoBot(EntityDamageByEntityEvent event){
        Entity player = (Player) event.getDamager();
        if(player.getName().equals("BOT_AkuramaUS")){
            event.setDamage(event.getDamage()+5D);
        }
    }
}

