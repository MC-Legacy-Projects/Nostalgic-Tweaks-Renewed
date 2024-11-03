package mod.legacyprojects.nostalgic.mixin.tweak.candy.world_sky;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mod.legacyprojects.nostalgic.tweak.config.CandyTweak;
import mod.legacyprojects.nostalgic.tweak.enums.SunsetSunriseType;
import mod.legacyprojects.nostalgic.util.client.GameUtil;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(DimensionSpecialEffects.class)
public abstract class DimensionSpecialEffectsMixin
{

    @Unique
    private final float[] sunriseCol = new float[4];
    /**
     * Changes the overworld dimension's cloud height.
     */
    @ModifyReturnValue(
        method = "getCloudHeight",
        at = @At("RETURN")
    )
    private float nt_world_sky$setCloudHeight(float cloudHeight)
    {
        int customHeight = CandyTweak.OLD_CLOUD_HEIGHT.get();
        boolean isCustomHeight = customHeight != CandyTweak.OLD_CLOUD_HEIGHT.getDisabled();

        return GameUtil.isInOverworld() && isCustomHeight ? (float) customHeight : cloudHeight;
    }

    @Inject(method = "getSunriseColor", at = @At("HEAD"), cancellable = true)
    private void changeSunsetSunriseColor(float timeOfDay, float partialTicks, CallbackInfoReturnable<float[]> cir) {
        if (CandyTweak.SUNSET_SUNRISE_TYPE.get() != SunsetSunriseType.JAVA) {
            Color base_color = new Color(63, 43, 51);
            float red = base_color.getRed() / 255f;
            float green = base_color.getGreen() / 255f;
            float blue = base_color.getBlue() / 255f;
            float alpha = base_color.getAlpha() / 255f;
            float h = 0.4F;
            float i = Mth.cos(timeOfDay * 6.2831855F) - 0.0F;
            float j = -0.0F;
            if (i >= -0.2F && i <= 0.4F) {
                float k = (i - -0.0F) / (i <= 0.0F ? 0.2F : 0.4F) * 0.5F + 0.5F;
                float l = 1.0F - (1.0F - Mth.sin(k * 3.1415927F)) * 0.99F;
                l *= l;
                this.sunriseCol[0] = k * red + red;
                this.sunriseCol[1] = k * k * red + 0.2F;
                this.sunriseCol[2] = k * k * blue + 0.2F;
                this.sunriseCol[3] = l;
                cir.setReturnValue(this.sunriseCol);
            } else {
                cir.setReturnValue(null);
            }
        } else {
            float f = 0.4F;
            float g = Mth.cos(timeOfDay * 6.2831855F) - 0.0F;
            float h = -0.0F;
            if (g >= -0.4F && g <= 0.4F) {
                float i = (g - -0.0F) / 0.4F * 0.5F + 0.5F;
                float j = 1.0F - (1.0F - Mth.sin(i * 3.1415927F)) * 0.99F;
                j *= j;
                this.sunriseCol[0] = i * 0.3F + 0.7F;
                this.sunriseCol[1] = i * i * 0.7F + 0.2F;
                this.sunriseCol[2] = i * i * 0.0F + 0.2F;
                this.sunriseCol[3] = j;
                cir.setReturnValue(this.sunriseCol);
            } else {
                cir.setReturnValue(null);
            }
        }
        cir.cancel();
    }
}
