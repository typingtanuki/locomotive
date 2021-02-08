package com.github.typingtanuki.locomotive.utils;

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

    public static void installPpa(Ppa ppa, TerminalComponent terminal)
            throws ProcessFailedException, ProcessNotAuthorized {
        ProcessExec.sudoExec(terminal, "apt-add-repository", ppa.getUrl(), "-y");
        updateRepositories(terminal);
    }

    public static void installKey(PpaKey key, TerminalComponent terminal)
            throws IOException, ProcessFailedException, ProcessNotAuthorized {
        Path keyFile = DownloadUtils.inTempFile(key.getKey(), nullDownload()).toAbsolutePath();
        ProcessExec.sudoExec(terminal, "apt-key", "add", keyFile.toString());
        Files.deleteIfExists(keyFile);
    }

    private static void updateRepositories(TerminalComponent terminal)
            throws ProcessFailedException, ProcessNotAuthorized {
        ProcessExec.sudoExec(terminal, "apt", "update", "-y");
    }
}
