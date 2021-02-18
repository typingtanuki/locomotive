package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaInstallerWidget;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaKeyInstallerWidget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;

/**
 * The page for installing a new PPA
 */
public class AddPpaPage extends AbstractInstallerPage {
    private final Ppa ppa;

    public AddPpaPage(Ppa ppa, Deque<AbstractInstallerPage> nextPages) {
        super(nextPages);

        this.ppa = ppa;
    }

    @Override
    protected Node makeContent() {
        PpaInstallerWidget ppaInstallerWidget = new PpaInstallerWidget(
                ppa,
                this::installStarts,
                this::installFinished);
        PpaKeyInstallerWidget ppaKeyInstallerWidget = new PpaKeyInstallerWidget(
                ppa.getKey(),
                this::installStarts,
                ppaInstallerWidget::keyIsReady);
        return vertical(ppaKeyInstallerWidget, ppaInstallerWidget);
    }

    @Override
    protected Pane makeHeader() {
        return header(
                ppa.getTitle(),
                ppa.getDescription(),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(getNextPages().isEmpty());
    }

    public void installStarts() {
        Platform.runLater(() -> getNextButton().setDisable(true));
    }

    public void installFinished() {
        if (!getNextPages().isEmpty()) {
            Platform.runLater(() -> getNextButton().setDisable(false));
        }
    }
}
