package me.thevipershow.constantslookup.commands;

import me.thevipershow.constantslookup.MinecraftUniversity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ConstantsCommand implements CommandExecutor {

    private final MinecraftUniversity mcUni;

    public ConstantsCommand(@NotNull MinecraftUniversity mcUni) {
        this.mcUni = mcUni;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (!(commandSender instanceof Player p)) {

        } else {

        }
        return false;
    }
}
