package me.thevipershow.minecraftuniversity.studyblocks;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * An abstract class of a block handler class.
 * Each Block Handler implementation should represent a unique block
 * that will be able to have information in a GUI, a representative Material
 * and a list of actions to run upon encountering an ItemStack (which is
 * stored rigorously only inside the Inventory).
 */
@Getter
public abstract class AbstractStudyBlockHandler {

    protected final Material blockMaterial;
    protected final Inventory studyBlockGUI;
    protected final Map<ItemStack, Consumer<ItemStack>> clickActionMap = new HashMap<>();

    /**
     * Main constructor of this abstract class.
     * @param blockMaterial The material that represents this class.
     */
    protected AbstractStudyBlockHandler(@NotNull Material blockMaterial) {
        this.blockMaterial = blockMaterial;
        this.studyBlockGUI = generateInventory();
    }

    /**
     * Generate the main inventory corresponding to the specific implementation of this abstract class.
     * @return the generated inventory.
     */
    @NotNull
    protected abstract Inventory generateInventory();

    /**
     * Open the main GUI to the player.
     * @param player the Player.
     */
    public abstract void openBlockStudyGUI(Player player);

    /**
     * Create and assign the click action map values.
     */
    public abstract void assignClickActionMap();
}
