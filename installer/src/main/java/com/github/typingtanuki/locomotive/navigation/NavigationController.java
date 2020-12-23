package com.github.typingtanuki.locomotive.navigation;

import com.github.typingtanuki.locomotive.pages.InstallerPage;
import com.github.typingtanuki.locomotive.pages.WelcomePage;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.setSize;

public class NavigationController extends Pane {
    public NavigationController() {
        setSize(this);
        setStyle("-fx-base:black");
    }

    public void welcome() {
        changePage(new WelcomePage());
    }

    public void changePage(InstallerPage page) {
        Platform.runLater(() -> {
            getChildren().clear();
            getChildren().add(page);
            page.attached();
        });
    }
}
