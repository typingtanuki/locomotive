package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.widgets.support.BinarySupportWidget;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.*;

public class SetupToolsPage extends InstallerPage {
    public SetupToolsPage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }


    @Override
    protected Node makeContent() {
        return vertical(
                new Label("Update system"), // TBD
                new Label("Screen calibration")  // TBD
        );
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("setuptools.title"),
                I18n.get("setuptools.description"),
                FontAwesome.Glyph.GAMEPAD);
    }

    @Override
    protected HBox makeFooter() {
        return horizontal(exitButton());
    }
}
