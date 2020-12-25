package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;

public abstract class AbstractToolWidget extends AbstractWidget {
    private final SetupToolsPage setupToolsPage;

    private Button executeButton;
    private TerminalComponent terminal = null;

    public AbstractToolWidget(String title,
                              String description,
                              SetupToolsPage setupToolsPage) {
        super(title, description);

        this.setupToolsPage = setupToolsPage;

        setState(WidgetState.MISSING);
    }

    protected void init() {
        if (isInstalled()) {
            executeButton = button("execute", FontAwesome.Glyph.NAVICON, this::execute);
            getLayout().getChildren().add(executeButton);
            setState(WidgetState.INSTALLED);
        } else {
            executeButton = null;
        }
    }

    protected abstract boolean isInstalled();

    private void execute() {
        setupToolsPage.toolStarted();
        setState(WidgetState.PROCESSING);

        if (terminal == null) {
            Platform.runLater(() -> {
                terminal = new TerminalComponent();
                getLayout().getChildren().add(terminal);
                CoreExecutor.execute(this::start);
            });
        }
    }

    protected void finished() {
        TerminalComponent toRemove = terminal;
        terminal = null;
        Platform.runLater(() -> getLayout().getChildren().remove(toRemove));
        setupToolsPage.toolFinished();
        setState(WidgetState.INSTALLED);
    }

    protected abstract void start();

    public void started(boolean started) {
        if (executeButton == null) {
            return;
        }

        executeButton.setDisable(started);
    }

    protected TerminalComponent getTerminal() {
        return terminal;
    }
}
