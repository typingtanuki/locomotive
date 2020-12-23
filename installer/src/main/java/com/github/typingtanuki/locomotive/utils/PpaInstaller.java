package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.ppa.PpaKey;

import java.io.IOException;

public final class PpaInstaller {
    private PpaInstaller() {
        super();
    }

    public static void installPpa(Ppa ppa) throws IOException {
        PpaKey key = ppa.getKey();
        if (key != null) {
            if (!PpaTester.isPpaKeyActivated(key.getKey())) {
                installKey(key);
            }
        }

        ProcessExec processExec = ProcessExec.sudoExec("apt-add-repository", ppa.getUrl(), "-y");
        processExec.checkSuccess();
    }

    private static void installKey(PpaKey key) {
        // TBD
    }
}
