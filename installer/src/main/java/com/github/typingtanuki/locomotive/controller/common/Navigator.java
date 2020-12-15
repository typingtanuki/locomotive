package com.github.typingtanuki.locomotive.controller.common;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    private final Stage stage;
    private final Parent nextNode;

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

    public boolean hasNext() {
        return nextNode != null;
    }
}
