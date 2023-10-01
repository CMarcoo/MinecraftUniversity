package me.thevipershow.minecraftuniversity.studyblocks;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract extension of AbstractStudyBlockHandler which implements SpaceShapeRenderer.
 * Classes extend this should rigorously have one or more shape to render for the client.
 */
public abstract class AbstractRenderStudyBlockHandler extends AbstractStudyBlockHandler implements SpaceShapeRender{

    /**
     * Main constructor of this abstract class.
     *
     * @param blockMaterial The material that represents this class.
     */
    protected AbstractRenderStudyBlockHandler(@NotNull Material blockMaterial) {
        super(blockMaterial);
    }
}
