package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
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
import static com.github.typingtanuki.locomotive.utils.StyleUtils.CLASS_UNDERLAY;

/**
 * The base page
 */
public abstract class AbstractInstallerPage extends BorderPane {
    private final Deque<AbstractInstallerPage> nextPages;

    private Button nextButton;

    public AbstractInstallerPage(Deque<AbstractInstallerPage> nextPages) {
        this.nextPages = nextPages;
        setSize(this);
        getStyleClass().add(CLASS_UNDERLAY);
    }

    public void attached() {
        setTop(makeHeader());
        setCenter(makeContent());
        setBottom(makeFooter());
    }

    /**
     * A default footer with next/exit
     */
    protected HBox basicFooter(boolean nextDisabled) {
        nextButton = button(I18n.get("next"), FontAwesome.Glyph.ARROW_RIGHT, this::doNext);
        nextButton.setDisable(nextDisabled);

        return horizontal(
                nextButton,
                exitButton());
    }

    /**
     * Go to next page
     */
    protected void doNext() {
        AbstractInstallerPage next = nextPages.pollFirst();
        NavigationCore.changePage(next);
    }

    public Button getNextButton() {
        return nextButton;
    }

    protected void clearPages() {
        nextPages.clear();
    }

    protected void addPage(AbstractInstallerPage page) {
        nextPages.add(page);
    }

    protected abstract Node makeContent();

    protected abstract Pane makeHeader();

    protected abstract Pane makeFooter();

    public Deque<AbstractInstallerPage> getNextPages() {
        return nextPages;
    }
}
