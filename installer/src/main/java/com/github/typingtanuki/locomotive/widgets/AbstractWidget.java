package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;
import static javafx.animation.Animation.INDEFINITE;

/**
 * Base class for a widget
 * <p>
 * Has title, description and an icon
 */
public abstract class AbstractWidget extends BorderPane {
    /**
     * The body of the widget
     */
    private final VBox layout;
    /**
     * The state of the widget
     */
    private WidgetState state;
    /**
     * Override the status icon
     */
    private Glyph icon;

    public AbstractWidget(String title, String description) {
        super();
        setState(WidgetState.PROCESSING);
        layout = vertical(
                withClass(new Label(title), CLASS_WIDGET_TITLE),
                withClass(new Label(description), CLASS_WIDGET_SUB_TITLE));
        setCenter(layout);
    }

    public WidgetState getState() {
        return state;
    }

    protected void setIcon(Glyph icon) {
        this.icon = icon;
    }

    /**
     * Update the state of the widget, update icon, ...
     */
    protected void setState(WidgetState state) {
        this.state = state;

        Platform.runLater(() -> {

            if (this.icon != null) {
                withClass(this.icon, "icon");
                withClass(this, "widget", state.name());
                setLeft(this.icon);
                return;
            }

            boolean spin = false;
            Glyph icon;
            switch (state) {
                case INSTALLED:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.CHECK);
                    break;
                case MISSING:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.TIMES);
                    break;
                case PROCESSING:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.SPINNER);
                    spin = true;
                    break;
                case UNKNOWN:
                default:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.QUESTION);
                    break;
            }

            if (spin) {
                // Make spinners spin
                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(360);
                rotate.setCycleCount(INDEFINITE);
                rotate.setDuration(Duration.millis(1000));
                rotate.setNode(icon);
                rotate.setDelay(Duration.ZERO);
                rotate.play();
            }

            withClass(icon, "icon", state.name());
            withClass(this, "widget", state.name());
            setLeft(icon);
        });
    }

    protected VBox getLayout() {
        return layout;
    }
}
