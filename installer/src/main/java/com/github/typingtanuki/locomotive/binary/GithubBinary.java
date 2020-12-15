package com.github.typingtanuki.locomotive.binary;

public class GithubBinary extends Binary {
    private final String user;
    private final String repository;

    public GithubBinary(String binary, String user, String repository) {
        super(binary);

        this.user = user;
        this.repository = repository;
    }

    @Override
    public void install() {
        // TBD
    }
}
