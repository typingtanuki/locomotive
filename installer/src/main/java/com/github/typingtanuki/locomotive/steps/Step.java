package com.github.typingtanuki.locomotive.steps;

public interface Step {
    String description();

    String title();

    boolean execute();

    boolean isDone();
}
