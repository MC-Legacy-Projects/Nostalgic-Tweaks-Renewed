package mod.adrenix.nostalgic.network.packet.sync;

import dev.architectury.networking.NetworkManager;
import mod.adrenix.nostalgic.network.packet.ModPacket;
import mod.adrenix.nostalgic.tweak.factory.Tweak;
import mod.adrenix.nostalgic.tweak.factory.TweakPool;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class ServerboundSyncAll implements ModPacket
{
    /* Constructors */

    /**
     * Send a request to the server to send its current tweak values so the client can be in sync with the server.
     */
    public ServerboundSyncAll()
    {
    }

    /**
     * Decode a buffer received over the network.
     *
     * @param buffer A {@link FriendlyByteBuf} instance.
     */
    public ServerboundSyncAll(FriendlyByteBuf buffer)
    {
    }

    /* Methods */

    @Override
    public void encode(FriendlyByteBuf buffer)
    {
    }

    @Override
    public void apply(NetworkManager.PacketContext context)
    {
        if (this.isClientHandling(context))
            return;

        final ServerPlayer player = this.getServerPlayer(context);

        TweakPool.filter(Tweak::isMultiplayerLike).forEach(tweak -> tweak.sendToPlayer(player));
    }
}