package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

public final class ButtonUtils {
    private ButtonUtils() {
        super();
    }

    public static Button button(String key, FontAwesome.Glyph icon, Runnable action) {
        Button out = new Button(I18n.get(key), IconUtils.getIcon(icon));
        out.setOnAction((e) -> action.run());
        return out;
    }

    public static Button exitButton() {
        return button("exit", FontAwesome.Glyph.TIMES, () -> NavigationCore.doExit(0));
    }
}
