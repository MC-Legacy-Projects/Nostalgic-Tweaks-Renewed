package mod.legacyprojects.nostalgic.init;

import dev.architectury.event.events.client.ClientPlayerEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.ReloadListenerRegistry;
import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.client.AfterConfigSave;
import mod.legacyprojects.nostalgic.client.ClientKeyMapping;
import mod.legacyprojects.nostalgic.client.ClientSound;
import mod.legacyprojects.nostalgic.client.gui.screen.home.HomeSplash;
import mod.legacyprojects.nostalgic.client.gui.screen.home.Panorama;
import mod.legacyprojects.nostalgic.client.gui.screen.vanilla.title.NostalgicLogoText;
import mod.legacyprojects.nostalgic.helper.candy.block.ChestHelper;
import mod.legacyprojects.nostalgic.helper.candy.hud.HudHelper;
import mod.legacyprojects.nostalgic.helper.candy.level.fog.OverworldFogRenderer;
import mod.legacyprojects.nostalgic.helper.candy.level.fog.VoidFogRenderer;
import mod.legacyprojects.nostalgic.helper.candy.level.fog.WaterFogRenderer;
import mod.legacyprojects.nostalgic.helper.candy.light.LightTextureHelper;
import mod.legacyprojects.nostalgic.helper.candy.light.LightingHelper;
import mod.legacyprojects.nostalgic.listener.client.GuiListener;
import mod.legacyprojects.nostalgic.listener.client.TooltipListener;
import mod.legacyprojects.nostalgic.network.packet.sync.ServerboundSyncTweak;
import mod.legacyprojects.nostalgic.tweak.factory.Tweak;
import mod.legacyprojects.nostalgic.tweak.factory.TweakPool;
import mod.legacyprojects.nostalgic.util.client.animate.Animator;
import mod.legacyprojects.nostalgic.util.client.timer.ClientTimer;
import mod.legacyprojects.nostalgic.util.common.network.PacketUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.packs.PackType;

abstract class ClientInitializer
{
    /**
     * Registers client events.
     */
    static void register()
    {
        ClientKeyMapping.register();
        ClientSound.register();
        GuiListener.register();
        TooltipListener.register();

        ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, HomeSplash.getInstance());
        ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, NostalgicLogoText.getInstance());

        for (Panorama panorama : Panorama.values())
            ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, panorama);

        ClientTickEvent.CLIENT_PRE.register(ClientInitializer::onPreTick);
        ClientTickEvent.CLIENT_POST.register(ClientInitializer::onPostTick);
        ClientPlayerEvent.CLIENT_PLAYER_QUIT.register(ClientInitializer::onPlayerQuit);

        AfterConfigSave.addInstruction(HudHelper::runAfterSave);

        ChestHelper.init();
        LightingHelper.init();
    }

    /**
     * Removes the verification of the mod connection when the player leaves a world with the mod installed and performs
     * other needed tasks when the player leaves the world.
     *
     * @param player The {@link LocalPlayer} instance.
     */
    private static void onPlayerQuit(LocalPlayer player)
    {
        TweakPool.stream().forEach(Tweak::disconnect);

        NostalgicTweaks.setNetworkVerification(false);
        NostalgicTweaks.setConnection(null);

        LightingHelper.resetLightingCache();
        LightTextureHelper.resetLightingCache();

        OverworldFogRenderer.reset();
        WaterFogRenderer.reset();
        VoidFogRenderer.reset();
    }

    /**
     * Instructions to perform at the start of every tick.
     *
     * @param minecraft The {@link Minecraft} singleton instance.
     */
    private static void onPreTick(Minecraft minecraft)
    {
        ClientTimer.getInstance().onTick();
        Animator.onTick();
        Panorama.onTick();

        LightingHelper.onTick();
    }

    /**
     * Instructions to perform at the end of every tick.
     *
     * @param minecraft The {@link Minecraft} singleton instance.
     */
    private static void onPostTick(Minecraft minecraft)
    {
        TweakPool.stream().forEach(Tweak::invalidate);

        if (NostalgicTweaks.isNetworkVerified())
            ClientTimer.getInstance().runAfter(3000L, ClientInitializer::syncAllTweaks);
    }

    /**
     * Check and sync tweaks that are not currently in sync with a verified server.
     */
    private static void syncAllTweaks()
    {
        if (!NostalgicTweaks.isNetworkVerified())
            return;

        TweakPool.filter(Tweak::isNotConnected)
            .forEach(tweak -> PacketUtil.sendToServer(new ServerboundSyncTweak(tweak)));
    }
}
