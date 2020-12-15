package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.io.IOException;

public abstract class AbstractPackageStep implements Step {
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
        return binary.getDescription();
    }

    @Override
    public String title() {
        return binary.getTitle();
    }

    @Override
    public boolean execute() {
        try {
            if (ppa != null && !ppa.isInstalled()) {
                ppa.install();
            }
        } catch (IOException e) {
            System.err.println("Could not install ppa " + ppa.getTitle());
            e.printStackTrace(System.err);
            return false;
        }
        try {
            if (!binary.isInstalled()) {
                return binary.install();
            }
        } catch (IOException e) {
            System.err.println("Could not install package " + binary.getTitle());
            e.printStackTrace(System.err);
            return false;
        }
        return true;
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
}
