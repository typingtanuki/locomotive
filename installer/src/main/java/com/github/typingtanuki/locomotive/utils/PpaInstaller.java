package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.components.DownloadComponent;
import com.github.typingtanuki.locomotive.components.TerminalComponent;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.ppa.PpaKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.typingtanuki.locomotive.components.DownloadComponent.nullDownload;

public final class PpaInstaller {
    private PpaInstaller() {
        super();
    }

    public static void installPpa(Ppa ppa, TerminalComponent terminal) throws IOException {
        ProcessExec processExec = ProcessExec.sudoExec(terminal, "apt-add-repository", ppa.getUrl(), "-y");
        processExec.checkSuccess();

        updateRepositories(terminal);
    }

    public static void installKey(PpaKey key, TerminalComponent terminal) throws IOException {
        Path keyFile = DownloadUtils.inTempFile(key.getKey(), nullDownload()).toAbsolutePath();
        ProcessExec processExec = ProcessExec.sudoExec(terminal, "apt-key", "add", keyFile.toString());
        processExec.checkSuccess();
        Files.deleteIfExists(keyFile);
    }

    private static void updateRepositories(TerminalComponent terminal) throws IOException {
        ProcessExec processExec = ProcessExec.sudoExec(terminal, "apt", "update", "-y");
        processExec.checkSuccess();
    }
}
