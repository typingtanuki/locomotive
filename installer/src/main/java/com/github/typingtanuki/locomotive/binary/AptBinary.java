package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.ppa.Ppa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    /**
     * The PPA from which the package is from (null if default)
     */
    private final Ppa ppa;

    public AptBinary(String binary, String packageName, Ppa ppa) {
        super(binary);

        packages.add(packageName);
        this.ppa = ppa;
    }

    public AptBinary(String binary, String packageName) {
        this(binary, packageName, null);
    }

    public AptBinary(String binary, Ppa ppa) {
        this(binary, binary, ppa);
    }

    public AptBinary(String binary) {
        this(binary, binary, null);
    }

    /**
     * Adds extra packages to be installed together with
     */
    public Binary extraPackages(String... extra) {
        packages.addAll(Arrays.asList(extra));
        return this;
    }

    /**
     * Set flags for APT
     */
    public Binary aptFlags(String... flags) {
        this.flags.addAll(Arrays.asList(flags));
        return this;
    }

    public Set<String> getPackages() {
        return packages;
    }

    public Set<String> getFlags() {
        return flags;
    }

    public Ppa getPpa() {
        return ppa;
    }
}
