package me.thevipershow.minecraftuniversity.listeners;

import me.thevipershow.minecraftuniversity.commands.ConstantsCommand;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import static me.thevipershow.minecraftuniversity.gui.GUIUtilities.*;

import java.util.Map;

/**
 * This class is responsible for all the logic that allows the player to navigate user interfaces in this plugin.
 */
public class InventoryClickListener implements Listener {

    private final ConstantsCommand constantsCommand;

    /**
     * Main constructor for the InventoryClickListener class.
     * @param constantsCommand The ConstantsCommand class.
     */
    public InventoryClickListener(@NotNull ConstantsCommand constantsCommand) {
        this.constantsCommand = constantsCommand;
    }

    /**
     * This function is responsible for the logic that lets a user go forward or backward in the pages of a gui.
     * @param map The inventory map, with page number as key and corresponding inventory as value.
     * @param p The player using gui.
     * @param clicked The inventory the player is currently clicking from.
     * @param back true if the player is going back, false if going forward.
     * @return true if player changed inventory.
     */
    private static boolean pageSwitchLogic(@NotNull Map<Integer, Inventory> map, @NotNull Player p, @NotNull Inventory clicked, boolean back) {
        boolean success = false;
        for (final Map.Entry<Integer, Inventory> entry : map.entrySet()) {
            if (entry.getValue() == clicked) {
                int currentPage = entry.getKey();
                if ((back && currentPage <= 1) || (!back && currentPage == map.keySet().size())) return false;
                p.closeInventory();
                p.openInventory(map.get(currentPage - (back ? 1 : (-1))));
                success = true;
                interactSound(p);
                break;
            }
        }
        return success;
    }

    /**
     * General function that performs pageSwitchLogic for all types of inventories maps.
     * @param player The player who clicked.
     * @param clickedInv The clicked Inventory.
     * @param back true if the player is going back, false if going forward.
     */
    private void pageNavigate(@NotNull Player player, @NotNull Inventory clickedInv, boolean back) {
        boolean navigateSuccess = pageSwitchLogic(constantsCommand.getMathInventoryMap(), player, clickedInv, back);
        if (!navigateSuccess)
            navigateSuccess = pageSwitchLogic(constantsCommand.getPhysInventoryMap(), player, clickedInv, back);
        if (!navigateSuccess)
            denySound(player);
    }

    /**
     * Send a default interaction sounds for guis.
     * @param humanoid The player.
     */
    private static void interactSound(@NotNull Player humanoid) {
        humanoid.playSound(humanoid.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 3f, 3f);
    }

    /**
     * Send a error sounds for guis.
     * @param humanoid The player.
     */
    private static void denySound(@NotNull Player humanoid) {
        humanoid.playSound(humanoid.getLocation(), Sound.BLOCK_ANVIL_BREAK, 3f, 3f);
    }

    /**
     * Send a study interaction sounds for guis.
     * @param humanoid The player.
     */
    private static void studySound(@NotNull Player humanoid) {
        humanoid.playSound(humanoid.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2f, 2f);
    }

    /**
     * Called when player clicks an item inside an Inventory.
     * @param event InventoryClickEvent.
     */
    @EventHandler
    public void onEvent(@NotNull InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        boolean cancel = true;

        if (currentItem == null || inv == null) {
            return;
        } else if (currentItem.equals(BACK_ITEM)) {
            pageNavigate(p, inv, true);
            interactSound(p);
        } else if (currentItem.equals(NEXT_ITEM)) {
            p.closeInventory();
            pageNavigate(p, inv, false);
            interactSound(p);
        } else if (currentItem.equals(MAIN_MENU_ITEM)) {
            p.closeInventory();
            interactSound(p);
            p.openInventory(MAIN_CONSTANTS_INV);
        } else if (currentItem.equals(MATH_SECTION_ITEM)) {
            p.closeInventory();
            interactSound(p);
            p.openInventory(constantsCommand.getMathInventoryMap().get(1));
        } else if (currentItem.equals(PHYS_SECTION_ITEM)) {
            p.closeInventory();
            interactSound(p);
            p.openInventory(constantsCommand.getPhysInventoryMap().get(1));
        } else if (currentItem.equals(GLASS_PANEL_BG)) {
            denySound(p);
        } else if (constantsCommand.getMathConstantsItems().values().stream().anyMatch(stack->stack.equals(currentItem))) {
            studySound(p);
        } else if (constantsCommand.getPhysConstantsItems().values().stream().anyMatch(stack->stack.equals(currentItem))) {
            studySound(p);
        } else {
            cancel=false;
        }

        event.setCancelled(cancel);
    }
}
