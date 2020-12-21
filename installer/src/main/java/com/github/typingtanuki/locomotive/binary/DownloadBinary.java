package com.github.typingtanuki.locomotive.binary;

/**
 * A binary where the installer comes from a downalod link
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

    @Override
    public boolean install() {
        // TBD
        return false;
    }

    @Override
    public InstallType getType() {
        return InstallType.DOWNLOAD;
    }
}
