package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;

public class ToolWidget extends AbstractWidget {
    private final Binary binary;
    private final SetupToolsPage setupToolsPage;

    private final Button executeButton;
    private final Path script;

    private String fullCommand = null;
    private TerminalComponent terminal = null;

    public ToolWidget(String title,
                      String description,
                      String command,
                      Binary binary,
                      SetupToolsPage setupToolsPage) {
        super(title, description);

        this.binary = binary;
        this.setupToolsPage = setupToolsPage;

        setState(WidgetState.MISSING);
        if (command != null) {
            script = Paths.get(command).toAbsolutePath();
            if (Files.exists(script)) {
                fullCommand = script.toString();
            }
        } else {
            script = null;
        }

        if (binary != null && PackageTester.isBinaryOnPath(binary)) {
            fullCommand = binary.getBinary();
        }

        if (fullCommand != null) {
            executeButton = button("execute", FontAwesome.Glyph.NAVICON, this::execute);
            getLayout().getChildren().add(executeButton);
            setState(WidgetState.INSTALLED);
        } else {
            executeButton = null;
        }
    }

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

    private void start() {
        try {
            ProcessExec processExec = ProcessExec.sudoExec(terminal, fullCommand);
            processExec.checkSuccess();
            TerminalComponent toRemove = terminal;
            terminal = null;
            Platform.runLater(() -> getLayout().getChildren().remove(toRemove));
            setupToolsPage.toolFinished();
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    public void started(boolean started) {
        if (executeButton == null) {
            return;
        }

        executeButton.setDisable(started);
    }
}
