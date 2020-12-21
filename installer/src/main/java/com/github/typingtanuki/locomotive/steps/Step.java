package com.github.typingtanuki.locomotive.steps;

import java.io.IOException;

public interface Step {
    String description();

    String title();

    String[] descriptionArgs();

    String[] titleArgs();

    void execute() throws IOException;

    boolean isDone() throws IOException;
}
