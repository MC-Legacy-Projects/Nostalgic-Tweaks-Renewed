package mod.adrenix.nostalgic.mixin.tweak.gameplay.animal_sheep;

import mod.adrenix.nostalgic.mixin.util.gameplay.SheepMixinHelper;
import mod.adrenix.nostalgic.tweak.config.GameplayTweak;
import mod.adrenix.nostalgic.util.common.ClassUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public abstract class AnimalMixin extends Mob
{
    /* Fake Constructor */

    private AnimalMixin(EntityType<? extends Mob> entityType, Level level)
    {
        super(entityType, level);
    }

    /* Injections */

    /**
     * Shears a sheep when a player "punches" the animal.
     */
    @Inject(
        method = "actuallyHurt",
        at = @At("HEAD")
    )
    private void nt_animal_sheep$onHurt(DamageSource damageSource, float damageAmount, CallbackInfo callback)
    {
        if (!GameplayTweak.OLD_SHEEP_PUNCHING.get())
            return;

        Sheep sheep = ClassUtil.cast(this, Sheep.class).orElse(null);

        if (sheep == null)
            return;

        SheepMixinHelper.punch(sheep, this.level(), damageSource, this.random);
    }
}
