package mod.legacyprojects.nostalgic.client.gui.screen.config.overlay.manage;

import mod.legacyprojects.nostalgic.client.gui.overlay.Overlay;
import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonBuilder;
import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.DynamicWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.LayoutBuilder;
import mod.legacyprojects.nostalgic.client.gui.widget.separator.SeparatorWidget;
import mod.legacyprojects.nostalgic.util.common.CollectionUtil;
import mod.legacyprojects.nostalgic.util.common.array.UniqueArrayList;
import mod.legacyprojects.nostalgic.util.common.asset.Icons;
import mod.legacyprojects.nostalgic.util.common.color.Color;
import mod.legacyprojects.nostalgic.util.common.function.ForEachWithPrevious;
import mod.legacyprojects.nostalgic.util.common.lang.Lang;

public class ManageOverlay
{
    /* Fields */

    final int padding = 2;
    final Overlay overlay;
    final SeparatorWidget separator;
    final UniqueArrayList<ButtonWidget> sections;

    /* Constructor */

    public ManageOverlay()
    {
        this.sections = new UniqueArrayList<>();

        this.overlay = Overlay.create(Lang.Manage.TITLE)
            .icon(Icons.SMALL_TOOLS)
            .resizeUsingPercentage(0.9D)
            .padding(this.padding)
            .onClose(this::close)
            .build();

        this.overlay.runOnTick(() -> {
            for (ManageThreadMessage message : ManageThreadMessage.values())
                message.tick();
        });

        int maxWidth = ManageSection.getLargestWidth();

        ManageSection.stream()
            .map(ManageSection::button)
            .map(builder -> builder.width(maxWidth))
            .map(ButtonBuilder::build)
            .forEach(this.sections::add);

        ForEachWithPrevious.create(this.sections)
            .forEach((prev, next) -> next.getBuilder().below(prev, this.padding))
            .run();

        this.separator = SeparatorWidget.create(Color.WHITE)
            .width(1)
            .rightOf(this.sections.getFirst(), this.padding)
            .height(() -> this.overlay.getInsideHeight() - this.padding * 2)
            .build();

        this.sections.forEach(this.overlay::addWidget);
        this.overlay.addWidget(this.separator);

        UniqueArrayList<DynamicWidget<?, ?>> widgets = this.overlay.getExternalWidgets();

        CollectionUtil.fromCast(widgets.stream().map(DynamicWidget::getBuilder), LayoutBuilder.class)
            .forEach(LayoutBuilder::anchor);

        ManageSection.stream().forEach(section -> section.setOverlay(this.overlay));
        ManageSection.stream().map(ManageSection::getManager).forEach(manager -> manager.subscribe(this));
        ManageSection.stream().findFirst().ifPresent(ManageSection::activate);
    }

    /* Methods */

    /**
     * Open the config manager overlay.
     */
    public void open()
    {
        this.overlay.open();
    }

    /**
     * Closes the config manager overlay and releases section cache from memory.
     */
    public void close()
    {
        ManageSection.stream().forEach(section -> section.setOverlay(null));
    }
}
