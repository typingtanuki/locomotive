package com.github.typingtanuki.locomotive.binary;

/**
 * A binary installer downloaded from github releases
 */
public class GithubBinary extends Binary {
    /**
     * The name of the repository's user
     */
    private final String user;
    /**
     * The name of the repository
     */
    private final String repository;

    public GithubBinary(String binary, String user, String repository) {
        super(binary);

        this.user = user;
        this.repository = repository;
    }

    @Override
    public boolean install() {
        // TBD
        return false;
    }
}
