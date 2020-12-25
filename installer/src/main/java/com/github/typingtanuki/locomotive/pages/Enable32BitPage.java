package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaInstallerWidget;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaKeyInstallerWidget;
import com.github.typingtanuki.locomotive.widgets.tools.Enable32BitWidget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;

public class Enable32BitPage extends InstallerPage {

    public Enable32BitPage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }

    @Override
    protected Node makeContent() {
        return vertical(new Enable32BitWidget(
                this::installStarts,
                this::installFinished
        ));
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("enable32bit.title"),
                I18n.get("enable32bit.description"),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(false);
    }


    public void installStarts() {
        Platform.runLater(() -> getNextButton().setDisable(true));
    }

    public void installFinished() {
        Platform.runLater(() -> getNextButton().setDisable(false));
    }
}
