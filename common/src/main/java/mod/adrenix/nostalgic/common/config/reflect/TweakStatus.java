package mod.adrenix.nostalgic.common.config.reflect;

import mod.adrenix.nostalgic.util.common.log.LogColor;

/**
 * There are numerous states a tweak can be in. Some tweaks will start in different states than others.
 *
 * Each tweak cache will have tweaks in their own unique state. This is done so the server can properly report
 * tweak states to clients.
 *
 * <ol>
 * <b>LOADED</b> - The tweak ran expected code. <br>
 * <b>WAIT</b> - The tweak will run code in a world. <br>
 * <b>WARN</b> - Inform the user that there is something important to know about a tweak. <br>
 * <b>FAIL</b> - The tweak never ran expected code or the tweak has not been loaded by code that runs it. <br>
 * </ol>
 */

public enum TweakStatus
{
    /* Types */

    LOADED,
    WAIT,
    WARN,
    FAIL;

    /* Static Methods */

    /**
     * Get the name of a status type in colored format. This can only be used by loggers that support ANSI color codes.
     * @param status A status type to retrieve <code>toString</code> data from.
     * @return A colored <code>toString</code> value.
     */
    public static String toStringWithColor(TweakStatus status)
    {
        return switch (status)
        {
            case LOADED -> LogColor.apply(LogColor.GREEN, status.toString());
            case WAIT -> LogColor.apply(LogColor.YELLOW, status.toString());
            case WARN -> LogColor.apply(LogColor.GOLD, status.toString());
            case FAIL -> LogColor.apply(LogColor.RED, status.toString());
        };
    }
}