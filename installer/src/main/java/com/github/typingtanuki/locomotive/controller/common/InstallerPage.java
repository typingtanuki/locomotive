package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_HEIGHT;
import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;

public abstract class InstallerPage extends BorderPane {
    private final Footer footer;

    public InstallerPage() {
        super();

        this.footer = new Footer();
        setPrefSize(PAGE_WIDTH, PAGE_HEIGHT);
        setStyle("-fx-base:black");
        getStylesheets().add(CommonSettings.css());
    }

    public void activated() {
        this.footer.activated();
    }

    public Footer getFooter() {
        return footer;
    }
}
