package com.github.typingtanuki.locomotive.steps.tools;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class AntimicroxStep extends AbstractPackageStep {
    public AntimicroxStep() {
        super(Binaries.antimicroX());
    }
}
