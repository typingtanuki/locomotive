package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.steps.Step;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BasicStepInstallController extends GridPane {
    public BasicStepInstallController(Step step) {
        getChildren().clear();

        VBox stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);
    }
}
