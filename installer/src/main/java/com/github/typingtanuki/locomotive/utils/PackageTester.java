package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.binary.Binary;

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

    public static boolean isBinaryOnPath(Binary binary) {
        Set<String> paths = resolvePath();
        for (String path : paths) {
            Path binaryPath = Paths.get(path).resolve(binary.getBinary());
            if (Files.exists(binaryPath)  /* Binary does not exists in this path */ &&
                    Files.isExecutable(binaryPath) /* Binary exists, but is not executable */) {
                return true;
            }
        }

        Path path = Paths.get(System.getenv("HOME")).resolve("." + binary.getBinary()).resolve(binary.getBinary());
        /* Binary exists, but is not executable */
        return Files.exists(path)  /* Binary does not exists in this path */ &&
                Files.isExecutable(path);
    }

    private static Set<String> resolvePath() {
        if (!PATHS.isEmpty()) {
            return PATHS;
        }
        Collections.addAll(PATHS, System.getenv("PATH").split(File.pathSeparator));
        return PATHS;
    }
}
