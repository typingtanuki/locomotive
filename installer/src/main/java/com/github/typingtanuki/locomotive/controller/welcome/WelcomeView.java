package com.github.typingtanuki.locomotive.controller.welcome;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static com.github.typingtanuki.locomotive.utils.Style.withClass;

public class WelcomeView extends GridPane {
    public WelcomeView() {
        Label welcome = withClass(new Label(CommonSettings.bundle("welcome")), Style.TITLE);

        welcome.setWrapText(true);
        setAlignment(Pos.CENTER);
        getChildren().add(welcome);
    }
}
