package com.github.typingtanuki.locomotive.steps.games;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class GamehubStep extends AbstractPackageStep {
    public GamehubStep() {
        super(Binaries.gamehub(), Ppas.gamehub());
    }
}
