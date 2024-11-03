package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_tools;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShovelItem.class)
public class ShovelItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void removeShovelPathing(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (GameplayTweak.DISABLE_SHOVEL_PATHING.get()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
