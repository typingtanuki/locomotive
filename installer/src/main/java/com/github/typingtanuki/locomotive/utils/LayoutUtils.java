package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import org.controlsfx.glyphfont.FontAwesome;

import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

public final class LayoutUtils {
    public static final double TERMINAL_WIDTH = 500;
    public static final double TERMINAL_HEIGHT = 100;
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

    public static Pane header(String title, String description, FontAwesome.Glyph icon) {
        BorderPane out = withClass(new BorderPane(), CLASS_HEADER);
        out.setLeft(withClass(IconUtils.getIcon(icon), CLASS_ICON));
        out.setCenter(vertical(
                withClass(new GlitchLabel(title, true), CLASS_TITLE),
                withClass(new GlitchLabel(description), CLASS_SUB_TITLE)));
        return out;
    }

    public static String css() {
        return LayoutUtils.class.getResource("/style.css").toExternalForm();
    }
}
