package com.github.typingtanuki.locomotive.binary;

import org.controlsfx.glyphfont.FontAwesome;

/**
 * Represents a binary and its way of installation
 */
public abstract class Binary {
    /**
     * The command to run this binary (used for detection)
     */
    private final String binary;
    /**
     * The name of this binary (often same as the binary itself)
     */
    private String title;
    /**
     * A description of this binary for the user to understand what it does
     */
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

    public FontAwesome.Glyph icon() {
        return FontAwesome.Glyph.FILE_CODE_ALT;
    }
}
