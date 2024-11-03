package mod.legacyprojects.nostalgic.tweak.enums;

import mod.legacyprojects.nostalgic.util.common.lang.Lang;
import mod.legacyprojects.nostalgic.util.common.lang.Translation;

public enum SunsetSunriseType implements EnumTweak {
    JAVA(Lang.Enum.SUNSETSUNRISE_JAVA),
    TU1LCE(Lang.Enum.SUNSETSUNRISE_TU1LCE),
    TU5LCE(Lang.Enum.SUNSETSUNRISE_TU5LCE);

    private final Translation title;

    SunsetSunriseType(Translation title) {
        this.title = title;
    }

    @Override
    public Translation getTitle()
    {
        return this.title;
    }
}
