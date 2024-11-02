package mod.legacyprojects.nostalgic.util.common.world;

import dev.architectury.utils.EnvExecutor;
import mod.legacyprojects.nostalgic.util.client.GameUtil;
import mod.legacyprojects.nostalgic.util.common.annotation.PublicAPI;
import mod.legacyprojects.nostalgic.util.server.ServerUtil;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class LevelUtil
{
    /**
     * Get a common {@link Level} instance for the overworld.
     *
     * @return A {@link Level} instance, if it is available.
     */
    @Nullable
    @PublicAPI
    public static Level getOverworld()
    {
        return EnvExecutor.getEnvSpecific(() -> GameUtil::getOverworldLevel, () -> ServerUtil::getOverworldLevel);
    }
}
