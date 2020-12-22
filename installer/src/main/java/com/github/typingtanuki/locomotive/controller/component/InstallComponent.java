package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static com.github.typingtanuki.locomotive.controller.common.AbstractCenter.PAGE_WIDTH;

public abstract class InstallComponent extends BorderPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstallComponent.class);

    private final Button action;
    private final VBox body;

    public InstallComponent(VBox body, Button action) {
        this.action = action;
        this.body = body;
        this.body.setPrefWidth(PAGE_WIDTH);

        setCenter(body);
        setBottom(action);
        action.setOnAction(e -> {
            LOGGER.debug("Executing step {}", this.getClass().getSimpleName());
            CompletableFuture<StepState> state = CommonSettings.submitTask(this::execute);
            state = CommonSettings.chainTask(state, this::checkSuccess);
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

    private StepState checkSuccess(StepState previous) {
        if (previous.getFailure() != null) {
            throw new IllegalStateException("Step failed", previous.getFailure());
        }
        return StepState.success();
    }

    public void setEnabled(boolean enabled) {
        this.action.setDisable(!enabled);
    }

    protected abstract StepState execute();

    protected VBox getBody() {
        return body;
    }
}
