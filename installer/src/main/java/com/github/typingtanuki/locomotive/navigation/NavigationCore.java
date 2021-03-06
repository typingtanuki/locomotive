package com.github.typingtanuki.locomotive.navigation;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.pages.AbstractInstallerPage;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class NavigationCore {
    private static Stage primaryStage;
    private static NavigationController controller;


    private NavigationCore() {
        super();
    }

    /**
     * Prepare the controller and the javaFX scene
     */
    public static void init(Stage primaryStage) {
        NavigationCore.primaryStage = primaryStage;
        controller = new NavigationController();

        primaryStage.setScene(new Scene(controller));
        primaryStage.setResizable(true);
        primaryStage.setTitle(I18n.get("title"));
    }

    /**
     * Show the application
     */
    public static void start() {
        controller.welcome();
        primaryStage.show();
    }

    /**
     * Stop the application
     *
     * @param status The exit code
     */
    public static void doExit(int status) {
        Platform.runLater(() -> {
            GlitchLabel.cancelAll();
            AbstractWidget.cancelAll();
        });
        Platform.runLater(() -> exit(status));
    }

    /**
     * Used to change page
     *
     * @param page The page to display
     */
    public static void changePage(AbstractInstallerPage page) {
        controller.changePage(page);
    }

    private static void exit(int signal) {
        CoreExecutor.exit();
        Platform.exit();
        System.exit(signal);
    }
}
