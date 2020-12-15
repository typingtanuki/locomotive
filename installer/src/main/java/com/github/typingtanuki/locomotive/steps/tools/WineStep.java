package com.github.typingtanuki.locomotive.steps.tools;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class WineStep extends AbstractPackageStep {
    public WineStep() {
        super(Binaries.wine(), Ppas.wine());
    }
}
