package com.github.typingtanuki.locomotive.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    private Stage stage;
    private Parent nextNode;

    public Navigator(Stage stage, Parent nextNode) {
        super();

        this.stage = stage;
        this.nextNode = nextNode;
    }

    public void goToNext() {
        stage.setScene(new Scene(
                nextNode,
                stage.getScene().getWidth(),
                stage.getScene().getHeight()));
        stage.show();
    }
}
