package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.controller.component.StepState;

import java.io.IOException;
import java.util.concurrent.Future;

public interface Step {
    String description();

    String title();

    String[] descriptionArgs();

    String[] titleArgs();

    Future<StepState> execute();

    boolean isDone() throws IOException;
}
