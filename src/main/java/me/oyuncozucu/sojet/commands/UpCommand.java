package me.oyuncozucu.sojet.commands;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UpCommand implements CommandExecutor {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {

                Vector playerDir = player.getEyeLocation().getDirection().setY(player.getLocation().getY());
                Vector otherDir = playerDir.clone().multiply(-0.3);
                otherDir.setY(1.5);

                player.setVelocity(otherDir);

                cooldown.put(player.getUniqueId(), System.currentTimeMillis());

            }


        }

        return true;
    }

}
