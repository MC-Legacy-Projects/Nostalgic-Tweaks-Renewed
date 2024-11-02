package mod.legacyprojects.nostalgic.neoforge.setup;

import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.client.gui.screen.home.HomeScreen;
import mod.legacyprojects.nostalgic.neoforge.event.AppleSkinHandler;
import mod.legacyprojects.nostalgic.neoforge.event.EmbeddiumHandler;
import mod.legacyprojects.nostalgic.util.ModTracker;
import mod.legacyprojects.nostalgic.util.client.gui.GuiUtil;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ModListScreen;
import net.neoforged.neoforge.common.NeoForge;

@EventBusSubscriber(
    modid = NostalgicTweaks.MOD_ID,
    bus = EventBusSubscriber.Bus.MOD,
    value = Dist.CLIENT
)
public abstract class ClientSetup
{
    /**
     * Instructions for client initialization.
     *
     * @param event A {@link FMLClientSetupEvent} event instance.
     */
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event)
    {
        // Register config screen
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, ClientSetup::getScreenFactory);

        // Define mod screen
        GuiUtil.modListScreen = ModListScreen::new;

        // Listen for AppleSkin
        if (ModTracker.APPLE_SKIN.isInstalled())
        {
            NeoForge.EVENT_BUS.register(AppleSkinHandler.class);
            NostalgicTweaks.LOGGER.info("Registered AppleSkin Listener");
        }

        // Listen for Embeddium
        if (ModTracker.EMBEDDIUM.isInstalled())
        {
            NeoForge.EVENT_BUS.register(EmbeddiumHandler.class);
            EmbeddiumHandler.init();

            NostalgicTweaks.LOGGER.info("Registered Embeddium Listener");
        }
    }

    /**
     * @return A {@link HomeScreen} instance that represents the mod's {@link IConfigScreenFactory}.
     */
    private static IConfigScreenFactory getScreenFactory()
    {
        return ((minecraft, screen) -> new HomeScreen(screen, false));
    }
}
