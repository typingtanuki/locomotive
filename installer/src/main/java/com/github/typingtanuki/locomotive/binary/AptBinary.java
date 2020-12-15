package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;
import java.util.*;

/**
 * Binary to be installed through APT
 */
public class AptBinary extends Binary {
    /**
     * All the packages to install (may have extras)
     */
    private final Set<String> packages = new HashSet<>();
    /**
     * Extra flags to pass to the apt process
     */
    private final Set<String> flags = new HashSet<>();

    public AptBinary(String binary, String packageName) {
        super(binary);
        packages.add(packageName);
    }

    public AptBinary(String binary) {
        this(binary, binary);
    }

    @Override
    public boolean install() throws IOException {
        List<String> allArgs = new ArrayList<>();
        allArgs.addAll(flags);
        allArgs.addAll(packages);
        allArgs.add("-y");
        ProcessExec processExec = new ProcessExec("apt");
        processExec.exec(allArgs.toArray(new String[0]));
        return processExec.getExit() == 0;
    }

    public Binary extraPackages(String... extra) {
        packages.addAll(Arrays.asList(extra));
        return this;
    }

    public Binary aptFlags(String... flags) {
        this.flags.addAll(Arrays.asList(flags));
        return this;
    }

}
