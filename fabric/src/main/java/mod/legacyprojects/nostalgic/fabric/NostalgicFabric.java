package mod.legacyprojects.nostalgic.fabric;

import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.fabric.network.Networking;
import mod.legacyprojects.nostalgic.util.ModTracker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class NostalgicFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        // Mod tracking
        ModTracker.init(FabricLoader.getInstance()::isModLoaded);

        // Initialize mod
        NostalgicTweaks.initialize();

        // Register networking
        Networking.register();
    }
}
