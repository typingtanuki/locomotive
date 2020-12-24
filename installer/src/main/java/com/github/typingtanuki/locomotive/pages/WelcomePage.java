package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayDeque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;

public class WelcomePage extends InstallerPage {
    public WelcomePage() {
        super(new ArrayDeque<>());
    }

    @Override
    protected Node makeContent() {
        return new Label(I18n.get("welcome"));
    }

    @Override
    protected Pane makeHeader() {
        return new VBox();
    }

    @Override
    protected Pane makeFooter() {
        return horizontal(
                button("start", FontAwesome.Glyph.CHECK, this::doStart),
                exitButton());
    }

    public void doStart() {
        NavigationCore.changePage(new SystemOverviewPage(getNextPages()));
    }
}
