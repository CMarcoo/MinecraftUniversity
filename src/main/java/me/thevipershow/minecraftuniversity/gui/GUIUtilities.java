package me.thevipershow.minecraftuniversity.gui;

import me.thevipershow.minecraftuniversity.MinecraftUniversity;
import me.thevipershow.minecraftuniversity.constants.ItemConversible;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GUIUtilities {
    public static final List<Integer> GLASS_SLOTS_INV_3x9 = Arrays.asList(0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26);
    public final static byte INV_COLUMNS = 9;
    public final static byte MAIN_INV_ROWS = 3;
    public final static byte CONSTANTS_INV_SIZE = INV_COLUMNS * MAIN_INV_ROWS;
    public final static byte MAIN_INV_SIZE = INV_COLUMNS*MAIN_INV_ROWS;
    public static final Inventory MAIN_CONSTANTS_INV = Bukkit.createInventory(null, MAIN_INV_SIZE, MinecraftUniversity.PREFIX + "§9Constants Menu");
    public final static byte MATH_POS = 11;
    public final static byte PHYS_POS = 13;
    public final static byte CONSTANTS_ITEMS_PER_INV = 7 * 4;
    public static final ItemStack BACK_ITEM = createCustomItem(Material.RED_TERRACOTTA, 1, "§7[§cPrevious Page§7]", "§7Return to previous page");
    public static final ItemStack NEXT_ITEM = createCustomItem(Material.GREEN_TERRACOTTA, 1, "§7[§aNext Page§7]", "§7Move to next page");
    public static final ItemStack MAIN_MENU_ITEM = createCustomItem(Material.GRAY_TERRACOTTA, 1, "§7[§fMain Menu§7]", "§7Return to the main menu");
    public static final ItemStack MATH_SECTION_ITEM = createCustomItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, "§eMathematics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of mathematics.");
    public static final ItemStack PHYS_SECTION_ITEM = createCustomItem(Material.ORANGE_GLAZED_TERRACOTTA, 1, "§bPhysics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of physics.");
    public static final ItemStack GLASS_PANEL_BG = createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, " ");

    /**
     * Fill a collection of integer positions with the same given ItemStack.
     * @param positions The slots to fill.
     * @param inventory The target Inventory.
     * @param item The item that will be filling the slots.
     */
    public static void fillInventoryRepeatedItem(@NotNull Collection<Integer> positions, @NotNull Inventory inventory, @NotNull ItemStack item) {
        positions.forEach(pos -> inventory.setItem(pos, item));
    }

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
     * Adds navigations buttons in the bottom center position of an Inventory.
     * @param inv The Inventory to modify.
     * @param nextPageItem The ItemStack to add as next page nav button.
     * @param backPageItem The ItemStack to add as previous page nav button.
     * @param mainMenuItem The ItemStack to add as mainMenu page nav button.
     */
    public static void addCenteredBottomNavButtons(@NotNull Inventory inv, ItemStack nextPageItem, ItemStack backPageItem, ItemStack mainMenuItem) {
        int invSize = inv.getSize();
        inv.setItem(invSize - 2, nextPageItem);
        inv.setItem(invSize - 8, backPageItem);
        inv.setItem(invSize - 5, mainMenuItem);
    }

    /**
     * Fills a generic inventory of Constant Type
     * @param inventoryMap the inventory map
     * @param genericMap the generic map
     * @param t a generic type array.
     * @param <T> must implement ItemConversible
     */
    @SafeVarargs
    public static <T extends ItemConversible> void fillConstantsStyleInventoryGeneric(@NotNull Map<Integer, Inventory> inventoryMap, @NotNull Map<T, ItemStack> genericMap , @NotNull T... t) {
        for (int i = 0; i <= t.length / (CONSTANTS_ITEMS_PER_INV); i++) {
            inventoryMap.put(i+1, Bukkit.createInventory(null, INV_COLUMNS*6, MinecraftUniversity.PREFIX + "§9Physics Page " + (1+i)));
        }

        for (int v = 0; v < t.length; v++) {
            T constant = t[v];
            ItemStack constantItem = constant.convertToItem();
            if (constantItem == null) continue;
            Inventory tempInv = inventoryMap.get( 1 + (v / (CONSTANTS_ITEMS_PER_INV)));
            tempInv.setItem(9 + (v % ((CONSTANTS_ITEMS_PER_INV) - 1)), constantItem);
            GUIUtilities.addCenteredBottomNavButtons(tempInv, GUIUtilities.NEXT_ITEM, GUIUtilities.BACK_ITEM, GUIUtilities.MAIN_MENU_ITEM);
            genericMap.put(constant, constantItem);
        }
    }
}
