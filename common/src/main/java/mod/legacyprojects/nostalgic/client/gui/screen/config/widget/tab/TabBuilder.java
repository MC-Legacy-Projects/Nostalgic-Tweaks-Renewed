package mod.legacyprojects.nostalgic.client.gui.screen.config.widget.tab;

import mod.legacyprojects.nostalgic.client.gui.screen.config.ConfigScreen;
import mod.legacyprojects.nostalgic.client.gui.widget.button.AbstractButtonMaker;
import mod.legacyprojects.nostalgic.tweak.container.Container;
import net.minecraft.network.chat.Component;

public class TabBuilder extends AbstractButtonMaker<TabBuilder, TabButton>
{
    /* Fields */

    final ConfigScreen configScreen;
    final Container category;

    /* Constructor */

    protected TabBuilder(ConfigScreen configScreen, Container category)
    {
        super(Component.literal(category.toString()));

        this.configScreen = configScreen;
        this.category = category;
    }

    /* Methods */

    @Override
    public TabBuilder self()
    {
        return this;
    }

    @Override
    protected TabButton construct()
    {
        return new TabButton(this, this::onPress);
    }
}
