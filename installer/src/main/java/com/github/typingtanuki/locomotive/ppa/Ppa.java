package com.github.typingtanuki.locomotive.ppa;

public class Ppa {
    private final String ppa;
    private final PpaKey key;
    private final String title;
    private final String description;

    private Boolean installed = null;

    public Ppa(String ppa, String title, String description) {
        this(ppa, title, description, null);
    }

    public Ppa(String ppa, String title, String description, PpaKey key) {
        super();

        this.ppa = ppa;
        this.key = key;
        this.title = title;
        this.description = description;
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

    public PpaKey getKey() {
        return key;
    }

    public String getUrl() {
        return ppa;
    }
}
