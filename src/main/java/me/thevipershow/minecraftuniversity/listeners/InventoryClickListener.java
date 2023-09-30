package me.thevipershow.minecraftuniversity.listeners;

import me.thevipershow.minecraftuniversity.commands.ConstantsCommand;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class InventoryClickListener implements Listener {

    private final ConstantsCommand constantsCommand;

    public InventoryClickListener(ConstantsCommand constantsCommand) {
        this.constantsCommand = constantsCommand;
    }

    private static void aaa(Map<Integer, Inventory> map, Player p, Inventory clicked, boolean back) {
        for (final Map.Entry<Integer, Inventory> entry : map.entrySet()) {
            if (entry.getValue() == clicked) {
                int currentPage = entry.getKey();
                if ((back && currentPage <= 1) || (!back && currentPage == map.keySet().size())) return;
                p.closeInventory();
                p.openInventory(map.get(currentPage - (back ? 1 : (-1))));
            }
        }
    }

    private void nav(Player player, Inventory clickedInv, boolean back) {
        aaa(constantsCommand.getMathInventoryMap(), player, clickedInv, back);
        aaa(constantsCommand.getPhysInventoryMap(), player, clickedInv, back);
    }

    private static void interactSound(Player humanoid) {
        humanoid.playSound(humanoid.getLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 3f, 3f);
    }

    private static void denySound(Player humanoid) {
        humanoid.playSound(humanoid.getLocation(), Sound.BLOCK_ANVIL_BREAK, 3f, 3f);
    }

    private static void studySound(Player humanoid) {
        humanoid.playSound(humanoid.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2f, 2f);
    }

    @EventHandler
    public void onEvent(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        Player humanoid = (Player) event.getWhoClicked();
        boolean cancel = false;
        if (currentItem == null) {
            return;
        } else if (currentItem.equals(constantsCommand.getBackItem())) {
            nav(humanoid, event.getClickedInventory(), true);
            cancel = true;
            interactSound(humanoid);
        } else if (currentItem.equals(constantsCommand.getNextItem())) {
            humanoid.closeInventory();
            nav(humanoid, event.getClickedInventory(), false);
            interactSound(humanoid);
            cancel = true;
        } else if (currentItem.equals(constantsCommand.getMainMenuItem())) {
            humanoid.closeInventory();
            interactSound(humanoid);
            humanoid.openInventory(constantsCommand.getMainConstantsInv());
            cancel = true;
        } else if (currentItem.equals(constantsCommand.getMathSectionItem())) {
            humanoid.closeInventory();
            interactSound(humanoid);
            humanoid.openInventory(constantsCommand.getMathInventoryMap().get(1));
            cancel = true;
        } else if (currentItem.equals(constantsCommand.getPhysicsSectionItem())) {
            humanoid.closeInventory();
            interactSound(humanoid);
            humanoid.openInventory(constantsCommand.getPhysInventoryMap().get(1));
            cancel = true;
        } else if (currentItem.equals(constantsCommand.getGlassPanelBg())) {
            denySound(humanoid);
            cancel = true;
        } else if (constantsCommand.getMathConstantsItems().values().stream().anyMatch(stack->stack.equals(currentItem))) {
            studySound(humanoid);
            cancel=true;
        } else if (constantsCommand.getPhysConstantsItems().values().stream().anyMatch(stack->stack.equals(currentItem))) {
            studySound(humanoid);
            cancel=true;
        }

        event.setCancelled(cancel);
    }
}
