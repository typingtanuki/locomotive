package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.io.IOException;

public abstract class AbstractPackageStep extends AbstractStep {
    private final Binary binary;
    private final Ppa ppa;

    public AbstractPackageStep(Binary binary) {
        this(binary, null);
    }

    public AbstractPackageStep(Binary binary, Ppa ppa) {
        super();

        this.binary = binary;
        this.ppa = ppa;
    }

    @Override
    public String description() {
        return "step.package.description";
    }

    @Override
    public String[] descriptionArgs() {
        return new String[]{binary.getDescription()};
    }

    @Override
    public String title() {
        return "step.package.title";
    }

    @Override
    public String[] titleArgs() {
        return new String[]{binary.getTitle()};
    }


    @Override
    public void execute() throws IOException {
        if (ppa != null && !ppa.isInstalled()) {
            ppa.install();
        }
        if (!binary.isInstalled()) {
            binary.install();
        }
    }

    @Override
    public boolean isDone() {
        try {
            if (ppa != null && !ppa.isInstalled()) {
                return false;
            }
            return binary.isInstalled();
        } catch (IOException e) {
            return false;
        }
    }

    public Binary getBinary() {
        return binary;
    }

    public Ppa getPpa() {
        return ppa;
    }
}
