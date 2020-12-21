package com.github.typingtanuki.locomotive.controller.ppa;

import com.github.typingtanuki.locomotive.controller.component.PpaInstall;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PpaInstallController extends BorderPane {
    private final VBox stepArea;
    private final Ppa ppa;

    public PpaInstallController(Ppa ppa) {
        this.ppa = ppa;

        getChildren().clear();

        stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);
    }

    public void activated() {
        stepArea.getChildren().add(new Label("Setup PPA ?"));
        stepArea.getChildren().add(new PpaInstall(ppa));
    }
}
