package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.exec.Download;
import com.github.typingtanuki.locomotive.exec.PackageTester;

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

    public String getKeyName() {
        return keyName;
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

    public void install() throws IOException {
        Path keyBytes = Download.inTempFile(key);

    }
}
