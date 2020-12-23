package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;

public class AddPpaPage extends InstallerPage {
    private final String ppa;
    private final Deque<InstallerPage> nextPages;
    private Button nextButton;

    public AddPpaPage(String ppa, Deque<InstallerPage> nextPages) {
        super();

        this.ppa = ppa;
        this.nextPages = nextPages;
    }

    @Override
    protected Node makeContent() {
        return null;
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("setupPpa.title"),
                I18n.get("setupPpa.description"),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        nextButton = button("next", FontAwesome.Glyph.ARROW_RIGHT, this::doNext);
        return horizontal(
                nextButton,
                exitButton());
    }

    private void doNext() {
        InstallerPage next = nextPages.pollFirst();
        NavigationCore.changePage(next);
    }
}
