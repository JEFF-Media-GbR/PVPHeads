package de.jeff_media.pvpheads;

import de.jeff_media.jefflib.SkullUtils;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class PVPHeads extends JavaPlugin implements Listener {

    @Getter private static PVPHeads instance;
    @Getter private static net.milkbowl.vault.economy.Economy economy;

    public void onEnable() {
        instance = this;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        economy = rsp.getProvider();

        getCommand("sellhand").setExecutor(new SellHeadCommand());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        if(victim.getKiller() == null) return;
        Player killer = victim.getKiller();

        if(!Utils.dropChanceSuccess(killer)) return;

        ItemStack head = SkullUtils.getPlayerHead(victim.getUniqueId());
        victim.getWorld().dropItem(victim.getLocation(), head);
    }


}
