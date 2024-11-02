package mod.legacyprojects.nostalgic.client.gui.widget.input;

import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.*;
import mod.legacyprojects.nostalgic.util.common.data.CacheValue;

import java.util.List;

class LayoutSync<Builder extends AbstractInputMaker<Builder, Input>, Input extends AbstractInput<Builder, Input>>
    implements DynamicFunction<Builder, Input>
{
    @Override
    public void apply(Input widget, Builder builder)
    {
        DynamicWidget.sync(widget.internal);
        widget.setInput(widget.input);
    }

    @Override
    public boolean isReapplyNeeded(Input widget, Builder builder, WidgetCache cache)
    {
        return CacheValue.isAnyExpired(cache.x, cache.width);
    }

    @Override
    public List<DynamicField> getManaging(Builder builder)
    {
        return List.of();
    }

    @Override
    public DynamicPriority priority()
    {
        return DynamicPriority.HIGH;
    }
}
