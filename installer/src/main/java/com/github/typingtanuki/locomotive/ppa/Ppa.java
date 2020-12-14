package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.exec.PackageTester;

import java.io.IOException;

public class Ppa {
    private final String ppa;
    private final PpaKey key;

    private Boolean installed = null;

    public Ppa(String ppa, PpaKey key) {
        super();

        this.ppa = ppa;
        this.key = key;
    }

    public Ppa(String ppa) {
        this(ppa, null);
    }

    public boolean isInstalled() throws IOException {
        return PackageTester.isPpaActivated(ppa);
    }

    public void install() throws IOException {
        if (installed != null && installed) {
            return;
        }

        if (key != null) {
            if (!key.isInstalled()) {
                key.install();
            }
        }

        // TBD install ppa
    }

    public void checkInstalled() throws IOException {
        installed = isInstalled();
    }

    @Override
    public String toString() {
        return "Ppa{" +
                "installed=" + installed +
                ", ppa='" + ppa + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
