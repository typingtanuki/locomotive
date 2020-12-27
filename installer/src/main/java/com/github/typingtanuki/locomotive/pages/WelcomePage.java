package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayDeque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.CLASS_WELCOME;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.withClass;

public class WelcomePage extends InstallerPage {
    public WelcomePage() {
        super(new ArrayDeque<>());
    }

    @Override
    protected Node makeContent() {
        return withClass(new GlitchLabel(I18n.get("welcome")), CLASS_WELCOME);
    }

    @Override
    protected Pane makeHeader() {
        return new VBox();
    }

    @Override
    protected Pane makeFooter() {
        return horizontal(
                button(I18n.get("start"), FontAwesome.Glyph.CHECK, this::doStart),
                exitButton());
    }

    public void doStart() {
        NavigationCore.changePage(new SystemOverviewPage(getNextPages()));
    }
}
