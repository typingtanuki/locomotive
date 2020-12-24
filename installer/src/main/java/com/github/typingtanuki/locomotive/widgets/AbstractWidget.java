package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

public abstract class AbstractWidget extends BorderPane {
    private final VBox layout;
    private WidgetState state;

    public AbstractWidget(String title, String description) {
        super();
        setState(WidgetState.UNKNOWN);
        layout = vertical(
                withClass(new Label(title), CLASS_TITLE),
                withClass(new Label(description), CLASS_SUB_TITLE));
        setCenter(layout);
    }

    public WidgetState getState() {
        return state;
    }

    protected void setState(WidgetState state) {
        this.state = state;

        Platform.runLater(() -> {
            switch (state) {
                case INSTALLED:
                    setLeft(IconUtils.getIcon(FontAwesome.Glyph.CHECK));
                    break;
                case MISSING:
                    setLeft(IconUtils.getIcon(FontAwesome.Glyph.TIMES));
                    break;
                case UNKNOWN:
                default:
                    setLeft(IconUtils.getIcon(FontAwesome.Glyph.QUESTION));
                    break;
            }
        });
    }

    protected VBox getLayout() {
        return layout;
    }
}
