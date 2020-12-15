package com.github.typingtanuki.locomotive.binary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AptBinary extends Binary {
    private final Set<String> packages = new HashSet<>();
    private final Set<String> flags = new HashSet<>();


    public AptBinary(String binary, String packageName) {
        super(binary);
        packages.add(packageName);
    }

    public AptBinary(String binary) {
        this(binary, binary);
    }

    @Override
    public void install() {
        // TBD
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
