package mod.legacyprojects.nostalgic.client.gui.screen.config.overlay.manage;

import mod.legacyprojects.nostalgic.NostalgicTweaks;
import mod.legacyprojects.nostalgic.client.AfterConfigSave;
import mod.legacyprojects.nostalgic.client.gui.overlay.types.state.SwitchGroup;
import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonTemplate;
import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.group.Group;
import mod.legacyprojects.nostalgic.client.gui.widget.separator.SeparatorWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.text.TextWidget;
import mod.legacyprojects.nostalgic.config.factory.ConfigBuilder;
import mod.legacyprojects.nostalgic.tweak.config.ModTweak;
import mod.legacyprojects.nostalgic.tweak.factory.Tweak;
import mod.legacyprojects.nostalgic.tweak.factory.TweakFlag;
import mod.legacyprojects.nostalgic.tweak.factory.TweakPool;
import mod.legacyprojects.nostalgic.util.common.asset.Icons;
import mod.legacyprojects.nostalgic.util.common.asset.TextureIcon;
import mod.legacyprojects.nostalgic.util.common.color.Color;
import mod.legacyprojects.nostalgic.util.common.data.FlagHolder;
import mod.legacyprojects.nostalgic.util.common.lang.Lang;
import mod.legacyprojects.nostalgic.util.common.lang.Translation;
import net.minecraft.world.item.Items;

import java.util.concurrent.TimeUnit;

public class GroupMenuOptions extends ManageGroup
{
    /* Fields */

    private final TweakFlag tweak = ModTweak.PERSISTENT_CONFIG_SCREEN;

    /* Methods */

    @Override
    void define(ManageOverlay manager)
    {
        /* Toggle View */

        Group view = Group.create(manager.overlay)
            .forceRelativeY()
            .title(Lang.Manage.TOGGLE_VIEW_SCREEN)
            .icon(TextureIcon.fromItem(Items.SPYGLASS))
            .border(Color.SUMMER_YELLOW)
            .rightOf(manager.separator, manager.padding)
            .extendWidthToScreenEnd(0)
            .build(this::register);

        TextWidget.create(Lang.Manage.TOGGLE_VIEW_INFO).width(view::getInsideWidth).build(view::addWidget);

        /* Persistence */

        Group persistence = Group.create(manager.overlay)
            .icon(Icons.SAVE_FLOPPY)
            .title(Lang.Manage.PERSISTENCE)
            .border(Color.IRIS_BLUE)
            .below(view, manager.padding)
            .rightOf(manager.separator, manager.padding)
            .extendWidthToScreenEnd(0)
            .build(this::register);

        Translation persistHead = Lang.Manage.PERSISTENT_SCREEN;
        Translation persistInfo = Lang.Manage.PERSISTENT_INFO;

        SwitchGroup.Widgets persistSwitch = SwitchGroup.create(manager.overlay, persistHead, persistInfo, this.tweak::get, this.tweak::setDiskAndSave)
            .getWidgets();

        persistSwitch.header().getBuilder().extendWidthToEnd(persistence, persistence.getInsidePaddingX());
        persistSwitch.description().getBuilder().extendWidthToEnd(persistence, persistence.getInsidePaddingX());
        persistSwitch.subscribeTo(persistence);

        /* Reset Config */

        Group reset = Group.create(manager.overlay)
            .icon(Icons.RED_UNDO)
            .title(Lang.Manage.RESET_CONFIG)
            .border(Color.GOLDEN_GATE_BRIDGE)
            .below(persistence, manager.padding)
            .rightOf(manager.separator, manager.padding)
            .extendWidthToScreenEnd(0)
            .build(this::register);

        TextWidget resetInfo = TextWidget.create(Lang.Manage.RESET_CONFIG_INFO)
            .width(reset::getInsideWidth)
            .build(reset::addWidget);

        SeparatorWidget separator = SeparatorWidget.create(reset.getColor())
            .height(1)
            .below(resetInfo, manager.padding * 2)
            .width(reset::getInsideWidth)
            .build(reset::addWidget);

        final FlagHolder backup = FlagHolder.on();

        ButtonWidget checkbox = ButtonTemplate.checkbox(Lang.Manage.RESET_CONFIG_BACKUP, backup::get)
            .skipFocusOnClick()
            .below(separator, manager.padding * 2)
            .extendWidthToEnd(reset, reset.getInsidePaddingX())
            .onPress(backup::toggle)
            .build(reset::addWidget);

        ButtonWidget.create(Lang.Manage.RESET_CONFIG)
            .icon(Icons.RED_UNDO)
            .useTextWidth()
            .centerInWidgetX(reset)
            .holdFor(1L, TimeUnit.SECONDS)
            .onPress(() -> resetConfig(backup.get()))
            .below(checkbox, manager.padding * 2)
            .build(reset::addWidget);
    }

    /**
     * Creates a backup of the current config and resets the config back to the mod's default tweak values.
     */
    private static void resetConfig(boolean backup)
    {
        if (backup)
            ConfigBuilder.getHandler().backup();

        ConfigBuilder.getHandler().setLoaded(ConfigBuilder.getHandler().getDefault());
        ConfigBuilder.getHandler().save();

        TweakPool.values().forEach(Tweak::sync);
        AfterConfigSave.reloadAndRun();

        NostalgicTweaks.LOGGER.info("[Config Reset] Restored config back to its default state");
    }
}
