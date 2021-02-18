package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.util.ArrayList;
import java.util.List;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

/**
 * Base class for a widget
 * <p>
 * Has title, description and an icon
 */
public abstract class AbstractWidget extends BorderPane {
    private static final List<Timeline> TIMELINES = new ArrayList<>();

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
                withClass(new GlitchLabel(title), CLASS_WIDGET_TITLE),
                withClass(new GlitchLabel(description), CLASS_WIDGET_SUB_TITLE));
        setCenter(layout);
    }

    public static void cancelAll() {
        synchronized (TIMELINES) {
            for (Timeline timeline : TIMELINES) {
                timeline.stop();
            }
            TIMELINES.clear();
        }
    }

    public WidgetState getState() {
        return state;
    }

    /**
     * Update the state of the widget, update icon, ...
     */
    protected void setState(WidgetState state) {
        this.state = state;

        Platform.runLater(() -> {

            if (this.icon != null) {
                withClass(this.icon, CLASS_ICON);
                withClass(this, CLASS_WIDGET, state.name());
                setLeft(this.icon);
                return;
            }

            boolean spin = false;
            Glyph icon;
            switch (state) {
                case INSTALLED:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.CHECK_CIRCLE);
                    break;
                case MISSING:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.TIMES_CIRCLE);
                    break;
                case PROCESSING:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.GEAR);
                    spin = true;
                    break;
                case FAILED:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.EXCLAMATION_TRIANGLE);
                    break;
                case UNKNOWN:
                default:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.QUESTION);
                    break;
            }

            if (spin) {
                // Make spinners spin
                Rotate rotation = new Rotate();
                rotation.setPivotX(21.5);
                rotation.setPivotY(21.5);
                icon.getTransforms().add(rotation);

                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), 360)));
                timeline.setCycleCount(Animation.INDEFINITE);
                synchronized (TIMELINES) {
                    TIMELINES.add(timeline);
                }
                timeline.play();
            }

            withClass(icon, CLASS_ICON, state.name());
            withClass(this, CLASS_WIDGET, state.name());
            setLeft(icon);
        });
    }

    protected void setIcon(Glyph icon) {
        this.icon = icon;
    }

    protected VBox getLayout() {
        return layout;
    }
}
