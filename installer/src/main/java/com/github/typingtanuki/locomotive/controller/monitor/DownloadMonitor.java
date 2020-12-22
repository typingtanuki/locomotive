package com.github.typingtanuki.locomotive.controller.monitor;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class DownloadMonitor implements Monitor {
    private final ProgressBar progressBar = new ProgressBar();
    private final Label label = new Label();

    public DownloadMonitor(String message) {
        CommonSettings.runInGui(() -> label.setText(message));
    }

    public void setProgress(double progress) {
        CommonSettings.runInGui(() -> progressBar.setProgress(progress));
    }

    @Override
    public Node getLayout() {
        VBox out = new VBox();
        out.getChildren().add(label);
        out.getChildren().add(progressBar);
        return out;
    }
}