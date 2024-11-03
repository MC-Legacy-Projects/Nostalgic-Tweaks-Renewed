package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_tools;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void removeAxeStripping(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (GameplayTweak.DISABLE_AXE_STRIPPING.get()) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
