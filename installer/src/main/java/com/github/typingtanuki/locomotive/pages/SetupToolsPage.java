package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.widgets.tools.AbstractToolWidget;
import com.github.typingtanuki.locomotive.widgets.tools.CalibratorToolWidget;
import com.github.typingtanuki.locomotive.widgets.tools.ScriptToolWidget;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.*;

public class SetupToolsPage extends InstallerPage {
    private final List<AbstractToolWidget> tools = new ArrayList<>();

    public SetupToolsPage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }

    @Override
    protected Node makeContent() {
        tools.clear();
        tools.add(new ScriptToolWidget(
                I18n.get("update.title"),
                I18n.get("update.description"),
                "scripts/update",
                this));
        tools.add(new CalibratorToolWidget(
                I18n.get("calibrate.title"),
                I18n.get("calibrate.description"),
                this));
        return vertical(tools.toArray(new AbstractToolWidget[0]));
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("setuptools.title"),
                I18n.get("setuptools.description"),
                FontAwesome.Glyph.WRENCH);
    }

    @Override
    protected HBox makeFooter() {
        return horizontal(exitButton());
    }

    public void toolStarted() {
        for (AbstractToolWidget tool : tools) {
            tool.started(true);
        }
    }

    public void toolFinished() {
        for (AbstractToolWidget tool : tools) {
            tool.started(false);
        }
    }
}
