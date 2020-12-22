package com.github.typingtanuki.locomotive.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class PackageTester {
    private static final Set<String> PATHS = new HashSet<>();
    private static final Set<String> PPAS = new HashSet<>();
    private static final Set<String> PPA_KEYS = new HashSet<>();

    private PackageTester() {
        super();
    }

    public static boolean isBinaryOnPath(String binaryName) {
        Set<String> paths = resolvePath();
        for (String path : paths) {
            Path binary = Paths.get(path).resolve(binaryName);
            if (Files.exists(binary)  /* Binary does not exists in this path */ &&
                    Files.isExecutable(binary) /* Binary exists, but is not executable */) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPpaActivated(String ppa) throws IOException {
        String ppaName = ppa;
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
        ProcessExec exec = new ProcessExec("apt", null);
        exec.exec("policy");
        exec.checkSuccess();
        for (String s : exec.getStdout().split("\\n")) {
            PPAS.add(s.strip().toLowerCase(Locale.ENGLISH));
        }
        return PPAS;
    }

    private static Set<String> resolvePath() {
        if (!PATHS.isEmpty()) {
            return PATHS;
        }
        Collections.addAll(PATHS, System.getenv("PATH").split(File.pathSeparator));
        return PATHS;
    }

    private static Set<String> resolveKeys() throws IOException {
        if (!PPA_KEYS.isEmpty()) {
            return PPAS;
        }
        ProcessExec exec = new ProcessExec("apt-key", null);
        exec.exec("list");
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
