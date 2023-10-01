package me.thevipershow.minecraftuniversity;

import me.thevipershow.minecraftuniversity.commands.ConstantsCommand;
import me.thevipershow.minecraftuniversity.commands.StudyCommand;
import me.thevipershow.minecraftuniversity.listeners.BlockClickListener;
import me.thevipershow.minecraftuniversity.listeners.InventoryClickListener;
import me.thevipershow.minecraftuniversity.studyblocks.SpatialDrawingUtilities;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftUniversity extends JavaPlugin {

    public static final String PREFIX = "§7[§cMCUniversity§7]§r: ";
    private ConstantsCommand constantsCommand;
    private StudyCommand studyCommand;
    private Server server;
    private PluginManager pluginManager;

    /**
     * This function registers all the commands for this plugin.
     */
    private void registerCommands() {
        server = getServer();
        server.getPluginCommand("constants").setExecutor(constantsCommand = new ConstantsCommand(this));
        server.getPluginCommand("study").setExecutor(studyCommand = new StudyCommand());
    }

    /**
     * This function registers all the classes implementing Bukkit's Listener Interface
     */
    private void registerListeners() {
        pluginManager = server.getPluginManager();
        pluginManager.registerEvents(new InventoryClickListener(constantsCommand),this);
        pluginManager.registerEvents(new BlockClickListener(studyCommand), this);
    }

    @Override
    public void onEnable() { // Plugin startup logic
        registerCommands();
        registerListeners();
    }
}
