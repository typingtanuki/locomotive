package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.utils.Download;
import com.github.typingtanuki.locomotive.utils.PackageTester;

import java.io.IOException;
import java.nio.file.Path;

public class PpaKey {
    private final String keyName;
    private final String key;

    public PpaKey(String keyName, String key) {
        super();

        this.keyName = keyName;
        this.key = key;
    }

    @Override
    public String toString() {
        return "PpaKey{" +
                "keyName='" + keyName + '\'' +
                '}';
    }

    public boolean isInstalled() throws IOException {
        return PackageTester.isPpaKeyActivated(keyName);
    }

    public void install(DownloadMonitor monitor) throws IOException {
        Path keyBytes = Download.inTempFile(key, monitor);
        // TBD
    }
}