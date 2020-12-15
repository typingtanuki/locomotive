package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.layout.BorderPane;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_HEIGHT;
import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;

public abstract class InstallerPage extends BorderPane {
    public InstallerPage() {
        setPrefSize(PAGE_WIDTH, PAGE_HEIGHT);
        getStylesheets().add(CommonSettings.css());
    }
}
