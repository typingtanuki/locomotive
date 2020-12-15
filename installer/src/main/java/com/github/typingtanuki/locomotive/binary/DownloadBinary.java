package com.github.typingtanuki.locomotive.binary;

public class DownloadBinary extends Binary {
    private final String url;

    public DownloadBinary(String binary, String url) {
        super(binary);

        this.url = url;
    }

    @Override
    public void install() {
        // TBD
    }
}
