package com.github.typingtanuki.locomotive.steps;

public interface Step {
    String description();

    String title();

    String[] descriptionArgs();

    String[] titleArgs();

    boolean execute();

    boolean isDone();
}
