package me.thevipershow.minecraftuniversity.constants;

import org.bukkit.inventory.ItemStack;

/**
 * To be implemented in objects that can be represented as Bukkit's ItemStacks.
 */
public interface ItemConversible {

    /**
     * Generate an Item from this class.
     * @return The new Item.
     */
    ItemStack convertToItem();
}
