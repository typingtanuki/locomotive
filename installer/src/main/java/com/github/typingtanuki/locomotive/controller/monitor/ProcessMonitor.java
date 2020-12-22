package com.github.typingtanuki.locomotive.controller.monitor;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ProcessMonitor implements Monitor {
    private final TextArea textArea = new TextArea();
    private final Label label = new Label();

    public ProcessMonitor(String message) {
        CommonSettings.runInGui(() -> label.setText(message));
    }

    public void appendOutput(String output) {
        CommonSettings.runInGui(() -> textArea.appendText(output));
    }

    @Override
    public Node getLayout() {
        VBox out = new VBox();
        out.getChildren().add(label);
        out.getChildren().add(textArea);
        return out;
    }
}
