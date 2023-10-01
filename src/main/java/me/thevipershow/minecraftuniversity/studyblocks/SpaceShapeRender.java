package me.thevipershow.minecraftuniversity.studyblocks;

import org.bukkit.Location;

/**
 * This interface should be implemented to all of those classes that handle minecraft 3d shape rendering.
 */
public interface SpaceShapeRender {

    /**
     * Renders in minecraft a 3D space.
     * @param referenceLocation the location used as position reference.
     */
    void renderSpaceShape(Location referenceLocation);
}
