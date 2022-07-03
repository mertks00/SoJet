package me.oyuncozucu.sojet;

import me.oyuncozucu.sojet.commands.SpeedCommand;
import me.oyuncozucu.sojet.commands.UpCommand;
import me.oyuncozucu.sojet.listeners.JetListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoJet extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("dasgfsgrs").setExecutor(new UpCommand());
        getCommand("ilsadtasc").setExecutor(new SpeedCommand());
        getCommand("reload").setExecutor(this);

        Bukkit.getPluginManager().registerEvents(new JetListener(this), this);


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equals("reload")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("sojet.reload")) {
                    this.reloadConfig();
                    player.sendMessage(ChatColor.GREEN+"Config Reloaded!");

                } else {
                    player.sendMessage(ChatColor.RED+"Buna izniniz yok!");

                }
            }
        }
        return true;
    }
}
