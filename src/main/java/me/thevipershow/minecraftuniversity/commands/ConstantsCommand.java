package me.thevipershow.minecraftuniversity.commands;

import lombok.Getter;
import me.thevipershow.minecraftuniversity.MinecraftUniversity;
import me.thevipershow.minecraftuniversity.constants.MathematicsConstants;
import me.thevipershow.minecraftuniversity.constants.PhysicsConstants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    private final static int INV_COLUMNS = 9, MAIN_INV_ROWS = 3, MAIN_INV_SIZE = INV_COLUMNS*MAIN_INV_ROWS, MATH_POS = 11, PHYS_POS = 13;
    private final Map<Integer, Inventory> mathInventoryMap = new HashMap<>();
    private final Map<Integer, Inventory> physInventoryMap = new HashMap<>();
    private final Map<MathematicsConstants, ItemStack> mathConstantsItems = new HashMap<>();
    private final Map<PhysicsConstants, ItemStack> physConstantsItems = new HashMap<>();
    private final Inventory mainConstantsInv = Bukkit.createInventory(null, MAIN_INV_SIZE, MinecraftUniversity.PREFIX + "§9Constants Menu");

    private final ItemStack backItem = createCustomItem(Material.RED_TERRACOTTA, 1, "§7[§cPrevious Page§7]", "§7Return to previous page");
    private final ItemStack nextItem = createCustomItem(Material.GREEN_TERRACOTTA, 1, "§7[§aNext Page§7]", "§7Move to next page");
    private final ItemStack mainMenuItem = createCustomItem(Material.GRAY_TERRACOTTA, 1, "§7[§fMain Menu§7]", "§7Return to the main menu");
    private final ItemStack mathSectionItem = createCustomItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, "§eMathematics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of mathematics.");
    private final ItemStack physicsSectionItem = createCustomItem(Material.ORANGE_GLAZED_TERRACOTTA, 1, "§bPhysics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of physics.");
    private final ItemStack glassPanelBg = createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, " ");

    /**
     * Creates a custom ItemStack with given characteristics.
     * @param material The material of the ItemStack.
     * @param amount The amount of items in the ItemStack.
     * @param name The display name of the ItemStack.
     * @param desc The lore of the ItemStack.
     * @return returns a new instance of the ItemStack with given characteristics.
     */
    @NotNull
    public static ItemStack createCustomItem(@NotNull Material material, int amount, @NotNull String name, String... desc) {
        final ItemStack item = new ItemStack(material, amount <= 0 ? 1 : amount);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (desc.length != 0)
            itemMeta.setLore(Arrays.asList(desc));
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Creates the main inventory.
     */
    private void createMainInv() {
        if (mainConstantsInv != null) return;

        Arrays.asList(0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26).forEach(v -> mainConstantsInv.setItem(v, glassPanelBg));

        mainConstantsInv.setItem(MATH_POS, mathSectionItem);
        mainConstantsInv.setItem(PHYS_POS, physicsSectionItem);
    }


    /**
     * Adds the navigation button items to an Inventory.
     * @param inv Inventory that will receive navigation button items.
     */
    private void addNavigateButtons(@NotNull Inventory inv) {
        inv.setItem(54 - 2, nextItem);
        inv.setItem(54 - 8, backItem);
        inv.setItem(54 - 5, mainMenuItem);
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

        for (int i = 0; i <= MathematicsConstants.values().length / (7 * 4); i++) {
            mathInventoryMap.put(i+1, Bukkit.createInventory(null, INV_COLUMNS*6, MinecraftUniversity.PREFIX + "§9Math Page " + (1+i)));
        }
        final MathematicsConstants[] values = MathematicsConstants.values();
        for (int v = 0; v < values.length; v++) {
            MathematicsConstants constant = values[v];
            ItemStack constantItem = createCustomItem(Material.END_CRYSTAL, 1, "§6" + constant.getName(),
                    "§7Symbol§r: §e" + constant.getSymbol(),
                    "§7Value§r: §e" + constant.getValue());
            mathInventoryMap.get(1+(v / (7*4))).setItem(9 + (v % ((7*4) - 1)), constantItem);
            this.mathConstantsItems.put(constant, constantItem);
        }

        for (int i = 0; i <= PhysicsConstants.values().length / (7*4); i++) {
            physInventoryMap.put(i+1, Bukkit.createInventory(null, INV_COLUMNS*6, MinecraftUniversity.PREFIX + "§9Physics Page " + (1+i)));
        }

        final PhysicsConstants[] pValues = PhysicsConstants.values();
        for (int v = 0; v < pValues.length; v++) {
            PhysicsConstants constant = pValues[v];
            ItemStack constantItem = createCustomItem(Material.END_CRYSTAL, 1, "§6"+constant.getName(),
                    "§7Symbol§r: §e" + constant.getSymbol(),
                    "§7Value§r: §e" + constant.getValue() + " " + constant.getScientificNotation(),
                    "§7Unit§r: §e" + constant.getMeasureUnit());
            physInventoryMap.get(1+(v / (7*4))).setItem(9 + (v % ((7*4) - 1)), constantItem);
            this.physConstantsItems.put(constant, constantItem);
        }

        mathInventoryMap.values().forEach(this::addNavigateButtons);
        physInventoryMap.values().forEach(this::addNavigateButtons);
    }

    /**
     * This function opens the main Inventory necessary for the /constants command.
     * @param player The player opening the inventory.
     */
    private void openConstantsMenu(Player player) {
        createMainInv();
        createMathAndPhysInv();
        player.openInventory(this.mainConstantsInv);
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
