package com.github.typingtanuki.locomotive.steps.tools;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class KodiStep extends AbstractPackageStep {
    public KodiStep() {
        super(Binaries.kodi());
    }
}
