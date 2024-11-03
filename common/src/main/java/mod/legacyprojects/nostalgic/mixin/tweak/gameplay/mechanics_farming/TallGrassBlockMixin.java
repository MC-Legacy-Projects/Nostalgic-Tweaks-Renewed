package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_farming;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TallGrassBlock.class)
public class TallGrassBlockMixin {

    @Inject(method = "isValidBonemealTarget", at = @At("RETURN"), cancellable = true)
    private void disableTallGrassFromBonemealingShortGrass(LevelReader level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (GameplayTweak.DISABLE_BONEMEAL_SHORT_GRASS.get()) {
            cir.setReturnValue(false);
        }
    }
}
