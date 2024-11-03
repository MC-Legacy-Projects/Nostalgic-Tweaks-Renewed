package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mob_ai;

import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {

    @Inject(method = "spawnGolemInWorld", at = @At("HEAD"), cancellable = true)
    private static void removeGolemCreation(CallbackInfo ci) {
        if (GameplayTweak.DISABLE_GOLEM_CREATION.get()) {
            ci.cancel();
        }
    }
}
