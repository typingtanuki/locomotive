package com.github.typingtanuki.locomotive.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AbstractCenter extends GridPane {
    /**
     * Fixed height of a page
     */
    public static final Double PAGE_HEIGHT = 450d;
    /**
     * Fixed width of a page
     */
    public static final Double PAGE_WIDTH = 680d;
    /**
     * Base margin size
     */
    public static final double MARGIN_SIZE = 20;

    public AbstractCenter() {
        super();
        setPrefSize(PAGE_WIDTH, PAGE_HEIGHT);
        setAlignment(Pos.CENTER);
        getStyleClass().add("center");
    }

    protected void setContent(Node content) {
        GridPane.setHgrow(content, Priority.ALWAYS);
        GridPane.setVgrow(content, Priority.ALWAYS);
        GridPane.setMargin(content, new Insets(MARGIN_SIZE));
        getChildren().add(content);
    }
}
