package me.oyuncozucu.sojet.listeners;

import me.oyuncozucu.sojet.SoJet;
import me.oyuncozucu.sojet.commands.UpCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JetListener implements Listener {

    private SoJet plugin;

    public JetListener(SoJet plugin) {
        this.plugin = plugin;
    }

    public ItemStack jetItem(Player player) {

        ItemStack jet = new ItemStack(Material.FIREWORK);

        ItemMeta jet_ = jet.getItemMeta();
        String name = plugin.getConfig().getString("item-name");
        jet_.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        jet.setItemMeta(jet_);

        return jet;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        int slot = plugin.getConfig().getInt("item-slot");
        player.getInventory().setItem(slot, jetItem(player));

    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        Player player = e.getPlayer();

        int slot = plugin.getConfig().getInt("item-slot");
        player.getInventory().setItem(slot, jetItem(player));

    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();

        int slot = plugin.getConfig().getInt("item-slot");
        player.getInventory().setItem(slot, jetItem(player));

    }

    @EventHandler
    public void onJetRightClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) {
            String dname = plugin.getConfig().getString("item-name");
            if (!(e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(dname))) {

                e.getPlayer().playSound(player.getLocation(), Sound.ENDERDRAGON_HIT, 10F, 100);

                player.performCommand("ilsadtasc");

            }
        }

    }
    @EventHandler
    public void onJetLefClick(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (!(e.getAction() == Action.LEFT_CLICK_AIR)) {
            String dname = plugin.getConfig().getString("item-name");
            if (!(e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(dname))) {

                e.getPlayer().playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10F, 20);
                player.performCommand("dasgfsgrs");

            }
        }

    }


}
