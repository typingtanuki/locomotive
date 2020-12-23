package com.github.typingtanuki.locomotive.ppa;

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

    public String getKeyName() {
        return keyName;
    }

    public String getKey() {
        return key;
    }
}
