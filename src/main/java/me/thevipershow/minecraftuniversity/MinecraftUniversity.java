package me.thevipershow.minecraftuniversity;

import me.thevipershow.minecraftuniversity.commands.ConstantsCommand;
import me.thevipershow.minecraftuniversity.commands.StudyCommand;
import me.thevipershow.minecraftuniversity.listeners.InventoryClickListener;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftUniversity extends JavaPlugin {

    public static final String PREFIX = "§7[§cMCUniversity§7]§r: ";
    private ConstantsCommand constantsCommand;
    private StudyCommand studyCommand;

    /**
     * This function registers all the commands for this plugin.
     */
    private void registerCommands() {
        Server server = getServer();
        server.getPluginCommand("constants").setExecutor(constantsCommand = new ConstantsCommand(this));
        server.getPluginCommand("study").setExecutor(studyCommand = new StudyCommand());
    }

    /**
     * This function registers all the classes implementing Bukkit's Listener Interface
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(constantsCommand),this);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        registerCommands();
        registerListeners();
    }
}
