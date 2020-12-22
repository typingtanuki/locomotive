package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;
import static com.github.typingtanuki.locomotive.utils.Style.withClass;

public class Header extends GridPane {
    @SuppressWarnings("RedundantCast")
    public Header(String title, String[] titleArgs,
                  String description, String[] descriptionArgs) {
        super();
        setPrefWidth(PAGE_WIDTH);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(PAGE_WIDTH);
        if (!title.isBlank()) {
            vbox.getChildren().add(withClass(
                    new Label(CommonSettings.bundle(title, (Object[]) titleArgs)),
                    Style.TITLE,
                    Style.HEADER));
        }
        if (!description.isBlank()) {
            vbox.getChildren().add(withClass(
                    new Label(CommonSettings.bundle(description, (Object[]) descriptionArgs)),
                    Style.SUB_TITLE,
                    Style.HEADER));
        }
        getChildren().add(vbox);
    }
}
