package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

public class FooterController extends AbstractBottom {
    public FooterController(Navigator navigator) {
        super();

        if(navigator.hasNext()) {
            Button start = new Button("Start", IconUtils.getIcon(FontAwesome.Glyph.ARROW_RIGHT));
            start.setOnAction(e -> navigator.goToNext());
            addNavigationButton(start);
        }

        Button quit = new Button("Exit", IconUtils.getIcon(FontAwesome.Glyph.TIMES));
        quit.setOnAction(e -> Platform.exit());

        addRightButton(quit);
    }
}
