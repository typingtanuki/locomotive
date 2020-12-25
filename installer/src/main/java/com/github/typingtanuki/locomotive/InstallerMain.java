package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e) -> DialogUtils.showErrorDialog(e));

        I18n.init();
        CoreExecutor.init();

        NavigationCore.init(primaryStage);
        NavigationCore.start();
    }
}
