package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_fire;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulFireBlock.class)
public class SoulFireBlockMixin {

    @Inject(method = "canSurviveOnBlock", at = @At("HEAD"), cancellable = true)
    private static void removeSoulFire(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (GameplayTweak.DISABLE_SOUL_FIRE.get()) {
            cir.setReturnValue(false);
        }
    }
}
