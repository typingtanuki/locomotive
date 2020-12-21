package com.github.typingtanuki.locomotive.controller.binary;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.controller.component.*;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class BinaryInstallController extends GridPane {
    private final Binary binary;
    private final Ppa ppa;
    private final VBox stepArea;

    public BinaryInstallController(Binary binary, Ppa ppa) {
        this.binary = binary;
        this.ppa = ppa;

        getChildren().clear();

        stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);
    }

    public void activated() {
        try {
            boolean canContinue = true;
            if (ppa != null && !ppa.isInstalled()) {
                // Need to install ppa first
                stepArea.getChildren().add(new Label("Must setup PPA"));
                stepArea.getChildren().add(new PpaInstall(ppa));
                canContinue = false;
            }

            InstallComponent sub;
            switch (binary.getType()) {
                case APT:
                    sub = new AptInstall(binary);
                    break;
                case GITHUB:
                    sub = new GithubInstall(binary);
                    break;
                case DOWNLOAD:
                    sub = new DownloadInstall(binary);
                    break;
                default:
                    throw new IllegalStateException("Unknown install type: " + binary.getType());
            }
            sub.setEnabled(canContinue);
            stepArea.getChildren().add(sub);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}