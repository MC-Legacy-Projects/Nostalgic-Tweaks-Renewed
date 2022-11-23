package mod.adrenix.nostalgic.common.config.tweak;

import mod.adrenix.nostalgic.NostalgicTweaks;
import mod.adrenix.nostalgic.client.config.reflect.TweakClientCache;
import mod.adrenix.nostalgic.common.config.reflect.TweakGroup;
import mod.adrenix.nostalgic.server.config.reflect.TweakServerCache;

public enum AnimationTweak implements Tweak
{
    // Arm Animations

    ARM_SWAY,
    ARM_SWAY_MIRROR,
    ARM_SWAY_INTENSITY,
    ITEM_SWING,
    SWING_INTERRUPT,
    SWING_DROP,

    // Item Animations

    COOLDOWN,
    REEQUIP,
    TOOL_EXPLODE,

    // Mob Animations

    ZOMBIE_ARMS,
    SKELETON_ARMS,
    GHAST_CHARGING,

    // Player Animations

    DEATH_TOPPLE,
    BACKWARD_WALK,
    COLLIDE_BOB,
    RANDOM_DAMAGE,
    DIRECTIONAL_DAMAGE,
    BOB_VERTICAL,
    CREATIVE_CROUCH,
    SNEAK_SMOOTH;

    /* Fields */

    /**
     * This field must be defined in the client config within a static block below an entry definition.
     * There are safeguard checks in place to prevent missing, mistyped, or invalid key entries.
     */
    private String key;

    /**
     * Keeps track of whether this tweak is client or server controller.
     */
    private NostalgicTweaks.Side side = null;

    /**
     * Keeps track of whether this tweak has had its enumeration queried.
     */
    private boolean loaded = false;

    /* Caching */

    private TweakClientCache<?> clientCache;
    private TweakServerCache<?> serverCache;

    /* Tweak Implementation */

    @Override public TweakGroup getGroup() { return TweakGroup.ANIMATION; }

    @Override public void setKey(String key) { this.key = key; }
    @Override public String getKey() { return this.key; }

    @Override public void setSide(NostalgicTweaks.Side side) { this.side = side; }
    @Override public NostalgicTweaks.Side getSide() { return this.side; }

    @Override public void setClientCache(TweakClientCache<?> cache) { this.clientCache = cache; }
    @Override public TweakClientCache<?> getClientCache() { return this.clientCache; }

    @Override public void setServerCache(TweakServerCache<?> cache) { this.serverCache = cache; }
    @Override public TweakServerCache<?> getServerCache() { return this.serverCache; }

    @Override public void setLoaded(boolean state) { this.loaded = state; }
    @Override public boolean isLoaded() { return this.loaded; }
}
