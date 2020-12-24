package com.github.typingtanuki.locomotive.binary;

/**
 * A binary where the installer comes from a download link
 */
public class DownloadBinary extends Binary {
    /**
     * The URL pointing to the installer
     */
    private final String url;
    private final String binary;

    public DownloadBinary(String binary, String url) {
        super(binary);
        this.binary = binary;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getBinary() {
        return binary;
    }
}
