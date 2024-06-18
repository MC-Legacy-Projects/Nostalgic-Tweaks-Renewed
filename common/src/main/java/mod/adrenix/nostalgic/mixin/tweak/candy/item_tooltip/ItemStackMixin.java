package mod.adrenix.nostalgic.mixin.tweak.candy.item_tooltip;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import mod.adrenix.nostalgic.tweak.config.CandyTweak;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin
{
    /**
     * Prevents certain aspects of an item tooltip from being shown based on tweak context.
     */
    @WrapWithCondition(
        method = "getTooltipLines",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V"
        )
    )
    private <T extends TooltipProvider> boolean nt_item_tooltip$shouldAddToTooltip(ItemStack itemStack, DataComponentType<T> component, Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag)
    {
        if (DataComponents.DYED_COLOR == component)
            return CandyTweak.SHOW_DYE_TIP.get();
        else if (DataComponents.ENCHANTMENTS == component)
            return CandyTweak.SHOW_ENCHANTMENT_TIP.get();

        return true;
    }

    /**
     * Determines whether the modifiers are shown on an item tooltip.
     */
    @WrapWithCondition(
        method = "method_57370",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;addModifierTooltip(Ljava/util/function/Consumer;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V"
        )
    )
    private boolean nt_item_tooltip$shouldAddModifierTooltip(ItemStack itemStack, Consumer<Component> tooltipAdder, @Nullable Player player, Holder<Attribute> attribute, AttributeModifier modifier)
    {
        return CandyTweak.SHOW_MODIFIER_TIP.get();
    }
}
