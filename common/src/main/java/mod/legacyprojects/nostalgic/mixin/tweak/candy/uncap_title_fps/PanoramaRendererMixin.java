package mod.legacyprojects.nostalgic.mixin.tweak.candy.uncap_title_fps;

import mod.legacyprojects.nostalgic.tweak.config.CandyTweak;
import mod.legacyprojects.nostalgic.util.client.timer.PartialTick;
import net.minecraft.client.renderer.PanoramaRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PanoramaRenderer.class)
public abstract class PanoramaRendererMixin
{
    /**
     * Modifies the partial tick used by the panorama to ensure that it uses realtime change between two ticks.
     */
    @ModifyVariable(
        index = 5,
        argsOnly = true,
        method = "render",
        at = @At("HEAD")
    )
    private float nt_title_screen$modifyPanoramaPartialTick(float partialTick)
    {
        return CandyTweak.UNCAP_TITLE_FPS.get() ? PartialTick.realtime() : partialTick;
    }
}
