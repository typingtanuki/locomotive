package com.github.typingtanuki.locomotive.navigation;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.pages.InstallerPage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class NavigationCore {
    private static Stage primaryStage;
    private static NavigationController controller;


    private NavigationCore() {
        super();
    }

    public static void init(Stage primaryStage) {
        NavigationCore.primaryStage = primaryStage;
        controller = new NavigationController();

        primaryStage.setScene(new Scene(controller));
        primaryStage.setResizable(true);
        primaryStage.setTitle(I18n.get("title"));
    }

    public static void start() {
        controller.welcome();
        primaryStage.show();
    }

    public static void doExit(int status) {
        Platform.runLater(Platform::exit);
        System.exit(status);
    }

    public static void changePage(InstallerPage page) {
        controller.changePage(page);
    }
}
