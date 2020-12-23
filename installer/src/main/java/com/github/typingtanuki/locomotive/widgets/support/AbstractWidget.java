package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

public class AbstractWidget extends BorderPane {
    public AbstractWidget(String title, String description) {
        super();
        setState(WidgetState.UNKNOWN);
        setCenter(vertical(
                withClass(new Label(title), CLASS_TITLE),
                withClass(new Label(description), CLASS_SUB_TITLE)
        ));
    }

    protected void setState(WidgetState state) {
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
}
