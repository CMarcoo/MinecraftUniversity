package me.thevipershow.minecraftuniversity.listeners;

import me.thevipershow.minecraftuniversity.commands.StudyCommand;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This class is responsible to handle the player clicking a block within study mode.
 */
public class BlockClickListener implements Listener {

    private final StudyCommand studyCommand;

    /**
     * Main constructor for the BlockClickListener.
     * @param studyCommand the study command instance.
     */
    public BlockClickListener(StudyCommand studyCommand) {
        this.studyCommand = studyCommand;
    }

    /**
     * Opens studying menu for the clicked block.
     * @param player The Player who clicked.
     * @param block The Block clicked.
     */
    private void studyBlock(Player player, Block block) {

    }

    /**
     * Called and handled only when the player clicks a block.
     * @param event PlayerInteractEvent.
     */
    @EventHandler
    public void onEvent(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (studyCommand.getInspectionPlayers().contains(playerUUID)) {
            Block clickedBlock = event.getClickedBlock();
            boolean clickedAir = clickedBlock == null;
            if (!clickedAir) {
                studyBlock(player, clickedBlock);
                event.setCancelled(true);
            }
        }
    }
}
