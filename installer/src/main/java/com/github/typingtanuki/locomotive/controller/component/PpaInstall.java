package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.controlsfx.glyphfont.FontAwesome;

public class PpaInstall extends BorderPane {
    public PpaInstall(Ppa ppa) {
        setCenter(new Label("PPA " + ppa.getTitle()));
        setBottom(new Button("Install PPA!", IconUtils.getIcon(FontAwesome.Glyph.INBOX)));
    }
}
