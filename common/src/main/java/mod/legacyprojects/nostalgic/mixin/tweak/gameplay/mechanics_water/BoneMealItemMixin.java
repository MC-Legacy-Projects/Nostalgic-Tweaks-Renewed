package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_water;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {

    @Inject(method = "growWaterPlant", at = @At("HEAD"), cancellable = true)
    private static void disableWaterBonemeal(ItemStack stack, Level level, BlockPos pos, Direction clickedSide, CallbackInfoReturnable<Boolean> cir) {
        if (GameplayTweak.DISABLE_WATER_BONEMEAL.get()) {
            cir.setReturnValue(false);
        }
    }
}
