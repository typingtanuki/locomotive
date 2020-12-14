package com.github.typingtanuki.locomotive.controller;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.layout.BorderPane;

public class WelcomePageController extends BorderPane {
    public static final double WIDTH = 900d;
    public static final double HEIGHT = 550d;

    public WelcomePageController() {
        setPrefSize(WIDTH, HEIGHT);
        getStylesheets().add(CommonSettings.css());
    }
}
