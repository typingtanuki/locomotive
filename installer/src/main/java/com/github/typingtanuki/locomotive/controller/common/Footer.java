package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

public class Footer extends AbstractBottom {
    private final Button start;
    private final Button quit;
    private final Button next;

    public Footer() {
        super();

        start = new Button("Start", IconUtils.getIcon(FontAwesome.Glyph.ARROW_RIGHT));
        start.setOnAction(e -> Navigator.goToNext());

        next = new Button("Next", IconUtils.getIcon(FontAwesome.Glyph.ARROW_RIGHT));
        next.setOnAction(e -> Navigator.goToNext());

        quit = new Button("Exit", IconUtils.getIcon(FontAwesome.Glyph.TIMES));
        quit.setOnAction(e -> Platform.exit());

    }

    public void activated() {
        clearButtons();
        if (Navigator.hasNext()) {
            if (Navigator.hasPrevious()) {
                addNavigationButton(next);
            } else {
                addNavigationButton(start);
            }
        }
        addRightButton(quit);
    }
}
