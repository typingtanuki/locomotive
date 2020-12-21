package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.controller.common.Navigator;
import com.github.typingtanuki.locomotive.controller.games.GamesOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.system.SystemOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.tools.ToolsOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.welcome.WelcomePageController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CommonSettings.setStage(primaryStage);
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
