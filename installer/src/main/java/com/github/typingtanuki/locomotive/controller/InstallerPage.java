package com.github.typingtanuki.locomotive.controller;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.layout.BorderPane;

import static com.github.typingtanuki.locomotive.controller.AbstractCenter.PAGE_HEIGHT;
import static com.github.typingtanuki.locomotive.controller.AbstractCenter.PAGE_WIDTH;

public abstract class InstallerPage extends BorderPane {
    public InstallerPage() {
        setPrefSize(PAGE_WIDTH, PAGE_HEIGHT);
        getStylesheets().add(CommonSettings.css());
    }
}
