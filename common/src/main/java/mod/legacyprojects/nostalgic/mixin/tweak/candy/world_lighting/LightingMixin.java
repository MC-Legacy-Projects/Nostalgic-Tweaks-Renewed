package mod.legacyprojects.nostalgic.mixin.tweak.candy.world_lighting;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import mod.legacyprojects.nostalgic.tweak.config.CandyTweak;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Lighting.class)
public abstract class LightingMixin
{
    /* Shadows */

    @Shadow @Final private static Vector3f DIFFUSE_LIGHT_0;
    @Shadow @Final private static Vector3f DIFFUSE_LIGHT_1;

    /* Injections */

    /**
     * Modifies the diffuse lighting in the nether.
     */
    @WrapWithCondition(
        method = "setupNetherLevel",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;setupLevelDiffuseLighting(Lorg/joml/Vector3f;Lorg/joml/Vector3f;)V"
        ),
            remap = false
    )
    private static boolean nt_world_lighting$modifyNetherDiffuseLighting(Vector3f dir0, Vector3f dir1)
    {
        if (CandyTweak.OLD_NETHER_LIGHTING.get())
        {
            RenderSystem.setupLevelDiffuseLighting(DIFFUSE_LIGHT_0, DIFFUSE_LIGHT_1);
            return false;
        }

        return true;
    }
}
