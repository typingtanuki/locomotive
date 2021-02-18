package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.pages.MenuPage;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import org.controlsfx.glyphfont.FontAwesome;

public final class ButtonUtils {
    private ButtonUtils() {
        super();
    }

    public static Button button(String key, FontAwesome.Glyph icon, Runnable action) {
        Button out = new Button(key, IconUtils.getIcon(icon));
        out.setOnAction((e) -> action.run());
        out.setEffect(new Glow(1.5));
        return out;
    }

    public static Button exitButton() {
        return button(I18n.get("exit"), FontAwesome.Glyph.TIMES, () -> NavigationCore.doExit(0));
    }

    public static Button menuButton() {
        return button(I18n.get("menu"), FontAwesome.Glyph.LIST_ALT, () -> NavigationCore.changePage(new MenuPage()));
    }
}
