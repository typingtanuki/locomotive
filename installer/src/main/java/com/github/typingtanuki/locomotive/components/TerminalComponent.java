package com.github.typingtanuki.locomotive.components;


import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * A component used to display terminal output
 */
public class TerminalComponent extends TextArea {
    private OutputStream input;

    public TerminalComponent() {
        super();

        getStyleClass().add("terminal");

        setWidth(LayoutUtils.TERMINAL_WIDTH);
        setHeight(LayoutUtils.TERMINAL_HEIGHT);
        setOnKeyTyped(this::keyTyped);
    }

    /**
     * A more explicit way of expressing "no terminal"
     */
    public static TerminalComponent nullTerminal() {
        return null;
    }

    public void setInput(OutputStream input) {
        this.input = input;
    }

    private void keyTyped(KeyEvent keyEvent) {
        if (input == null) {
            return;
        }

        try {
            input.write(keyEvent.getCharacter().getBytes(StandardCharsets.UTF_8));
            input.flush();
        } catch (IOException e) {
            if (e.getMessage().equals("Stream closed")) {
                return;
            }
            DialogUtils.showErrorDialog(e);
        }
    }
}
