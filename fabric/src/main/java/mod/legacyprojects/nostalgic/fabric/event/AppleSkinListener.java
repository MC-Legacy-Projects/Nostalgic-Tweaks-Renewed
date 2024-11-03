package mod.legacyprojects.nostalgic.fabric.event;

import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.tweak.config.GameplayTweak;
import squeek.appleskin.api.AppleSkinApi;
import squeek.appleskin.api.event.TooltipOverlayEvent;

public class AppleSkinListener implements AppleSkinApi
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerEvents()
    {
        TooltipOverlayEvent.Pre.EVENT.register(AppleSkinListener::disableTooltip);
        TooltipOverlayEvent.Render.EVENT.register(AppleSkinListener::disableTooltip);

        NostalgicTweaks.LOGGER.info("Registered Fabric AppleSkin Listener");
    }

    /**
     * Prevents tooltip food data for food items being displayed if hunger is disabled.
     *
     * @param event The {@link TooltipOverlayEvent} instance.
     */
    private static void disableTooltip(TooltipOverlayEvent event)
    {
        if (GameplayTweak.DISABLE_HUNGER.get())
            event.isCanceled = true;
    }
}
