package mod.legacyprojects.nostalgic.tweak;

import mod.legacyprojects.nostalgic.tweak.config.CandyTweak;

public abstract class TweakCondition
{
    /**
     * @return A truthful value if items are being rendered in 2D.
     */
    public static boolean areItemsFlat()
    {
        return CandyTweak.OLD_2D_ITEMS.fromDisk();
    }
}
