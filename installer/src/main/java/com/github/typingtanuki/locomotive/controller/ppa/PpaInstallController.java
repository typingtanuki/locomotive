package com.github.typingtanuki.locomotive.controller.ppa;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PpaInstallController extends GridPane {
    public PpaInstallController(Ppa ppa) {
        getChildren().clear();

        VBox stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);
    }
}
