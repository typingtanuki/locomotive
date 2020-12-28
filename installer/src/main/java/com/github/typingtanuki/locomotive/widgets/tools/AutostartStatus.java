package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.utils.PackageTester;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AutostartStatus {
    private final List<Binary> enabled = new ArrayList<>();
    private final List<Binary> available = new ArrayList<>();
    private final List<Binary> notInstalled = new ArrayList<>();

    public AutostartStatus(List<Binary> binaries) {
        for (Binary binary : binaries) {
            check(binary);
        }
    }


    private void check(Binary binary) {
        if (!PackageTester.isBinaryOnPath(binary)) {
            notInstalled.add(binary);
            return;
        }
        if (isAutostart(binary)) {
            enabled.add(binary);
            return;
        }
        available.add(binary);
    }

    private boolean isAutostart(Binary binary) {
        return Files.exists(desktopAutostart(binary));
    }

    public static Path desktopAutostart(Binary binary) {
        return Paths.get("/home")
                .resolve(System.getProperty("user.name"))
                .resolve(".config")
                .resolve("autostart")
                .resolve(binary.getBinary() + ".desktop");
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("enabled: [");
        addAll(enabled, out);
        out.append("], available: [");
        addAll(available, out);
        out.append("], notInstalled: [");
        addAll(notInstalled, out);
        out.append("]");
        return "AutostartStatus{" + out.toString() + '}';
    }

    private void addAll(List<Binary> binaries, StringBuilder out) {
        boolean first = true;
        for (Binary binary : binaries) {
            if (!first) {
                out.append(", ");
            }
            first = false;
            out.append(binary.getBinary());
        }
    }

    public List<Binary> getEnabled() {
        return enabled;
    }

    public List<Binary> getAvailable() {
        return available;
    }

    public List<Binary> getNotInstalled() {
        return notInstalled;
    }

    public void addEnabled(Binary binary) {
        enabled.add(binary);
    }

    public void addDisabled(Binary binary) {
        available.add(binary);
    }
}
