package com.github.typingtanuki.locomotive.utils;

import javafx.scene.Node;

public final class Style {
    public static String TITLE = "title";
    public static String SUB_TITLE = "subtitle";
    public static String HEADER = "header";

    private Style() {
        super();
    }

    public static <T extends Node> T withClass(T node, String... classes) {
        node.getStyleClass().addAll(classes);
        return node;
    }
}
