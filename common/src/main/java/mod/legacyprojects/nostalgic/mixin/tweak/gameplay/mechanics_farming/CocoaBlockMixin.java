package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_farming;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CocoaBlock.class)
public abstract class CocoaBlockMixin
{
    /**
     * Immediately grows a cocoa block when using a bonemeal item.
     */
    @ModifyArg(
        method = "performBonemeal",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"
        )
    )
    private BlockState nt_mechanics_farming$modifyCocoaBlockAge(BlockState blockState)
    {
        return GameplayTweak.INSTANT_BONEMEAL.get() ? blockState.setValue(CocoaBlock.AGE, CocoaBlock.MAX_AGE) : blockState;
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void disableCocoaBeanPlacement(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (GameplayTweak.DISABLE_COCOA_BEAN_PLACEMENT.get()) {
            cir.setReturnValue(false);
        }
    }
}
