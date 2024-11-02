package mod.legacyprojects.nostalgic.tweak;

import mod.legacyprojects.nostalgic.util.common.lang.Translation;
import mod.legacyprojects.nostalgic.util.common.asset.TextureIcon;
import net.minecraft.ChatFormatting;

public interface StatusContext
{
    /**
     * @return A {@link ChatFormatting} value that represents the status context.
     */
    ChatFormatting getColor();

    /**
     * @return A {@link Translation} instance that represents the title of this status context.
     */
    Translation getTitle();

    /**
     * @return A {@link Translation} instance that provides information about this status context.
     */
    Translation getInfo();

    /**
     * @return A {@link TextureIcon} that represents this status context.
     */
    TextureIcon getIcon();

    /**
     * Get an icon that represents this status context based on the given flashing flag.
     *
     * @param isFlashing If {@code true}, returns an icon that shows a traffic signal that is off. Otherwise, the status
     *                   context's colored traffic signal icon is returned.
     * @return A {@link TextureIcon} that represents this status context.
     */
    TextureIcon getIcon(boolean isFlashing);
}
