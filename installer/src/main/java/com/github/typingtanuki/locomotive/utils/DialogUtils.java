package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.application.Platform;
import org.controlsfx.dialog.ExceptionDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DialogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DialogUtils.class);

    private DialogUtils() {
        super();
    }

    public static void showErrorDialog(Throwable e) {
        LOGGER.error("Installer failed", e);
        Platform.runLater(() -> {
            GlitchLabel.cancelAll();
        });
        Platform.runLater(() -> {
            ExceptionDialog dialog = new ExceptionDialog(e);

            dialog.setResizable(true);
            dialog.showAndWait();

            NavigationCore.doExit(10);
        });
    }
}
