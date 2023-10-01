package me.thevipershow.minecraftuniversity.studyblocks.implementations;

import me.thevipershow.minecraftuniversity.MinecraftUniversity;
import me.thevipershow.minecraftuniversity.studyblocks.AbstractRenderStudyBlockHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class IceBlockImplementation extends AbstractRenderStudyBlockHandler {
    /**
     * Main constructor of this abstract class.
     */
    protected IceBlockImplementation() {
        super(Material.ICE);
    }

    /**
     * Generate the main inventory corresponding to the specific implementation of this abstract class.
     *
     * @return the generated inventory.
     */
    @Override
    protected @NotNull Inventory generateInventory() {
        Inventory iceInventory = Bukkit.createInventory(null, 9*3, MinecraftUniversity.PREFIX + "§bICE");

        return iceInventory;
    }

    /**
     * Open the main GUI to the player.
     *
     * @param player the Player.
     */
    @Override
    public void openBlockStudyGUI(Player player) {

    }

    /**
     * Create and assign the click action map values.
     */
    @Override
    public void assignClickActionMap() {

    }

    /**
     * Renders in minecraft a 3D space.
     *
     * @param referenceLocation the location used as position reference.
     */
    @Override
    public void renderSpaceShape(Location referenceLocation) {

    }
}
