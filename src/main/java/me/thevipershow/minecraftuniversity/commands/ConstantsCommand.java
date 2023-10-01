package me.thevipershow.minecraftuniversity.commands;

import lombok.Getter;
import me.thevipershow.minecraftuniversity.MinecraftUniversity;
import me.thevipershow.minecraftuniversity.constants.MathematicsConstants;
import me.thevipershow.minecraftuniversity.constants.PhysicsConstants;
import me.thevipershow.minecraftuniversity.gui.GUIUtilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * The ConstantsCommand class is responsible for the creation, visualisation, and handling of the /constants command
 * and its relative user interfaces.
 * This class also pass values to {@link me.thevipershow.minecraftuniversity.listeners.InventoryClickListener} for checks.
 */
@Getter
public class ConstantsCommand implements CommandExecutor {

    private final MinecraftUniversity mcUni;

    /**
     * Main constructor for the command class.
     * @param mcUni The main instance of this plugin.
     */
    public ConstantsCommand(@NotNull MinecraftUniversity mcUni) {
        this.mcUni = mcUni;
    }

    private final Map<Integer, Inventory> mathInventoryMap = new HashMap<>();
    private final Map<Integer, Inventory> physInventoryMap = new HashMap<>();
    private final Map<MathematicsConstants, ItemStack> mathConstantsItems = new HashMap<>();
    private final Map<PhysicsConstants, ItemStack> physConstantsItems = new HashMap<>();

    /**
     * Creates the main inventory.
     */
    private void createMainInv() {
        GUIUtilities.fillInventoryRepeatedItem(GUIUtilities.GLASS_SLOTS_INV_3x9, GUIUtilities.MAIN_CONSTANTS_INV, GUIUtilities.GLASS_PANEL_BG);
        GUIUtilities.MAIN_CONSTANTS_INV.setItem(GUIUtilities.MATH_POS, GUIUtilities.MATH_SECTION_ITEM);
        GUIUtilities.MAIN_CONSTANTS_INV.setItem(GUIUtilities.PHYS_POS, GUIUtilities.PHYS_SECTION_ITEM);
    }


    /**
     * This method creates all the inventories for each constant category.
     * Several inventories will be created for each category, depending on how many constants there are.
     * Each constant inventory will present a maximum of 7*4 constants, and the three interaction buttons:
     * Next Page, Main Page and Back Page.
     * All of these inventories will be stored with their respective integer page number, and Inventory instance
     * inside the two maps named "mathInventoryMap" and "physInventoryMap".
     * A copy of all generated items, for each Constants class will also be stored in a set of its own.
     */
    private void createMathAndPhysInv() {
        if (!mathInventoryMap.isEmpty() && !physInventoryMap.isEmpty()) return;

        GUIUtilities.fillConstantsStyleInventoryGeneric(this.mathInventoryMap, this.mathConstantsItems, MathematicsConstants.values());
        GUIUtilities.fillConstantsStyleInventoryGeneric(this.physInventoryMap, this.physConstantsItems, PhysicsConstants.values());;
    }

    /**
     * This function opens the main Inventory necessary for the /constants command.
     * @param player The player opening the inventory.
     */
    private void openConstantsMenu(Player player) {
        createMainInv();
        createMathAndPhysInv();
        player.openInventory(GUIUtilities.MAIN_CONSTANTS_INV);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(MinecraftUniversity.PREFIX + "§4You cannot execute this command from a terminal!");
        } else {
            if (!player.hasPermission("mcuni.constants")) {
                player.sendMessage(MinecraftUniversity.PREFIX + "§4You do not have the necessary permission to view constants!");
                return false;
            }
            openConstantsMenu(player);
        }
        return false;
    }
}
