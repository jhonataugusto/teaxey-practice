package paper.kinox;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import paper.kinox.apis.ItemAPI;
import paper.kinox.commands.FastpotCommand;
import paper.kinox.commands.Lista;
import paper.kinox.commands.Ping;
import paper.kinox.listeners.PotionListener;

import java.util.Iterator;

public final class Kinox extends JavaPlugin implements Listener {


    private static Kinox instance;

    public static Kinox get() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY +"Plugin Feito por : KINOX");

        getConfig().options().copyDefaults(true);
        getConfig().addDefault("fastpot", 1.0);
        getConfig().addDefault("permission", "fastpot.command");
        saveConfig();

        reloadConfig();

        registerListeners();
        registerCommands();

    }

    @Override
    public void onDisable() {
        instance = null;

    }

    //------------------ MY COMMANDS ------------------------------------

    public void loadConfig(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        event.getPlayer().setHealth(20);
        event.getPlayer().setSaturation(20);
        event.getPlayer().setFoodLevel(20);

        player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT,5,5);
        player.sendMessage(ChatColor.GRAY+""+ChatColor.UNDERLINE+"");
        player.sendMessage(ChatColor.RED+"         Teaxey Practice               ");
        player.sendMessage(ChatColor.RED+"        versao: 1.0-ALPHA              ");
        player.sendMessage(ChatColor.GRAY+""+ChatColor.UNDERLINE+"");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void aoTeleportar(PlayerTeleportEvent event) {
        if(!(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            new BukkitRunnable() {
                @Override
                public void run() {
                    Player p = event.getPlayer();
                    p.setSaturation(14L);
                    p.setHealth(20);

                }
            }.runTaskLater(this, 1);   // Your plugin instance, the time to be delayed.
        }
    }

    @EventHandler
    public void naosei(PlayerDeathEvent event){
        Player p = event.getEntity();
        Iterator<ItemStack> iterator = event.getDrops().iterator();

        while(iterator.hasNext()){
            ItemStack i = iterator.next();
                iterator.remove();
        }

        p.getWorld().playEffect(p.getLocation(),Effect.GHAST_SHOOT, 152);
    }

    //------------------ POTION COMAMNDS ------------------------------------

    private void registerCommands() {
        getCommand("fastpot").setExecutor(new FastpotCommand());
        getCommand("ping").setExecutor(new Ping());
        getCommand("players").setExecutor(new Lista());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PotionListener(), this);
    }

    public void setFastpotTo(Double values) {
        getConfig().set("fastpot", values);
        saveConfig();
        reloadConfig();
    }

    public Double getFastpotValue() {
        return getConfig().getDouble("fastpot");
    }

    // COMMANDS TO HIDE


    @EventHandler
    public void cancelCommand(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().startsWith("/spawn") || event.getMessage().startsWith("/SPAWN")) {
            if(!(event.isCancelled())) event.setCancelled(true);
        }

        if(event.getMessage().startsWith("/list") || event.getMessage().startsWith("/LIST")) {
            if(!(event.isCancelled())) event.setCancelled(true);
        }

    }

    // ---------------------------- CHEST CODES ------------------------------------

    @EventHandler
    public void clickChest (PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.CHEST){

            new BukkitRunnable() {
                @Override
                public void run() {
                    createItemInventory(e.getPlayer());
                }
            }.runTaskLater(this, 1);   // Your plugin instance, the time to be delayed.
        }
    }

    public void createItemInventory(Player player){

        player.closeInventory();

        Inventory inventorykits = Bukkit.createInventory(null,54,ChatColor.RED+"Seletor de Kits");

        //carne batata cenoura
        ItemStack fireResistance = ItemAPI.newItem(Material.POTION,"fire_resistance",1,8226);
        ItemStack speed = ItemAPI.newItem(Material.POTION,"speed",1,8259);
        ItemStack healingPotion = ItemAPI.newItem(Material.POTION,"potion",1,16421);
        //COMIDAS
        ItemStack cooked_beef = ItemAPI.newItem(Material.COOKED_BEEF,"cooked beef",64);
        ItemStack goldenCarrot = ItemAPI.newItem(Material.GOLDEN_CARROT,"golden carrot",64);
        ItemStack fish = ItemAPI.newItem(Material.COOKED_FISH,"fish",64);
        ItemStack rottenFlesh = ItemAPI.newItem(Material.ROTTEN_FLESH,"rotten flesh",64);
        ItemStack glass = ItemAPI.newItem(Material.STAINED_GLASS,"glass",1);



        //VERTICAL GLASS
        inventorykits.setItem(1,glass);
        inventorykits.setItem(10,glass);
        inventorykits.setItem(19,glass);
        inventorykits.setItem(28,glass);
        inventorykits.setItem(37,glass);
        inventorykits.setItem(46,glass);
        //HORIZONTAL GLASS
        for(int i = 9 ; i <= 17 ; i++){
            if(!(i == 10)){
                inventorykits.setItem(i,glass);
            }
        }
        //FOODS
        inventorykits.setItem(0,rottenFlesh);
        inventorykits.setItem(6,cooked_beef);
        inventorykits.setItem(7,goldenCarrot);
        inventorykits.setItem(8,fish);
        //SPEED POTIONS
        inventorykits.setItem(18,speed);
        inventorykits.setItem(27,speed);
        inventorykits.setItem(36,speed);
        inventorykits.setItem(45,speed);
        //FIRE RESISTANCE POTIONS
        for(int i = 2; i <= 5 ; i++){
            inventorykits.setItem(i,fireResistance);
        }
        //POTIONS

        for(int i=20; i<=53;i++){
            if(!(i == 18 || i == 19 || i == 27 || i == 28 || i == 36 || i == 37 || i == 45 || i == 46)){
                inventorykits.setItem(i,healingPotion);
            }
        }

        player.openInventory(inventorykits);
    }

    // SIGN CODES |-----------------------

    @EventHandler
    public void aoClicarNaPlaca(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location godRiky = new Location(player.getWorld(),208,219,84);

        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(block.getType() == Material.JUKEBOX){
                    player.sendMessage(ChatColor.GREEN+"Teleportado para um lugar desconhecido.");
                    player.playSound(player.getLocation(),Sound.ENDERMAN_TELEPORT,5,5);
                    player.teleport(godRiky);
            }

        }
    }


}

