package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class BuildEssentialsStep extends AbstractPackageStep {
    public BuildEssentialsStep() {
        super(Binaries.buildEssentials());
    }
}
