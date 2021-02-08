package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.ppa.PpaKey;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static com.github.typingtanuki.locomotive.components.TerminalComponent.nullTerminal;

public final class PpaTester {
    private static final Set<String> PPAS = new HashSet<>();
    private static final Set<String> PPA_KEYS = new HashSet<>();

    private PpaTester() {
        super();
    }

    public static boolean isPpaActivated(Ppa ppa) throws ProcessFailedException {
        String ppaName = ppa.getUrl();
        if (ppaName.contains(":")) {
            ppaName = ppaName.split(":")[1];
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

    private static Set<String> resolvePpas() throws ProcessFailedException {
        if (!PPAS.isEmpty()) {
            return PPAS;
        }
        synchronized (PPAS) {
            if (!PPAS.isEmpty()) {
                return PPAS;
            }
            ProcessExec exec = ProcessExec.exec(nullTerminal(), "apt", "policy");
            for (String s : exec.getStdout().split("\\n")) {
                PPAS.add(s.strip().toLowerCase(Locale.ENGLISH));
            }
        }
        return PPAS;
    }

    private static Set<String> resolveKeys() throws ProcessFailedException {
        if (!PPA_KEYS.isEmpty()) {
            return PPA_KEYS;
        }
        synchronized (PPA_KEYS) {
            if (!PPA_KEYS.isEmpty()) {
                return PPA_KEYS;
            }
            ProcessExec exec = ProcessExec.exec(nullTerminal(), "apt-key", "list");
            for (String s : exec.getStdout().split("\\n")) {
                if (s.contains("uid")) {
                    PPA_KEYS.add(s.strip().toLowerCase(Locale.ENGLISH));
                }
            }
        }
        return PPA_KEYS;
    }

    public static boolean isPpaKeyActivated(PpaKey key) throws ProcessFailedException {
        if (key == null) {
            // No key needed
            return true;
        }

        Set<String> installedKeys = resolveKeys();
        for (String installedKey : installedKeys) {
            if (installedKey.contains(key.getKeyName())) {
                return true;
            }
        }
        return false;
    }
}
