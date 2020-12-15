package com.github.typingtanuki.locomotive.utils;


import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

public final class IconUtils {
    private IconUtils() {
        super();
    }

    public static Glyph getIcon(FontAwesome.Glyph name) {
        return new Glyph("FontAwesome", name);
    }

    public static Glyph getIcon(FontAwesome.Glyph name, Color color) {
        Glyph glyph = getIcon(name);
        glyph.setColor(color);
        return glyph;
    }
}