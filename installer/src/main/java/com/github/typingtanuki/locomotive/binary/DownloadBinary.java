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
    private final String version;

    public DownloadBinary(String binary, String url, String version) {
        super(binary);
        this.url = url;
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public FontAwesome.Glyph icon() {
        return FontAwesome.Glyph.DOWNLOAD;
    }
}
