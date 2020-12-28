package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.i18n.I18n;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Objects;

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
    private final String title;
    /**
     * A description of this binary for the user to understand what it does
     */
    private final String description;

    public Binary(String binary) {
        super();

        this.binary = binary;
        this.title = I18n.get("binary." + binary + ".title");
        this.description = I18n.get("binary." + binary + ".description");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binary binary1 = (Binary) o;
        return Objects.equals(binary, binary1.binary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binary);
    }
}
