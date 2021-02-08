package com.github.typingtanuki.locomotive.utils;


import javafx.scene.effect.Glow;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

public final class IconUtils {
    private IconUtils() {
        super();
    }

    public static Glyph getIcon(FontAwesome.Glyph name) {
        Glyph glyph= new Glyph("FontAwesome", name);
        glyph.setEffect(new Glow(3));
        return glyph;
    }
}