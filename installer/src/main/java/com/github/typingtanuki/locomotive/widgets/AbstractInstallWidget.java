package com.github.typingtanuki.locomotive.widgets;

import com.github.typingtanuki.locomotive.components.DownloadComponent;
import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
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
    private DownloadComponent download;


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
                    actionButtonName(),
                    FontAwesome.Glyph.DOWNLOAD,
                    this::install);
            getLayout().getChildren().add(installButton);
        });
    }

    private void hideInstallButton() {
        if (installButton == null) {
            return;
        }
        Button toRemove = installButton;
        installButton = null;
        Platform.runLater(() -> getLayout().getChildren().remove(toRemove));
    }

    private void hideTerminal() {
        if (terminal == null) {
            return;
        }
        TerminalComponent toRemove = terminal;
        terminal = null;
        Platform.runLater(() -> getLayout().getChildren().remove(toRemove));
    }

    private void hideDownload() {
        if (download == null) {
            return;
        }
        DownloadComponent toRemove = download;
        download = null;
        Platform.runLater(() -> getLayout().getChildren().remove(toRemove));
    }

    protected String actionButtonName() {
        return I18n.get("install");
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
        hideInstallButton();
        hideTerminal();
        hideDownload();
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

    protected DownloadComponent getDownload() {
        if (download == null) {
            download = new DownloadComponent();
            Platform.runLater(() -> getLayout().getChildren().add(download));
        }
        return download;
    }
}
