package mod.legacyprojects.nostalgic.helper.animation;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;

/**
 * This utility class is used by both the client and server.
 */
public abstract class AnimationConstant
{
    /**
     * The old sneak eye height value.
     */
    public static final float SNEAK_EYE_HEIGHT = GameplayTweak.DISABLE_SNEAKING_UNDER_SLABS.get() ? 1.55F : 1.41F;
}
