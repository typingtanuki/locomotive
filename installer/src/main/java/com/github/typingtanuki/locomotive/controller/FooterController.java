package com.github.typingtanuki.locomotive.controller;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class FooterController extends AbstractBottom {
    public FooterController(Navigator navigator) {
        super();
        Button start = new Button("Start");
        start.setOnAction(e -> navigator.goToNext());
        Button quit = new Button("Exit");
        quit.setOnAction(e -> Platform.exit());

        addNavigationButton(start);
        addRightButton(quit);
    }
}
