package mod.legacyprojects.nostalgic.util.common;

/**
 * This utility stores URLs that are used by the mod.
 */
public interface LinkLocation
{
    String LICENSE = "https://github.com/MC-Legacy-Projects/Nostalgic-Tweaks-Renewed/tree/main/LICENSE";
    String DISCORD = "https://discord.gg/BXkUxFDz8c";
    String GOLDEN_DAYS = "https://modrinth.com/resourcepack/golden-days/versions";
    String DOWNLOAD = "https://modrinth.com/mod/nt-renewed/versions";
    String SUPPORTERS = "https://raw.githubusercontent.com/MC-Legacy-Projects/Nostalgic-Tweaks-Renewed/refs/heads/data/supporters-v3.json";

    static String getSupporterFace(String uuid)
    {
        return String.format("https://crafthead.net/avatar/%s/8", uuid);
    }
}
