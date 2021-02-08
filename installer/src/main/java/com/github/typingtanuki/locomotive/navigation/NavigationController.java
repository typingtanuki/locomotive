package com.github.typingtanuki.locomotive.navigation;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.pages.AbstractInstallerPage;
import com.github.typingtanuki.locomotive.pages.WelcomePage;
import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.setSize;

/**
 * The root element of all pages
 */
public class NavigationController extends StackPane {
    private final Pane overlay;

    public NavigationController() {
        setSize(this);
        setStyle("-fx-base:black");
        getStylesheets().add(LayoutUtils.css());

        overlay = new Pane();
        overlay.getStyleClass().add("overlay");
        overlay.setMouseTransparent(true);
        LayoutUtils.setSize(overlay);
    }

    public void welcome() {
        changePage(new WelcomePage());
    }

    public void changePage(AbstractInstallerPage page) {
        Platform.runLater(() -> {
            GlitchLabel.cancelAll();
            getChildren().clear();
            getChildren().add(page);
            getChildren().add(overlay);
            page.attached();
        });
    }
}
