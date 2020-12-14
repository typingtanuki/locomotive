package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.controller.WelcomePageController;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class InstallerMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Ppas.checkPpas();
            Binaries.checkBinaries();
        } catch (IOException e) {
            System.err.println("Failed checking state");
            e.printStackTrace(System.err);
        }

        ResourceBundle bundle = CommonSettings.bundle();

        primaryStage.setScene(new Scene(new WelcomePageController(primaryStage)));
        primaryStage.setResizable(false);
        primaryStage.setTitle(bundle.getString("title"));
//        primaryStage.getIcons().add(CommonSettings.loadImage("/favicon.png"));

        primaryStage.show();
    }
}
