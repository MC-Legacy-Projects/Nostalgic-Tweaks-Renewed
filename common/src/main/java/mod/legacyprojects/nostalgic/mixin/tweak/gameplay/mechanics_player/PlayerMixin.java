package mod.legacyprojects.nostalgic.mixin.tweak.gameplay.mechanics_player;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    @Shadow @Final private Abilities abilities;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyReturnValue(
            method = "getDefaultDimensions",
            at = @At("RETURN")
    )
    private EntityDimensions nt_player_animation$modifyStandingEyeHeight(EntityDimensions dimensions, Pose pose)
    {
        if (NostalgicTweaks.isServer() || Pose.CROUCHING != pose)
            return dimensions;

        if (GameplayTweak.DISABLE_SNEAKING_UNDER_SLABS.get())
            return EntityDimensions.scalable(0.6F, 1.8F);

        return dimensions;
    }

    @Shadow
    protected boolean canPlayerFitWithinBlocksAndEntitiesWhen(Pose pose) {
        return this.level().noCollision(this, this.getDimensions(pose).makeBoundingBox(this.position()).deflate(1.0E-7));
    }

    @Inject(method = "updatePlayerPose", at = @At("HEAD"), cancellable = true)
    private void changeDefaultPoses(CallbackInfo ci) {
        if (this.canPlayerFitWithinBlocksAndEntitiesWhen(Pose.SWIMMING)) {
            Pose pose;
            if (this.isFallFlying()) {
                pose = Pose.FALL_FLYING;
            } else if (this.isSleeping()) {
                pose = Pose.SLEEPING;
            } else if (this.isSwimming()) {
                pose = Pose.SWIMMING;
            } else if (this.isAutoSpinAttack()) {
                pose = Pose.SPIN_ATTACK;
            } else if (this.isShiftKeyDown() && !this.abilities.flying) {
                pose = Pose.CROUCHING;
            } else {
                pose = Pose.STANDING;
            }

            Pose pose2;
            if (!this.isSpectator() && !this.isPassenger() && !this.canPlayerFitWithinBlocksAndEntitiesWhen(pose)) {
                if (this.canPlayerFitWithinBlocksAndEntitiesWhen(Pose.CROUCHING)) {
                    pose2 = Pose.CROUCHING;
                } else if (GameplayTweak.DISABLE_CRAWLING.get()) {
                    pose2 = Pose.STANDING;
                } else {
                    pose2 = Pose.SWIMMING;
                }
            } else {
                pose2 = pose;
            }

            this.setPose(pose2);
        }
        ci.cancel();
    }

//    @ModifyVariable(method = "updatePlayerPose", at = @At("STORE"), ordinal = 0)
//    private Pose changePoseOneWhenCrawlingDisabled(Pose pose) {
//        if (this.isSwimming() && GameplayTweak.DISABLE_CRAWLING.get()) {
//            return Pose.STANDING;
//        }
//        return pose;
//    }
//
//    @ModifyVariable(method = "updatePlayerPose", at = @At("STORE"), ordinal = 1)
//    private Pose changePoseTwoWhenCrawlingDisabled(Pose pose) {
//        if (!this.canPlayerFitWithinBlocksAndEntitiesWhen(Pose.CROUCHING) && GameplayTweak.DISABLE_CRAWLING.get()) {
//            return Pose.STANDING;
//        }
//        return pose;
//    }
}
