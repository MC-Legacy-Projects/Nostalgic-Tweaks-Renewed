package mod.legacyprojects.nostalgic.client.gui.screen.config.widget.list.controller;

import mod.legacyprojects.nostalgic.client.gui.screen.config.widget.SliderTweak;
import mod.legacyprojects.nostalgic.client.gui.widget.slider.SliderWidget;
import mod.legacyprojects.nostalgic.tweak.factory.TweakNumber;
import mod.legacyprojects.nostalgic.tweak.gui.TweakSlider;

public class NumberController
{
    /* Fields */

    private final Controller controller;
    private final TweakNumber<? extends Number> tweak;
    private final TweakSlider slider;

    /* Constructor */

    /**
     * Create a new number controller instance.
     *
     * @param controller The originating controller.
     * @param tweak      The number tweak this controller manages.
     */
    public NumberController(Controller controller, TweakNumber<? extends Number> tweak)
    {
        this.controller = controller;
        this.tweak = tweak;
        this.slider = tweak.getSlider();
    }

    /* Methods */

    /**
     * @return Create a new slider wrapper instance that will manage a number tweak.
     */
    public SliderWidget getWidget()
    {
        return SliderTweak.create(this.slider, this.tweak::setCacheValue, this.tweak::fromCache)
            .width(Controller.BUTTON_WIDTH)
            .leftOf(this.controller.getLeftOf(), 1)
            .disableIf(this.tweak::isNetworkLocked)
            .build();
    }
}
