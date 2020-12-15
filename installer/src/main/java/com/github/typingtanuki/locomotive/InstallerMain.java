package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.controller.welcome.WelcomePageController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ResourceBundle bundle = CommonSettings.bundle();

        primaryStage.setScene(new Scene(new WelcomePageController(primaryStage)));
        primaryStage.setResizable(false);
        primaryStage.setTitle(bundle.getString("title"));
//        primaryStage.getIcons().add(CommonSettings.loadImage("/favicon.png"));

        primaryStage.show();
    }
}
