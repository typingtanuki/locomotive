package com.github.typingtanuki.locomotive.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class WelcomeView extends GridPane {
    public WelcomeView() {
        Label welcome = new Label("nasflfaklnafnlsdafnld");
        welcome.setWrapText(true);
        welcome.getStyleClass().add("title");
        setAlignment(Pos.CENTER);
        getChildren().add(welcome);
    }
}
