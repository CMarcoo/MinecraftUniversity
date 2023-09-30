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

public class ConstantsCommand implements CommandExecutor {

    public static final String PREFIX = "§7[§cMCUniversity§7]§r: ";

    private final MinecraftUniversity mcUni;

    public ConstantsCommand(@NotNull MinecraftUniversity mcUni) {
        this.mcUni = mcUni;
    }

    @Getter private Inventory mainConstantsInv;

    private final static int INV_COLUMNS = 9, MAIN_INV_ROWS = 3, MAIN_INV_SIZE = INV_COLUMNS*MAIN_INV_ROWS, MATH_POS = 11,
    PHYS_POS = 13;

    @Getter private ItemStack mathSectionItem = createCustomItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, "§eMathematics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of mathematics.");
    @Getter private ItemStack physicsSectionItem = createCustomItem(Material.ORANGE_GLAZED_TERRACOTTA, 1, "§bPhysics Constants",
            "§7This section will cover all of the constants",  "§7That belong to the world of physics.");
    @Getter private final ItemStack glassPanelBg = createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, " ");

    public static ItemStack createCustomItem(Material material, int amount, String name, String... desc) {
        final ItemStack item = new ItemStack(material, amount <= 0 ? 1 : amount);
        final ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        if (desc.length != 0)
            itemMeta.setLore(Arrays.asList(desc));
        item.setItemMeta(itemMeta);
        return item;
    }

    private void createMainInv() {
        if (mainConstantsInv != null) return;
        mainConstantsInv = Bukkit.createInventory(null, MAIN_INV_SIZE, PREFIX + "§9Constants Menu");

        Arrays.asList(0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26).forEach(v -> mainConstantsInv.setItem(v, glassPanelBg));

        mainConstantsInv.setItem(MATH_POS, mathSectionItem);
        mainConstantsInv.setItem(PHYS_POS, physicsSectionItem);
    }

    @Getter private final Map<Integer, Inventory> mathInventoryMap = new HashMap<>();
    @Getter private final Map<Integer, Inventory> physInventoryMap = new HashMap<>();
    @Getter private final Map<MathematicsConstants, ItemStack> mathConstantsItems = new HashMap<>();
    @Getter private final Map<PhysicsConstants, ItemStack> physConstantsItems = new HashMap<>();

    @Getter private final ItemStack backItem = createCustomItem(Material.RED_TERRACOTTA, 1, "§7[§cPrevious Page§7]", "§7Return to previous page"),
            nextItem = createCustomItem(Material.GREEN_TERRACOTTA, 1, "§7[§aNext Page§7]", "§7Move to next page"),
            mainMenuItem = createCustomItem(Material.GRAY_TERRACOTTA, 1, "§7[§fMain Menu§7]", "§7Return to the main menu");

    private void addNavigateButtons(Inventory inv) {
        inv.setItem(54 - 2, backItem);
        inv.setItem(54 - 8, nextItem);
        inv.setItem(54 - 5, mainMenuItem);
    }

    private void createMathAndPhysInv() {
        if (!mathInventoryMap.isEmpty() && !physInventoryMap.isEmpty()) return;

        for (int i = 0; i <= MathematicsConstants.values().length / (7 * 4); i++) {
            mathInventoryMap.put(i+1, Bukkit.createInventory(null, INV_COLUMNS*6, PREFIX + "§9Math Page " + (1+i)));
        }
        final MathematicsConstants[] values = MathematicsConstants.values();
        for (int v = 0; v < values.length; v++) {
            MathematicsConstants constant = values[v];
            ItemStack constantItem = createCustomItem(Material.END_CRYSTAL, 1, "§6" + constant.getName(),
                    "§7Symbol§r: §e" + constant.getSymbol(),
                    "§7Value§r: §e" + constant.getValue());
            mathInventoryMap.get(1+(v / (7*4))).setItem(9 + v % (7*4), constantItem);
            this.mathConstantsItems.put(constant, constantItem);
        }

        for (int i = 0; i <= PhysicsConstants.values().length / (7 * 4); i++) {
            physInventoryMap.put(i+1, Bukkit.createInventory(null, INV_COLUMNS*6, PREFIX + "§9Physics Page " + (1+i)));
        }

        final PhysicsConstants[] pValues = PhysicsConstants.values();
        for (int v = 0; v < pValues.length; v++) {
            PhysicsConstants constant = pValues[v];
            ItemStack constantItem = createCustomItem(Material.END_CRYSTAL, 1, "§6"+constant.getName(),
                    "§7Symbol§r: §e" + constant.getSymbol(),
                    "§7Value§r: §e" + constant.getValue() + " " + constant.getScientificNotation(),
                    "§7Unit§r: §e" + constant.getMeasureUnit());
            physInventoryMap.get(1+(v / (7*4))).setItem(9 + v % (7*4), constantItem);
            this.physConstantsItems.put(constant, constantItem);
        }

        mathInventoryMap.values().forEach(this::addNavigateButtons);
        physInventoryMap.values().forEach(this::addNavigateButtons);
    }

    private void openConstantsMenu(Player player) {
        createMainInv();
        createMathAndPhysInv();
        player.openInventory(this.mainConstantsInv);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage(PREFIX + "§4You cannot execute this command from a terminal!");
        } else {
            if (!player.hasPermission("mcuni.constants")) {
                player.sendMessage(PREFIX + "§4You do not have the necessary permission to view constants!");
                return false;
            }
            openConstantsMenu(player);
        }
        return false;
    }
}
