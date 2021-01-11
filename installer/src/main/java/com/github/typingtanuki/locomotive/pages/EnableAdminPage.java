package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.Installer;
import com.github.typingtanuki.locomotive.comm.InstallerServer;
import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.withClass;

/**
 * The title page
 */
public class EnableAdminPage extends AbstractInstallerPage {
    private Button button1;
    private Button button2;

    public EnableAdminPage() {
        super(new ArrayDeque<>());
    }

    @Override
    protected Node makeContent() {
        return withClass(new Label(I18n.get("admin.title")));
    }

    private void enableAdmin() {
        Platform.runLater(() -> {
            button1.setDisable(true);
            button2.setDisable(true);
        });
        startSecondProcess();
    }

    private void startSecondProcess() {
        CoreExecutor.init();
        EnableAdminPage admin = this;
        CoreExecutor.execute(() -> {
            String javaHome = System.getProperty("java.home");
            String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
            String classpath = System.getProperty("java.class.path");
            String className = Installer.class.getName();

            try {
                ProcessExec.sudoExec(
                        TerminalComponent.nullTerminal(),
                        javaBin,
                        "-cp",
                        classpath,
                        className,
                        String.valueOf(InstallerServer.getPort()));
            } catch (IOException e) {
                DialogUtils.showErrorDialog(e);
            }

            InstallerServer.waitHandshake();
            admin.doStart();
        });
    }

    @Override
    protected Pane makeHeader() {
        return new VBox();
    }

    @Override
    protected Pane makeFooter() {
        button1 = button(I18n.get("admin.enable"), FontAwesome.Glyph.USER_SECRET, this::enableAdmin);
        button2 = exitButton();
        return horizontal(button1, button2);
    }

    public void doStart() {
        NavigationCore.changePage(new SystemOverviewPage(getNextPages()));
    }
}
