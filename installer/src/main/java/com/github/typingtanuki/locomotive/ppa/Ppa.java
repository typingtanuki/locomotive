package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.i18n.I18n;

public class Ppa {
    private final String ppa;
    private final PpaKey key;
    private final String title;
    private final String description;

    public Ppa(String ppa) {
        this(ppa, null);
    }

    public Ppa(String ppa, PpaKey key) {
        super();

        this.ppa = ppa;
        this.key = key;
        this.title = I18n.get("ppa." + shortName(ppa) + ".title");
        this.description = I18n.get("ppa." + shortName(ppa) + ".description");
    }

    private static String shortName(String ppa) {
        if (ppa.startsWith("ppa:")) {
            return ppa.split(":")[1].replaceAll("/", "_");
        }
        if (ppa.startsWith("deb")) {
            return ppa.split("//")[1].split("/")[0].replaceAll("\\.", "_");
        }
        return ppa;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PpaKey getKey() {
        return key;
    }

    public String getUrl() {
        return ppa;
    }
}
