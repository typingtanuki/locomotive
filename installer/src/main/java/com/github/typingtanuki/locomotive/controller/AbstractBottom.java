package com.github.typingtanuki.locomotive.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static com.github.typingtanuki.locomotive.controller.AbstractCenter.PAGE_WIDTH;

public class AbstractBottom extends GridPane {
    private static final Double HEIGHT = 50D;
    private static final Double RIGHT_BUTTON_AREA = 150D;
    private static final Double MINIMAL_BUTTON_WIDTH = 120D;
    private final HBox navigationButtonsArea;
    private final HBox rightButtonArea;

    public AbstractBottom() {
        super();
        getStyleClass().add("footer");
        setPrefSize(PAGE_WIDTH, HEIGHT);

        //main div holding the two groups of buttons
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHgrow(footer, Priority.ALWAYS);
        GridPane.setVgrow(footer, Priority.ALWAYS);
        getChildren().add(footer);

        //For the navigation buttons
        navigationButtonsArea = new HBox();
        navigationButtonsArea.setAlignment(Pos.CENTER_RIGHT);
        navigationButtonsArea.setSpacing(10);
        GridPane.setHgrow(navigationButtonsArea, Priority.ALWAYS);
        GridPane.setVgrow(navigationButtonsArea, Priority.ALWAYS);
        footer.getChildren().add(navigationButtonsArea);

        //Usually for the cancel button
        rightButtonArea = new HBox();
        rightButtonArea.setMinWidth(RIGHT_BUTTON_AREA);
        rightButtonArea.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setVgrow(navigationButtonsArea, Priority.ALWAYS);
        footer.getChildren().add(rightButtonArea);
    }

    public void addNavigationButton(Button navigationButton) {
        navigationButton.getStyleClass().add("navigation");
        navigationButton.setMinWidth(MINIMAL_BUTTON_WIDTH);
        navigationButtonsArea.getChildren().add(navigationButton);
    }

    public void addRightButton(Button rightButton) {
        rightButton.getStyleClass().add("navigation");
        rightButton.setMinWidth(MINIMAL_BUTTON_WIDTH);
        HBox.setMargin(rightButton, new Insets(0, 10, 0, 0));
        rightButtonArea.getChildren().add(rightButton);
    }

    public final Parent getView() {
        return this;
    }
}
