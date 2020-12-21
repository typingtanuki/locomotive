package com.github.typingtanuki.locomotive.controller.component;

import java.io.IOException;

public class StepState {
    private static final StepState SUCCESS_STATE = new StepState();
    private final IOException failure;

    public StepState(IOException e) {
        failure = e;
    }

    private StepState() {
        failure = null;
    }

    public static StepState success() {
        return SUCCESS_STATE;
    }

    public IOException getFailure() {
        return failure;
    }
}
