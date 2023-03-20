package mod.adrenix.nostalgic.common.config.v2.network.packet;

import dev.architectury.networking.NetworkManager;
import mod.adrenix.nostalgic.NostalgicTweaks;
import mod.adrenix.nostalgic.common.config.reflect.TweakStatus;
import mod.adrenix.nostalgic.common.config.v2.cache.ConfigCache;
import mod.adrenix.nostalgic.common.config.v2.network.TweakSerializer;
import mod.adrenix.nostalgic.common.config.v2.tweak.Tweak;
import mod.adrenix.nostalgic.util.common.PacketUtil;
import mod.adrenix.nostalgic.util.common.log.LogColor;
import net.fabricmc.api.EnvType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.function.Supplier;

/**
 * This packet sends a tweak update to the server.
 *
 * The server will process the packet and check if the sender is an operator before updating the server config file.
 * Once the config data is saved, the server will forward the update to all connected clients.
 */

public class v2PacketC2SChangeTweak
{
    /**
     * Register this packet to the mod's network channel.
     * Channel registration is handled by Architectury.
     */
    public static void register()
    {
        NostalgicTweaks.NETWORK.register
        (
            v2PacketC2SChangeTweak.class,
            v2PacketC2SChangeTweak::encode,
            v2PacketC2SChangeTweak::new,
            v2PacketC2SChangeTweak::handle
        );
    }

    /* Fields */

    private final String json;

    /* Constructors */

    /**
     * Create a new change tweak packet with a tweak instance.
     * This creates a packet using a serialized JSON string.
     *
     * @param tweak A tweak instance.
     */
    public v2PacketC2SChangeTweak(Tweak<?> tweak)
    {
        this.json = new TweakSerializer<>(tweak).serialize();
    }

    /**
     * Create a new change tweak packet with a buffer.
     * This decodes a packet into a JSON string.
     *
     * @param buffer A friendly byte buffer instance.
     */
    public v2PacketC2SChangeTweak(FriendlyByteBuf buffer)
    {
        this.json = buffer.readUtf();
    }

    /* Methods */

    /**
     * Encode data into the packet.
     * @param buffer A friendly byte buffer instance.
     */
    public void encode(FriendlyByteBuf buffer)
    {
        buffer.writeUtf(this.json);
    }

    /**
     * Handle packet data.
     * @param supplier A packet context supplier.
     */
    public void handle(Supplier<NetworkManager.PacketContext> supplier)
    {
        // Server received packet data
        supplier.get().queue(() -> this.process(supplier.get()));
    }

    /**
     * Process packet data.
     * @param context Network manager packet context.
     */
    private void process(NetworkManager.PacketContext context)
    {
        if (context.getEnv() == EnvType.CLIENT)
        {
            PacketUtil.warn(EnvType.CLIENT, this.getClass());
            return;
        }

        // Check if sender of new data is allowed to update server values
        ServerPlayer player = (ServerPlayer) context.getPlayer();

        if (!PacketUtil.isPlayerOp(player))
        {
            String warn = String.format("%s tried changing a tweak without permission", player.getDisplayName().getString());
            NostalgicTweaks.LOGGER.warn(warn);

            return;
        }

        // Retrieve server tweak data
        TweakSerializer<?> serializer = TweakSerializer.deserialize(this.json);
        Tweak<?> tweak = Tweak.get(serializer.getCacheKey());

        // Check if data sent from operator is something the server understands
        if (Tweak.get(serializer.getCacheKey()) == null)
        {
            String playerName = player.getDisplayName().getString();
            String cacheId = serializer.getCacheKey();
            String warn = String.format("%s tried changing [tweak={cacheId:%s}] but it doesn't exist in the server config", playerName, cacheId);

            NostalgicTweaks.LOGGER.warn(warn);

            return;
        }

        // Log information
        String information = String.format
        (
            """
                Received tweak update from client operator:
                NEW -> [tweak={cacheId:%s, newValue:%s, status:%s}]
                OLD -> [tweak={cacheId:%s, newValue:%s, status:%s}]
            """,

            LogColor.apply(LogColor.LIGHT_PURPLE, serializer.getCacheKey()),
            LogColor.apply(LogColor.BLUE, serializer.getSendingValue().toString()),
            TweakStatus.toStringWithColor(serializer.getStatus()),

            LogColor.apply(LogColor.LIGHT_PURPLE, tweak.getCacheKey()),
            LogColor.apply(LogColor.BLUE, tweak.getValue().toString()),
            TweakStatus.toStringWithColor(tweak.getStatus())
        );

        NostalgicTweaks.LOGGER.info(information);

        // Update server tweak value with data sent from client
        tweak.setValue(serializer.getSendingValue());

        // Even though this packet is handled by the server, we don't want singleplayer worlds to save a server config
        if (NostalgicTweaks.isServer())
            ConfigCache.saveServer();

        // Send tweak update to all connected players
        List<ServerPlayer> players = player.server.getPlayerList().getPlayers();
        PacketUtil.sendToAll(players, new v2PacketS2CTweakUpdate(tweak));
    }
}