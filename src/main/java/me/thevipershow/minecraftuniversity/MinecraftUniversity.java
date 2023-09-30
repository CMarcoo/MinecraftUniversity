package me.thevipershow.minecraftuniversity;

import me.thevipershow.minecraftuniversity.commands.ConstantsCommand;
import me.thevipershow.minecraftuniversity.listeners.InventoryClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftUniversity extends JavaPlugin {

    private ConstantsCommand constantsCommand;

    private void registerCommands() {
        constantsCommand = new ConstantsCommand(this);
        getServer().getPluginCommand("constants").setExecutor(constantsCommand);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(constantsCommand),this);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        registerCommands();
        registerListeners();
    }
}
