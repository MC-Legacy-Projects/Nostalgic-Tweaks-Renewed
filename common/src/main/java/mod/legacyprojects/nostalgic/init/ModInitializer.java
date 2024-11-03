package mod.legacyprojects.nostalgic.init;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.helper.gameplay.MobLootHelper;
import mod.legacyprojects.nostalgic.listener.common.InteractionListener;
import mod.legacyprojects.nostalgic.listener.common.PlayerListener;
import mod.legacyprojects.nostalgic.network.PacketRegistry;
import mod.legacyprojects.nostalgic.tweak.factory.Tweak;
import mod.legacyprojects.nostalgic.tweak.factory.TweakPool;
import mod.legacyprojects.nostalgic.util.server.ServerTimer;

public abstract class ModInitializer
{
    /**
     * Registers common mod events.
     */
    public static void register()
    {
        PacketRegistry.register();
        InteractionListener.register();
        PlayerListener.register();

        LifecycleEvent.SERVER_BEFORE_START.register(NostalgicTweaks::setServer);
        TickEvent.SERVER_PRE.register(server -> ServerTimer.getInstance().onTick());
        TickEvent.SERVER_POST.register(server -> TweakPool.stream().forEach(Tweak::invalidate));

        EnvExecutor.runInEnv(Env.CLIENT, () -> ClientInitializer::register);

        LifecycleEvent.SERVER_LEVEL_LOAD.register(MobLootHelper::init);
    }
}
