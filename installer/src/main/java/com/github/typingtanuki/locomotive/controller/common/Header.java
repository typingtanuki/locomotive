package com.github.typingtanuki.locomotive.controller.common;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;

public class Header extends GridPane {
    public Header(String title, String description) {
        super();
        setPrefWidth(PAGE_WIDTH);
        VBox vbox = new VBox();
        vbox.setPrefWidth(PAGE_WIDTH);
        vbox.getChildren().add(new Label(title));
        vbox.getChildren().add(new Label(description));
        getChildren().add(vbox);
    }
}
