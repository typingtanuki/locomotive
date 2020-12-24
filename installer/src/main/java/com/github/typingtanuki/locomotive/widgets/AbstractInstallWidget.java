package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;

public abstract class AbstractInstallWidget extends AbstractWidget {
    private final Runnable installStarts;
    private final Runnable installFinished;
    private Button installButton;

    private TerminalComponent terminal;


    public AbstractInstallWidget(String title,
                                 String description,
                                 Runnable installStarts,
                                 Runnable installFinished) {
        super(title, description);

        this.installStarts = installStarts;
        this.installFinished = installFinished;
    }

    protected void showInstallButton() {
        Platform.runLater(() -> {
            installButton = button(
                    "install",
                    FontAwesome.Glyph.DOWNLOAD,
                    this::install);
            getLayout().getChildren().add(installButton);
        });
    }

    @Override
    protected void setState(WidgetState state) {
        boolean finished = getState() != WidgetState.INSTALLED &&
                state == WidgetState.INSTALLED;

        super.setState(state);

        if (finished) {
            installIsDone();
        }
    }

    protected void installIsDone() {
        setState(WidgetState.INSTALLED);
        CoreExecutor.execute(this.installFinished);
        if (installButton != null) {
            installButton.setDisable(true);
        }
    }

    private void install() {
        setState(WidgetState.PROCESSING);
        if (installButton != null) {
            installButton.setDisable(true);
        }
        CoreExecutor.execute(this.installStarts);
        CoreExecutor.execute(this::doInstall);
    }

    protected abstract void doInstall();

    protected TerminalComponent getTerminal() {
        if (terminal == null) {
            terminal = new TerminalComponent();
            Platform.runLater(() -> getLayout().getChildren().add(terminal));
        }
        return terminal;
    }
}
