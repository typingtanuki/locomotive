package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

public class MenuEntryWidget extends AbstractWidget {
    public MenuEntryWidget(FontAwesome.Glyph icon, String i18nRoot, Runnable action) {
        super(I18n.get(i18nRoot + ".title"), I18n.get(i18nRoot + ".description"));
        setState(WidgetState.UNKNOWN);

        setLeft(withClass(IconUtils.getIcon(icon), CLASS_ICON));
        withClass(this, CLASS_WIDGET);

        getLayout().getChildren().add(button(I18n.get(i18nRoot + ".action"), icon, action));
    }
}
