package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.utils.PackageTester;

import java.io.IOException;

public class Ppa {
    private final String ppa;
    private final PpaKey key;

    private Boolean installed = null;
    private String title;
    private String description;

    public Ppa(String ppa, PpaKey key) {
        super();

        this.ppa = ppa;
        this.key = key;
    }

    public Ppa(String ppa) {
        this(ppa, null);
    }

    public Ppa title(String title) {
        this.title = title;
        return this;
    }

    public Ppa description(String description) {
        this.description = description;
        return this;
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
                ", title='" + title + '\'' +
                '}';
    }

    public String getName() {
        return ppa;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
