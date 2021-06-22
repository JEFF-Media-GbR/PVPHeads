package de.jeff_media.pvpheads;

import org.bukkit.entity.Player;

import java.util.Random;

public class Utils {

    private static final Random rand = new Random();

    public static boolean dropChanceSuccess(Player player) {
        return rand.nextInt(100) <= getDropChance(player);
    }

    private static int getDropChance(Player player) {
        for(int i = 100; i > 0; i--) {
            if(player.hasPermission("pvpheads.dropchance."+i)) {
                return i;
            }
        }
        return 0;
    }

    public static int getSellPrice() {
        int maxPrice = PVPHeads.getInstance().getConfig().getInt("max-price");
        int minPrice = PVPHeads.getInstance().getConfig().getInt("max-price");
        return rand.nextInt(maxPrice - minPrice + 1) - minPrice;
    }
}