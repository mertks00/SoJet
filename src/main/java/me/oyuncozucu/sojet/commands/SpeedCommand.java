package me.oyuncozucu.sojet.commands;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SpeedCommand implements CommandExecutor {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {

                Vector playerDir = player.getEyeLocation().getDirection();
                Vector otherDir = playerDir.clone().multiply(-0.1);
                otherDir.setY(0.1);


                player.setVelocity(player.getLocation().getDirection().multiply(10));

                cooldown.put(player.getUniqueId(), System.currentTimeMillis());

            }


        }


        return true;
    }
}
