package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.exec.PackageTester;

import java.util.*;

public class Binary {
    private final Set<String> packages = new HashSet<>();
    private final Set<String> flags = new HashSet<>();
    private final List<BinaryPost> posts = new ArrayList<>();

    private String binary;
    private boolean required;
    private String title;
    private String description;
    private String url;
    private Boolean installed;

    public Binary(String binary, String packageName, boolean required) {
        super();

        this.binary = binary;
        this.title = binary;
        packages.add(packageName);
        this.required = required;
    }

    public Binary(String binary, boolean required) {
        this(binary, binary, required);
    }

    public Binary title(String title) {
        this.title = title;
        return this;
    }

    public Binary description(String description) {
        this.description = description;
        return this;
    }

    public Binary extraPackages(String... extra) {
        packages.addAll(Arrays.asList(extra));
        return this;
    }

    public Binary aptFlags(String... flags) {
        this.flags.addAll(Arrays.asList(flags));
        return this;
    }

    public Binary fromGithub(String user, String repository) {
        // gwinga
        return this;
    }

    public Binary post(BinaryPost post) {
        posts.add(post);
        return this;
    }

    public Binary fromDownload(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return "Binary{" +
                "installed=" + installed +
                ", binary='" + binary + '\'' +
                ", required=" + required +
                '}';
    }

    public boolean isInstalled() {
        return PackageTester.isBinaryOnPath(binary);
    }

    public void checkInstalled() {
        installed = isInstalled();
    }
}
