package com.github.typingtanuki.locomotive.ppa;

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
