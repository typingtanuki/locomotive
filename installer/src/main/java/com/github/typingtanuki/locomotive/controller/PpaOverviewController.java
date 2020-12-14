package com.github.typingtanuki.locomotive.controller;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PpaOverviewController extends GridPane {
    private static final Double WIDTH = 220d;

    public PpaOverviewController() {
        getChildren().clear();

        VBox stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);

        for (Ppa ppa : Ppas.ppas()) {
            stepArea.getChildren().add(createView(ppa));
        }
    }

    private Node createView(Ppa ppa) {
        Pane pane = new Pane();
        pane.setPrefWidth(WIDTH);
        pane.getStyleClass().add("nav-step");
        pane.getChildren().add(createTextNode(ppa));
        return pane;
    }

    private Node createTextNode(Ppa ppa) {
        VBox box = new VBox();
        box.setPrefWidth(WIDTH);

        String name;
        try {
            if (ppa.isInstalled()) {
                name = "✓ " + ppa.getName();
            } else {
                name = "- " + ppa.getName();
            }
        } catch (IOException e) {
            name = "✗ " + ppa.getName();
        }

        Label title = new Label(name);
        title.getStyleClass().add("nav-title");
        VBox.setMargin(title, new Insets(0, 0, 0, 15));
        box.getChildren().add(title);

        return box;
    }
}
