package mod.legacyprojects.nostalgic.client.gui.widget.group;

import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.*;
import mod.legacyprojects.nostalgic.util.common.data.CacheValue;

import java.util.List;

class GroupSync implements DynamicFunction<GroupBuilder, Group>
{
    @Override
    public void apply(Group group, GroupBuilder builder)
    {
        group.widgets.forEach(widget -> {
            widget.setActive(group.isActive());
            widget.setVisible(group.isVisible());

            if (group.isActive())
                widget.setActive(widget.getActiveTest());

            if (group.isVisible())
                widget.setVisible(widget.getVisibleTest());
        });

        DynamicWidget.syncWithoutCache(group.widgets);
    }

    @Override
    public boolean isReapplyNeeded(Group group, GroupBuilder builder, WidgetCache cache)
    {
        return CacheValue.isAnyExpired(cache.active, cache.visible);
    }

    @Override
    public List<DynamicField> getManaging(GroupBuilder builder)
    {
        return List.of();
    }

    @Override
    public DynamicPriority priority()
    {
        return DynamicPriority.HIGH;
    }
}
