package mod.adrenix.nostalgic.client.config;

import com.mojang.blaze3d.platform.InputConstants;
import mod.adrenix.nostalgic.util.NostalgicLang;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public abstract class ClientKeyMapping
{
    public static KeyMapping getConfigKey()
    {
        return new KeyMapping(
            NostalgicLang.Key.OPEN_CONFIG,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            NostalgicLang.Key.CATEGORY_NAME
        );
    }

    public static KeyMapping getFogKey()
    {
        return new KeyMapping(
            NostalgicLang.Key.TOGGLE_FOG,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            NostalgicLang.Key.CATEGORY_NAME
        );
    }
}
