package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

public abstract class AbstractWidget extends BorderPane {
    private final VBox layout;
    private WidgetState state;

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

    protected void setState(WidgetState state) {
        this.state = state;

        Platform.runLater(() -> {
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
                    break;
                case UNKNOWN:
                default:
                    icon = IconUtils.getIcon(FontAwesome.Glyph.QUESTION);
                    break;
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
