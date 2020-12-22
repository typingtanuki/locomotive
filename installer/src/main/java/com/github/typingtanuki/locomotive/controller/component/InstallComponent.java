package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.Future;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;

public abstract class InstallComponent extends BorderPane {
    private final Button action;
    private final VBox body;

    public InstallComponent(VBox body, Button action) {
        this.action = action;
        this.body = body;
        this.body.setPrefWidth(PAGE_WIDTH);

        setCenter(body);
        setBottom(action);
        action.setOnAction(e -> {
            System.out.println("Executing step");
            Future<StepState> state = CommonSettings.submitTask(this::execute);
// TBD
//            IOException failure = state.getFailure();
//            if (failure != null) {
//                System.out.println("Step failed");
//                showErrorDialog(failure);
//            } else {
//                System.out.println("Step succeeded");
//            }
        });

        setPrefWidth(PAGE_WIDTH);
    }

    public void setEnabled(boolean enabled) {
        this.action.setDisable(!enabled);
    }

    protected abstract StepState execute();

    protected VBox getBody() {
        return body;
    }
}
