package mod.adrenix.nostalgic.tweak;

import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import mod.adrenix.nostalgic.util.ModTracker;

public abstract class TweakCondition
{
    /**
     * @return A truthful value when Sodium is <b>not</b> installed.
     */
    public static boolean isSodiumAbsent()
    {
        return !ModTracker.SODIUM.isInstalled();
    }

    /**
     * @return A truthful value if items are being rendered in 2D.
     */
    public static boolean areItemsFlat()
    {
        return CandyTweak.OLD_2D_ITEMS.fromDisk();
    }
}
