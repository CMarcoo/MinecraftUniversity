package me.thevipershow.minecraftuniversity.commands;

import lombok.Getter;
import me.thevipershow.minecraftuniversity.MinecraftUniversity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Class responsible for enabling and disabling the study mode.
 */
@Getter
public class StudyCommand implements CommandExecutor {

    /**
     * All the players whose UUID is stored here are in study mode.
     */
    private final Collection<UUID> inspectionPlayers = new HashSet<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(MinecraftUniversity.PREFIX + "§4§4You cannot execute this command from a terminal!");
        } else if (player.hasPermission("mcuni.study")) {
            UUID playerUUID = player.getUniqueId();
            if (inspectionPlayers.contains(playerUUID)) {
                inspectionPlayers.remove(playerUUID);
                player.sendMessage(MinecraftUniversity.PREFIX + "§aYou have now disabled study mode.");
            } else {
                inspectionPlayers.add(playerUUID);
                player.sendMessage(MinecraftUniversity.PREFIX + "§aYou have now enabled study mode.");
            }
        } else {
            player.sendMessage(MinecraftUniversity.PREFIX + "§4You do not have the necessary permission to study materials!");
        }
        return false;
    }
}
