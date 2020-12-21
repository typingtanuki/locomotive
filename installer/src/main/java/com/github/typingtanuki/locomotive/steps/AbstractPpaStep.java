package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.io.IOException;

public abstract class AbstractPpaStep extends AbstractStep {
    private final Ppa ppa;

    public AbstractPpaStep(Ppa ppa) {
        super();

        this.ppa = ppa;
    }


    @Override
    public String description() {
        return "step.ppa.title";
    }

    @Override
    public String[] descriptionArgs() {
        return new String[]{ppa.getTitle()};
    }

    @Override
    public String title() {
        return "step.ppa.description";
    }

    @Override
    public String[] titleArgs() {
        return new String[]{ppa.getTitle()};
    }

    @Override
    public void execute() throws IOException {
        ppa.install();
    }

    @Override
    public boolean isDone() throws IOException {
        return ppa.isInstalled();
    }

    public Ppa getPpa() {
        return ppa;
    }
}
