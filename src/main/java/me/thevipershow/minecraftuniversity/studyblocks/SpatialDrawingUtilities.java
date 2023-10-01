package me.thevipershow.minecraftuniversity.studyblocks;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Utilities class for spatial representation methods
 */
public final class SpatialDrawingUtilities {


    /**
     * Calculate the points following a line between two points in space
     * @param A the first location
     * @param B the second location
     * @param points amount of points between AB
     * @return a collection of points
     */
    public static List<Location> generateLineAB(Location A, Location B, short points) {
        Vector aVec = A.toVector();
        Vector bVec = B.toVector();
        World world = A.getWorld();
        List<Location> locations = new ArrayList<>();
        Vector differenceVec = bVec.clone().subtract(aVec);
        for (short i = 1; i < points; i++) {
            final Location temp = aVec.clone().add(differenceVec.clone().multiply((double) i / points)).toLocation(world);
            locations.add(temp);
        }
        return locations;
    }

    /**
     * Draw a line between two points in space.
     * @param p the player who will see this line
     * @param a point A
     * @param b point B
     * @param particle the particle chosen
     * @param points amount of points in AB
     */
    public static void drawLineABPlayer(@NotNull Player p, @NotNull Location a, @NotNull Location b, @NotNull Particle particle, short points) {
        for (final Location location : generateLineAB(a, b, points)) {
            p.spawnParticle(particle, location, 0,0,0,0);
        }
    }

    /**
     * Draw a line between two points in space.
     * @param a point A
     * @param b point B
     * @param particle the particle chosen
     * @param points amount of points in AB
     */
    public static void drawLineABWorld(@NotNull Location a, @NotNull Location b, @NotNull Particle particle, short points) {
        World world = a.getWorld();
        for (final Location location : generateLineAB(a, b, points)) {
            world.spawnParticle(particle, location,0,0,0,0);
        }
    }

    public static void aaa(Plugin plugin, Server server) {
        World mainWorld = server.getWorlds().get(0);
        short points = 0xF;
        final Location A = new Location(mainWorld, 0, 80, 0);
        server.getScheduler().runTaskTimer(plugin, () -> {
            for(int theta = 0; theta <= 360; theta+=5) {
                final Location B = new Location(mainWorld, 10*Math.cos(Math.toRadians(theta)), 80, 10*Math.sin(Math.toRadians(theta)));
                SpatialDrawingUtilities.drawLineABWorld(A, B, Particle.END_ROD, points);
            }
        }, 20L, 5L);
    }
}
