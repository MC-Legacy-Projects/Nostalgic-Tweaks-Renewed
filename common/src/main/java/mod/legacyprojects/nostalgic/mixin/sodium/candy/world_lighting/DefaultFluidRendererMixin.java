package mod.legacyprojects.nostalgic.mixin.sodium.candy.world_lighting;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.caffeinemc.mods.sodium.client.model.color.ColorProvider;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadViewMutable;
import net.caffeinemc.mods.sodium.client.model.quad.properties.ModelQuadFacing;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.DefaultFluidRenderer;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import mod.legacyprojects.nostalgic.helper.candy.light.LightingHelper;
import mod.legacyprojects.nostalgic.tweak.config.CandyTweak;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultFluidRenderer.class)
public abstract class DefaultFluidRendererMixin
{
    /* Shadows */

    @Shadow(remap = false) @Final private QuadLightData quadLightData;

    /* Injections */

    /**
     * Helps simulate old water lighting by disabling Sodium's ambient occlusion on water.
     */
    @ModifyExpressionValue(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;useAmbientOcclusion()Z"
        )
    )
    private boolean nt_sodium_world_lighting$modifyWaterAmbientOcclusion(boolean useAmbientOcclusion)
    {
        return CandyTweak.SODIUM_WATER_AO.get() && useAmbientOcclusion;
    }

    /**
     * Recalculates the quad's light data on water blocks to simulate old water rendering.
     */
    @Inject(
        method = "updateQuad",
        at = @At("RETURN")
    )
    private void nt_sodium_world_lighting$modifyWaterLight(ModelQuadViewMutable quad, LevelSlice level, BlockPos pos, LightPipeline lighter, Direction dir, ModelQuadFacing facing, float brightness, ColorProvider<FluidState> colorProvider, FluidState fluidState, CallbackInfo ci)
    {
        if (!CandyTweak.OLD_WATER_LIGHTING.get())
            return;

        int light = LightingHelper.getWaterLight(level, pos);

        for (int i = 0; i < 4; i++)
            this.quadLightData.lm[i] = light;
    }
}
