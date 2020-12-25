package com.github.typingtanuki.locomotive.utils;

import javafx.scene.Node;

public final class StyleUtils {
    public static final String CLASS_TITLE = "title";
    public static final String CLASS_WIDGET_TITLE = "widget-title";
    public static final String CLASS_SUB_TITLE = "subtitle";
    public static final String CLASS_WIDGET_SUB_TITLE = "widget-subtitle";
    public static final String CLASS_WELCOME = "welcome";

    private StyleUtils() {
        super();
    }

    public static <T extends Node> T withClass(T node, String... classes) {
        node.getStyleClass().addAll(classes);
        return node;
    }
}
