package com.github.typingtanuki.locomotive.components;


import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.scene.control.TextArea;

public class TerminalComponent extends TextArea {
    public TerminalComponent() {
        setWidth(LayoutUtils.TERMINAL_WIDTH);
        setHeight(LayoutUtils.TERMINAL_HEIGHT);
    }

    public static TerminalComponent nullTerminal() {
        return null;
    }
}
