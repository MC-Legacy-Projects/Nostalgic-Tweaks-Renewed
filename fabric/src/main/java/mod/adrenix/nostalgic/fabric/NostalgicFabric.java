package mod.adrenix.nostalgic.fabric;

import mod.adrenix.nostalgic.common.ArchCommonEvents;
import mod.adrenix.nostalgic.util.ModTracker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class NostalgicFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        // Mod tracking
        ModTracker.init(FabricLoader.getInstance()::isModLoaded);

        // Register Architectury events
        ArchCommonEvents.register();
    }
}