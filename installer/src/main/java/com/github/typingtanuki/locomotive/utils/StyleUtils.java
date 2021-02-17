package com.github.typingtanuki.locomotive.utils;

import javafx.scene.Node;

public final class StyleUtils {
    public static final String CLASS_HEADER = "header";
    public static final String CLASS_ICON = "icon";
    public static final String CLASS_SUB_TITLE = "subtitle";
    public static final String CLASS_TITLE = "title";
    public static final String CLASS_WELCOME = "welcome";
    public static final String CLASS_UNDERLAY = "underlay";
    public static final String CLASS_OVERLAY = "overlay";
    public static final String CLASS_WIDGET = "widget";
    public static final String CLASS_WIDGET_SUB_TITLE = "widget-subtitle";
    public static final String CLASS_WIDGET_TITLE = "widget-title";

    private StyleUtils() {
        super();
    }

    public static <T extends Node> T withClass(T node, String... classes) {
        node.getStyleClass().addAll(classes);
        return node;
    }
}
