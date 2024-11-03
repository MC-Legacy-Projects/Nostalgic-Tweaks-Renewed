package mod.legacyprojects.nostalgic.client.gui.screen.home;

import com.mojang.blaze3d.vertex.BufferBuilder;
import mod.legacyprojects.nostalgic.client.gui.screen.WidgetManager;
import mod.legacyprojects.nostalgic.client.gui.screen.config.ConfigScreen;
import mod.legacyprojects.nostalgic.client.gui.screen.home.overlay.DebugOverlay;
import mod.legacyprojects.nostalgic.client.gui.screen.home.overlay.SetupOverlay;
import mod.legacyprojects.nostalgic.client.gui.screen.home.overlay.supporter.SupporterOverlay;
import mod.legacyprojects.nostalgic.client.gui.screen.packs.PacksListScreen;
import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.DynamicWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.dynamic.LayoutBuilder;
import mod.legacyprojects.nostalgic.client.gui.widget.icon.IconFactory;
import mod.legacyprojects.nostalgic.client.gui.widget.icon.IconTemplate;
import mod.legacyprojects.nostalgic.client.gui.widget.icon.IconWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.separator.SeparatorWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.text.TextWidget;
import mod.legacyprojects.nostalgic.tweak.config.ModTweak;
import mod.legacyprojects.nostalgic.util.client.gui.GuiUtil;
import mod.legacyprojects.nostalgic.util.client.gui.LinkUtil;
import mod.legacyprojects.nostalgic.util.client.renderer.RenderPass;
import mod.legacyprojects.nostalgic.util.client.renderer.RenderUtil;
import mod.legacyprojects.nostalgic.util.common.LinkLocation;
import mod.legacyprojects.nostalgic.util.common.asset.Icons;
import mod.legacyprojects.nostalgic.util.common.asset.TextureIcon;
import mod.legacyprojects.nostalgic.util.common.color.Color;
import mod.legacyprojects.nostalgic.util.common.lang.Lang;
import mod.legacyprojects.nostalgic.util.common.math.MathUtil;
import mod.legacyprojects.nostalgic.util.common.timer.FlagTimer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class HomeWidgets implements WidgetManager
{
    /* Fields */

    private final HomeScreen homeScreen;
    private final IconFactory heartOutline;
    private final FlagTimer flashTimer;

    /* Constructor */

    HomeWidgets(HomeScreen homeScreen)
    {
        this.homeScreen = homeScreen;
        this.flashTimer = FlagTimer.create(1L, TimeUnit.SECONDS).build();
        this.heartOutline = IconWidget.create(Icons.HEART_OUTLINE).cannotFocus().renderWhen(RenderPass.LAST).size(12);
    }

    /* Methods */

    @Override
    public void init()
    {
        int sectionMargin = 0;
        int sectionOffset = 6;

        /* Menus & Links */

        ButtonWidget config = ButtonWidget.create(Lang.Enum.SCREEN_CONFIG)
            .icon(Icons.MECHANICAL_TOOLS)
            .useTextWidth()
            .alignLeft(sectionOffset)
            .onPress(() -> Minecraft.getInstance().setScreen(new ConfigScreen(this.homeScreen)))
            .backgroundRenderer(this::renderLeftTransparent)
            .build(this.homeScreen::addWidget);

        ButtonWidget presets = ButtonWidget.create(Lang.Enum.SCREEN_PACKS)
            .icon(Icons.ZIP_FILE)
            .alignFlushTo(config)
            .below(config, sectionMargin)
            .useTextWidth()
            .alignLeft(sectionOffset)
            .onPress(() -> Minecraft.getInstance().setScreen(new PacksListScreen(this.homeScreen)))
            .backgroundRenderer(this::renderLeftTransparent)
            .build(this.homeScreen::addWidget);

        ButtonWidget discord = ButtonWidget.create(Lang.Home.DISCORD)
            .icon(Icons.DISCORD)
            .alignFlushTo(presets)
            .below(presets, sectionMargin)
            .useTextWidth()
            .alignLeft(sectionOffset)
            .onPress(LinkUtil.onPress(LinkLocation.DISCORD))
            .backgroundRenderer(this::renderLeftTransparent)
            .build(this.homeScreen::addWidget);

        ButtonWidget golden = ButtonWidget.create(Lang.Home.GOLDEN_DAYS)
            .icon(Icons.GOLDEN_DAYS)
            .alignFlushTo(discord)
            .below(discord, sectionMargin)
            .useTextWidth()
            .alignLeft(sectionOffset)
            .onPress(LinkUtil.onPress(LinkLocation.GOLDEN_DAYS))
            .backgroundRenderer(this::renderLeftTransparent)
            .build(this.homeScreen::addWidget);

        SeparatorWidget separator = SeparatorWidget.create(Color.WHITE)
            .height(1)
            .below(golden, 2)
            .build(this.homeScreen::addWidget);

        ButtonWidget.create(Lang.Affirm.QUIT_CANCEL)
            .icon(Icons.GO_BACK)
            .alignFlushTo(golden)
            .below(separator, 1)
            .useTextWidth()
            .alignLeft(sectionOffset)
            .onPress(this.homeScreen::onClose)
            .backgroundRenderer(this::renderLeftTransparent)
            .build(this.homeScreen::addWidget);

        List<DynamicWidget<?, ?>> extended = this.homeScreen.getWidgets().stream().toList();

        this.homeScreen.getWidgets().stream().map(DynamicWidget::getBuilder).forEach(builder -> {
            if (builder instanceof LayoutBuilder<?, ?> layout)
                layout.extendWidthToLargest(extended);
        });

        config.getBuilder().posY(() -> {
            int spacing = (extended.size() - 1) * (20 + sectionMargin);
            return Math.round(MathUtil.center(spacing - sectionMargin, GuiUtil.getGuiHeight()));
        });

        /* Panorama */

        IconWidget panoramaLast = IconTemplate.button(Icons.SMALL_REWIND, Icons.SMALL_REWIND_HOVER, Icons.SMALL_REWIND_OFF)
            .pos(1, 1)
            .cannotFocus()
            .tooltip(Lang.Home.PREV_PANORAMA, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Home.PREV_PANORAMA_INFO, 35)
            .visibleIf(() -> Minecraft.getInstance().level == null)
            .onPress(Panorama::backward)
            .build(this.homeScreen::addWidget);

        Supplier<TextureIcon> cycleIcon = () -> this.getPlayOrPause(Icons.SMALL_PLAY, Icons.SMALL_PAUSE);
        Supplier<TextureIcon> cycleHover = () -> this.getPlayOrPause(Icons.SMALL_PLAY_HOVER, Icons.SMALL_PAUSE_HOVER);
        Supplier<TextureIcon> cyclePressed = () -> this.getPlayOrPause(Icons.SMALL_PLAY_OFF, Icons.SMALL_PAUSE_OFF);

        IconWidget panoramaCycle = IconTemplate.button(cycleIcon, cycleHover, cyclePressed)
            .cannotFocus()
            .rightOf(panoramaLast, 1)
            .tooltip(Lang.Home.CYCLE_PANORAMA, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Home.CYCLE_PANORAMA_INFO, 35)
            .visibleIf(() -> Minecraft.getInstance().level == null)
            .onPress(() -> {
                if (Panorama.isPaused())
                    Panorama.unpause();
                else
                    Panorama.pause();
            })
            .build(this.homeScreen::addWidget);

        IconTemplate.button(Icons.SMALL_NEXT, Icons.SMALL_NEXT_HOVER, Icons.SMALL_NEXT_OFF)
            .cannotFocus()
            .rightOf(panoramaCycle, 1)
            .tooltip(Lang.Home.NEXT_PANORAMA, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Home.NEXT_PANORAMA_INFO, 35)
            .visibleIf(() -> Minecraft.getInstance().level == null)
            .onPress(Panorama::forward)
            .build(this.homeScreen::addWidget);

        /* Extras */

        ButtonWidget debug = ButtonWidget.create()
            .icon(Icons.BUG)
            .tooltip(Lang.Home.DEBUG, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Tooltip.HOME_DEBUG, 35)
            .fromScreenEndX(1)
            .fromScreenEndY(1)
            .tabOrderGroup(4)
            .onPress(() -> new DebugOverlay().open())
            .build(this.homeScreen::addWidget);

        ButtonWidget heart = ButtonWidget.create()
            .icon(this::getHeart, 12)
            .tooltip(Lang.Home.SUPPORTERS, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Tooltip.HOME_SUPPORTERS, 35)
            .leftOf(debug, 1)
            .tabOrderGroup(3)
            .onPress(() -> new SupporterOverlay().open())
            .build(this.homeScreen::addWidget);

        this.heartOutline.pos(heart::getIconX, heart::getIconY)
            .visibleIf(heart::isHoveredOrFocused)
            .build(this.homeScreen::addWidget);

        ButtonWidget.create()
            .icon(Icons.MECHANICAL_TOOLS)
            .tooltip(Lang.Home.INIT_CONFIG, 35, 500L, TimeUnit.MILLISECONDS)
            .infoTooltip(Lang.Tooltip.HOME_INIT, 35)
            .leftOf(heart, 1)
            .tabOrderGroup(2)
            .onPress(SetupOverlay::open)
            .build(this.homeScreen::addWidget);

        /* Copyright */

        TextWidget.create("Made by Legacy Projects & Adrenix\nLGPL-3.0-only")
            .onPress(LinkUtil.onPress(LinkLocation.LICENSE))
            .color(Color.fromFormatting(ChatFormatting.GRAY))
            .useTextWidth()
            .centerAligned()
            .centerInScreenX()
            .fromScreenEndY(1)
            .tabOrderGroup(1)
            .build(this.homeScreen::addWidget);
    }

    /**
     * @return A {@link TextureIcon} heart.
     */
    private TextureIcon getHeart()
    {
        if (this.flashTimer.getFlag() && !ModTweak.OPENED_SUPPORTER_SCREEN.get())
            return Icons.HEART_EMPTY;

        return Icons.HEART;
    }

    /**
     * Get the correct play/pause icon based on panorama context.
     *
     * @param play  The play {@link TextureIcon}.
     * @param pause The pause {@link TextureIcon}.
     * @return A {@link TextureIcon} instanced based on panorama context.
     */
    private TextureIcon getPlayOrPause(TextureIcon play, TextureIcon pause)
    {
        if (Panorama.isPaused())
            return play;

        return pause;
    }

    /**
     * Handler method for rendering a transparent section button background.
     *
     * @param button      A {@link ButtonWidget} instance.
     * @param graphics    A {@link GuiGraphics} instance.
     * @param mouseX      The current x-position of the mouse.
     * @param mouseY      The current y-position of the mouse.
     * @param partialTick The normalized progress between two ticks [0.0F-1.0F].
     */
    private void renderLeftTransparent(ButtonWidget button, GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        final Color bar = Color.BLACK.fromAlpha(0.0F);
        final Color fill = Color.BLACK.fromAlpha(0.6F);
        final BufferBuilder builder = RenderUtil.getAndBeginFill();

        if (button.isHoveredOrFocused())
        {
            bar.set(Color.LEMON_YELLOW);
            bar.setAlpha(1.0F);

            RenderUtil.fill(builder, graphics, button.getX(), button.getY(), button.getEndX(), button.getEndY(), fill.get());
        }

        RenderUtil.fill(builder, graphics, button.getX(), button.getY(), button.getX() + 2, button.getEndY(), bar.get());
        RenderUtil.endFill(builder);
    }
}
