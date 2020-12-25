package com.github.typingtanuki.locomotive.binary;

import org.controlsfx.glyphfont.FontAwesome;

/**
 * A binary where the installer comes from a download link
 */
public class DownloadBinary extends Binary {
    /**
     * The URL pointing to the installer
     */
    private final String url;

    public DownloadBinary(String binary, String url) {
        super(binary);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public FontAwesome.Glyph icon() {
        return FontAwesome.Glyph.DOWNLOAD;
    }
}
