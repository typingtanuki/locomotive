package com.github.typingtanuki.locomotive.binary;

public abstract class Binary {
    private final String binary;
    private String title;
    private String description;

    public Binary(String binary) {
        super();

        this.binary = binary;
        this.title = binary;
    }

    public Binary title(String title) {
        this.title = title;
        return this;
    }

    public Binary description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Binary{binary='" + binary + "'}";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBinary() {
        return binary;
    }
}
