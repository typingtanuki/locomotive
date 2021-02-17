package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Show a modal on uncaught exceptions
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e) -> DialogUtils.showErrorDialog(e));

        // Initialize fonts
        Font.loadFont(InstallerMain.class.getResource("/misaki_mincho.ttf").toExternalForm(), 10);

        // Initialize base classes
        I18n.init();
        CoreExecutor.init();
        primaryStage.setOnCloseRequest((e) -> NavigationCore.doExit(0));
        NavigationCore.init(primaryStage);

        // Start
        NavigationCore.start();
    }
}
