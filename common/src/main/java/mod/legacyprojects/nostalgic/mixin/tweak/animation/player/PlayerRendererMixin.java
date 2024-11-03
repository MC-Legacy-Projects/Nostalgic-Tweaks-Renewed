package mod.legacyprojects.nostalgic.mixin.tweak.animation.player;

import mod.legacyprojects.nostalgic.tweak.config.AnimationTweak;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "getRenderOffset(Lnet/minecraft/client/player/AbstractClientPlayer;F)Lnet/minecraft/world/phys/Vec3;", at = @At("RETURN"), cancellable = true)
    public void getRenderOffset(AbstractClientPlayer abstractClientPlayer, float f, CallbackInfoReturnable<Vec3> cir) {
        if (AnimationTweak.OLD_SNEAKING.get()) {
            cir.setReturnValue(abstractClientPlayer.isCrouching() ? new Vec3(0.0, 0.125, 0.0) : super.getRenderOffset(abstractClientPlayer, f));
        }
    }
}
