package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.io.IOException;

public abstract class AbstractPpaStep implements Step {
    private final Ppa ppa;

    public AbstractPpaStep(Ppa ppa) {
        super();

        this.ppa = ppa;
    }

    @Override
    public String description() {
        return ppa.getDescription();
    }

    @Override
    public String title() {
        return "Repository: " + ppa.getTitle();
    }

    @Override
    public boolean execute() {
        try {
            ppa.install();
        } catch (IOException e) {
            System.err.println("Could not install ppa " + ppa.getTitle());
            e.printStackTrace(System.err);
            return false;
        }
        return true;
    }

    @Override
    public boolean isDone() {
        try {
            return ppa.isInstalled();
        } catch (IOException e) {
            return false;
        }
    }
}
