package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;

public class SystemOverviewPage extends InstallerPage {
    @Override
    protected Node makeContent() {
        return null;
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("overview.title"),
                I18n.get("overview.description"),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        return horizontal(
                button("next", FontAwesome.Glyph.ARROW_RIGHT, this::doNext),
                exitButton());
    }

    private void doNext() {
        NavigationCore.changePage(new SystemOverviewPage());
    }
}
