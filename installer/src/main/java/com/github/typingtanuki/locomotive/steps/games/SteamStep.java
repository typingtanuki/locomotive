package com.github.typingtanuki.locomotive.steps.games;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class SteamStep extends AbstractPackageStep {
    public SteamStep() {
        super(Binaries.steam());
    }
}
