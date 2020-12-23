package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class PpaTester {
    private static final Set<String> PPAS = new HashSet<>();
    private static final Set<String> PPA_KEYS = new HashSet<>();

    private PpaTester() {
        super();
    }

    public static boolean isPpaActivated(Ppa ppa) throws IOException {
        String ppaName = ppa.getUrl();
        if (ppaName.contains(":")) {
            ppaName = ppaName.split("\\:")[1];
        }
        if (ppaName.contains(" ")) {
            ppaName = ppaName.split(" ")[0];
        }
        ppaName = ppaName.toLowerCase(Locale.ENGLISH).strip();

        Set<String> installedPpas = resolvePpas();
        for (String installedPpa : installedPpas) {
            if (installedPpa.contains(ppaName)) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> resolvePpas() throws IOException {
        if (!PPAS.isEmpty()) {
            return PPAS;
        }
        ProcessExec exec = ProcessExec.exec("apt", "policy");
        exec.checkSuccess();
        for (String s : exec.getStdout().split("\\n")) {
            PPAS.add(s.strip().toLowerCase(Locale.ENGLISH));
        }
        return PPAS;
    }

    private static Set<String> resolveKeys() throws IOException {
        if (!PPA_KEYS.isEmpty()) {
            return PPAS;
        }
        ProcessExec exec = ProcessExec.exec("apt-key", "list");
        exec.checkSuccess();
        for (String s : exec.getStdout().split("\\n")) {
            if (s.contains("uid")) {
                PPA_KEYS.add(s.strip().toLowerCase(Locale.ENGLISH));
            }
        }
        return PPA_KEYS;
    }

    public static boolean isPpaKeyActivated(String keyName) throws IOException {
        Set<String> installedKeys = resolveKeys();
        for (String installedKey : installedKeys) {
            if (installedKey.contains(keyName)) {
                return true;
            }
        }
        return false;
    }
}
