package com.github.typingtanuki.locomotive.steps;

import java.util.Locale;

public abstract class AbstractStep implements Step {
    @Override
    public String description() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".title";
    }

    @Override
    public String title() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".description";
    }

    @Override
    public String[] descriptionArgs() {
        return new String[0];
    }

    @Override
    public String[] titleArgs() {
        return new String[0];
    }
}
