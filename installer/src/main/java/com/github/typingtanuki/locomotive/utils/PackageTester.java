package com.github.typingtanuki.locomotive.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class PackageTester {
    private static final Set<String> PATHS = new HashSet<>();

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

    private static Set<String> resolvePath() {
        if (!PATHS.isEmpty()) {
            return PATHS;
        }
        Collections.addAll(PATHS, System.getenv("PATH").split(File.pathSeparator));
        return PATHS;
    }
}
