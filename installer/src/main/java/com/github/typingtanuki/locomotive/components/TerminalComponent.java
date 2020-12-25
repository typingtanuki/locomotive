package com.github.typingtanuki.locomotive.components;


import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.scene.control.TextArea;

/**
 * A component used to display terminal output
 */
public class TerminalComponent extends TextArea {
    public TerminalComponent() {
        setWidth(LayoutUtils.TERMINAL_WIDTH);
        setHeight(LayoutUtils.TERMINAL_HEIGHT);
    }

    /**
     * A more explicit way of expressing "no terminal"
     */
    public static TerminalComponent nullTerminal() {
        return null;
    }
}
