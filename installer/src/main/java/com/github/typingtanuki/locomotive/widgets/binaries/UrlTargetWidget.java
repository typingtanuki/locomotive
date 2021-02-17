package com.github.typingtanuki.locomotive.widgets.binaries;

public interface UrlTargetWidget {
    void setTarget(String url, String version);

    boolean isDownloaded();
}
