package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.controller.common.Navigator;
import com.github.typingtanuki.locomotive.controller.games.GamesOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.system.SystemOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.tools.ToolsOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.welcome.WelcomePageController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e) -> DialogUtils.showErrorDialog(e));

        CommonSettings.setStage(primaryStage);
        ExecutorService executor = new ThreadPoolExecutor(
                10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20));
        CommonSettings.setExecutor(executor);
        Navigator.addPage(new SystemOverviewPpaController());

        ToolsOverviewPpaController toolsOverviewPpaController = new ToolsOverviewPpaController();
        Navigator.addPage(toolsOverviewPpaController);

        GamesOverviewPpaController gamesOverviewPpaController = new GamesOverviewPpaController();
        Navigator.addPage(gamesOverviewPpaController);

        WelcomePageController welcomePageController = new WelcomePageController();
        welcomePageController.activated();

        primaryStage.setScene(new Scene(welcomePageController));
        primaryStage.setResizable(true);
        primaryStage.setTitle(CommonSettings.bundle("title"));
//        primaryStage.getIcons().add(CommonSettings.loadImage("/favicon.png"));

        primaryStage.show();
    }
}
