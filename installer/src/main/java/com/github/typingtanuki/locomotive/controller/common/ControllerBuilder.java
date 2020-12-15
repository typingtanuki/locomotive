package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.controller.binary.BinaryController;
import com.github.typingtanuki.locomotive.controller.ppa.PpaController;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;
import com.github.typingtanuki.locomotive.steps.AbstractPpaStep;
import com.github.typingtanuki.locomotive.steps.Step;

public final class ControllerBuilder {
    private ControllerBuilder() {
        super();
    }

    public static InstallerPage forStep(Step step) {
        if (step instanceof AbstractPpaStep) {
            return new PpaController(((AbstractPpaStep) step).getPpa());
        }
        if (step instanceof AbstractPackageStep) {
            return new BinaryController(((AbstractPackageStep) step).getBinary());
        }
        return new BasicStepController(step);
    }
}
