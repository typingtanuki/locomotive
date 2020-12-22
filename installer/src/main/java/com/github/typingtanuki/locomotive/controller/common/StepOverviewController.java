package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.steps.AbstractStepController;
import com.github.typingtanuki.locomotive.steps.Step;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.io.IOException;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;
import static com.github.typingtanuki.locomotive.utils.DialogUtils.showErrorDialog;

public class StepOverviewController extends AbstractOverviewController {

    public StepOverviewController(AbstractStepController stepController) {
        getChildren().clear();

        VBox stepArea = new VBox();
        stepArea.setSpacing(10D);
        GridPane.setHgrow(stepArea, Priority.ALWAYS);
        GridPane.setVgrow(stepArea, Priority.ALWAYS);
        GridPane.setMargin(stepArea, new Insets(10, 0, 0, 0));
        getChildren().add(stepArea);

        try {
            for (Step step : stepController.baseSteps()) {
                boolean isDone = step.isDone();
                if (!isDone) {
                    addSubPage(ControllerBuilder.forStep(step));
                }
                stepArea.getChildren().add(createView(step, isDone));
            }
        } catch (IOException e) {
            showErrorDialog(e);
        }
    }

    private Node createView(Step step, boolean isDone) {
        Pane pane = new Pane();
        pane.setPrefWidth(PAGE_WIDTH);
        pane.getStyleClass().add("nav-step");
        pane.getChildren().add(createTextNode(step, isDone));
        return pane;
    }

    @SuppressWarnings("RedundantCast")
    private Node createTextNode(Step step, boolean isDone) {
        HBox card = new HBox();
        VBox box = new VBox();
        box.setPrefWidth(PAGE_WIDTH);
        box.getStyleClass().add("install-box");

        Label title = new Label(CommonSettings.bundle(step.title(), (Object[]) step.titleArgs()));
        title.getStyleClass().add("nav-title");
        VBox.setMargin(title, new Insets(0, 0, 0, 15));
        box.getChildren().add(title);

        Label subtitle = new Label(CommonSettings.bundle(step.description(), (Object[]) step.descriptionArgs()));
        subtitle.getStyleClass().add("nav-subtitle");
        VBox.setMargin(subtitle, new Insets(0, 0, 0, 30));
        box.getChildren().add(subtitle);

        Glyph icon = IconUtils.getIcon(FontAwesome.Glyph.TIMES_CIRCLE, Color.ORANGE);
        if (isDone) {
            box.getStyleClass().add("installed");
            icon = IconUtils.getIcon(FontAwesome.Glyph.CHECK_CIRCLE, Color.GREEN);
        }

        card.getChildren().add(icon);
        card.getChildren().add(box);
        return card;
    }
}
