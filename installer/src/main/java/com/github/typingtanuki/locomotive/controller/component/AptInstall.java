package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.controlsfx.glyphfont.FontAwesome;

public class AptInstall extends BorderPane {
    public AptInstall(Binary binary) {
        setCenter(new Label("Binary " + binary.getTitle()));
        setBottom(new Button("Install Binary with APT!", IconUtils.getIcon(FontAwesome.Glyph.GIFT)));
    }
}
