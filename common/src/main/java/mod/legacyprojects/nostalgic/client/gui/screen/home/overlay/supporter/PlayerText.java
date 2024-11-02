package mod.legacyprojects.nostalgic.client.gui.screen.home.overlay.supporter;

import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.DynamicWidget;
import mod.legacyprojects.nostalgic.util.common.data.Holder;
import mod.legacyprojects.nostalgic.util.common.data.NullableHolder;

record PlayerText(Holder<DynamicWidget<?, ?>> name, NullableHolder<DynamicWidget<?, ?>> first, NullableHolder<DynamicWidget<?, ?>> last)
{
}
