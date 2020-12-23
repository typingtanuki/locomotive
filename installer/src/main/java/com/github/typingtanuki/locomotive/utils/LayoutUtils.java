package com.github.typingtanuki.locomotive.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.controlsfx.glyphfont.FontAwesome;

public final class LayoutUtils {
    private static final double PAGE_WIDTH = 600;
    private static final double PAGE_HEIGHT = 400;
    private static final double SPACING = 10;
    private static final double MARGIN_SIZE = 20;

    private LayoutUtils() {
        super();
    }

    public static void setSize(Pane pane) {
        pane.setPrefSize(PAGE_WIDTH, PAGE_HEIGHT);
    }

    public static VBox vertical(Node... nodes) {
        VBox out = new VBox();
        out.getChildren().addAll(nodes);
        out.setAlignment(Pos.TOP_LEFT);
        out.setSpacing(SPACING);
        GridPane.setMargin(out, new Insets(MARGIN_SIZE));
        return out;
    }

    public static HBox horizontal(Node... nodes) {
        HBox out = new HBox();
        out.getChildren().addAll(nodes);
        out.setAlignment(Pos.CENTER_RIGHT);
        out.setSpacing(SPACING);
        GridPane.setMargin(out, new Insets(MARGIN_SIZE));
        return out;
    }

    public static <T extends Node> T setGrow(T node) {
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setVgrow(node, Priority.ALWAYS);
        GridPane.setMargin(node, new Insets(MARGIN_SIZE));
        return node;
    }

    public static Pane header(String title, String description, FontAwesome.Glyph icon) {
        BorderPane out = new BorderPane();
        out.setLeft(IconUtils.getIcon(icon));
        out.setCenter(vertical(new Label(title), new Label(description)));
        return out;
    }


    public static <T extends Node> T disabled(T element) {
        element.setDisable(true);
        return element;
    }
}
