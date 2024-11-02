package mod.legacyprojects.nostalgic.client.gui.overlay.types.color;

import mod.legacyprojects.nostalgic.client.gui.widget.button.ButtonWidget;
import mod.legacyprojects.nostalgic.client.gui.widget.input.ColorInput;
import mod.legacyprojects.nostalgic.util.common.asset.Icons;
import mod.legacyprojects.nostalgic.util.common.color.Color;
import mod.legacyprojects.nostalgic.util.common.color.HexUtil;
import mod.legacyprojects.nostalgic.util.common.lang.Lang;

import java.util.concurrent.TimeUnit;

class PickerHex
{
    final ColorInput input;
    final ButtonWidget reset;

    PickerHex(ColorPicker picker)
    {
        this.input = ColorInput.create(picker.color)
            .width(ColorPicker.GROUP_WIDTH - 38)
            .background(Color.BLACK, Color.FRENCH_SKY_BLUE)
            .hover(Color.FRENCH_SKY_BLUE, Color.BLACK)
            .border(picker.color)
            .hideColor()
            .hidePipette()
            .onInput(hex -> picker.color.set(new Color(hex)))
            .onSync(widget -> {
                if (widget.isFocused())
                    return widget.getInput().trim().toUpperCase();

                return HexUtil.parseString(picker.color.getIntComponents());
            })
            .build(picker.group.hex::addWidget);

        if (!picker.transparent)
            this.input.getBuilder().opaque();

        this.reset = ButtonWidget.create()
            .icon(Icons.RED_UNDO)
            .tooltip(Lang.Button.RESET, 500L, TimeUnit.MILLISECONDS)
            .rightOf(this.input, 1)
            .disableIf(() -> picker.color.equals(picker.resetColor))
            .onPress(() -> picker.color.set(picker.resetColor))
            .build(picker.group.hex::addWidget);
    }
}
