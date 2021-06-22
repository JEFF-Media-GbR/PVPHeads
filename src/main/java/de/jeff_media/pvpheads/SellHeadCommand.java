package de.jeff_media.pvpheads;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SellHeadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        System.out.println("sellhead command called");

        if(!(sender instanceof Player)) {
            System.out.println("This command can only be run by players");
            return true;
        }

        Player player = (Player) sender;
        ItemStack hand = player.getInventory().getItemInMainHand();

        if(hand.getType() != Material.PLAYER_HEAD) {
            player.sendMessage("§cYou must hold a player's head in your main hand.");
            return true;
        }

        int sellPrice = Utils.getSellPrice();
        System.out.println("Sell price: " + sellPrice);
        hand.setAmount(hand.getAmount()-1);
        PVPHeads.getEconomy().depositPlayer(player, sellPrice);
        player.sendMessage("§aYou sold a head for " + sellPrice + ".");

        return true;
    }
}
