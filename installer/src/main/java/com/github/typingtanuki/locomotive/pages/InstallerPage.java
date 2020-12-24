package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.setSize;

public abstract class InstallerPage extends BorderPane {
    private final Deque<InstallerPage> nextPages;

    private Button nextButton;

    public InstallerPage(Deque<InstallerPage> nextPages) {
        this.nextPages = nextPages;
        setSize(this);
    }

    public void attached() {
        setTop(makeHeader());
        setCenter(makeContent());
        setBottom(makeFooter());
    }

    protected HBox basicFooter(boolean nextDisabled) {
        nextButton = button("next", FontAwesome.Glyph.ARROW_RIGHT, this::doNext);
        nextButton.setDisable(nextDisabled);

        return horizontal(
                nextButton,
                exitButton());
    }

    protected void doNext() {
        InstallerPage next = nextPages.pollFirst();
        NavigationCore.changePage(next);
    }

    public Button getNextButton() {
        return nextButton;
    }

    protected void clearPages() {
        nextPages.clear();
    }

    protected void addPage(InstallerPage page) {
        nextPages.add(page);
    }

    protected abstract Node makeContent();

    protected abstract Pane makeHeader();

    protected abstract Pane makeFooter();

    public Deque<InstallerPage> getNextPages() {
        return nextPages;
    }
}
