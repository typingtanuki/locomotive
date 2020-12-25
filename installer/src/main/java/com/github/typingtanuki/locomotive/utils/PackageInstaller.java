package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.binary.AptBinary;
import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.binary.DownloadBinary;
import com.github.typingtanuki.locomotive.components.DownloadComponent;
import com.github.typingtanuki.locomotive.components.TerminalComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class PackageInstaller {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadBinary.class);

    public static void installBinary(Binary binary,
                                     TerminalComponent terminalComponent,
                                     DownloadComponent download) throws IOException {
        if (binary instanceof AptBinary) {
            installAptBinary((AptBinary) binary, terminalComponent);
            return;
        }
        if (binary instanceof DownloadBinary) {
            installDownloadBinary((DownloadBinary) binary, terminalComponent, download);
            return;
        }
        throw new IllegalStateException("Unsupported install type for: " + binary.getClass().getSimpleName());
    }

    private static void installDownloadBinary(DownloadBinary binary,
                                              TerminalComponent terminal,
                                              DownloadComponent download) throws IOException {
        Path installer = Paths.get("./cache/").resolve(binary.getBinary() + "-installer");
        LOGGER.info("Downloading to " + installer);
        DownloadUtils.inFile(binary.getUrl(), installer, download);
        LOGGER.info("Downloading to " + installer + ": OK");
        LOGGER.info("Changing permissions of " + installer);
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(installer);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(installer, permissions);
        LOGGER.info("Changing permissions of " + installer + ": OK");

        ProcessExec processExec = ProcessExec.exec(terminal, installer.toAbsolutePath().toString());
        processExec.checkSuccess();
    }

    private static void installAptBinary(AptBinary binary, TerminalComponent terminal) throws IOException {
        List<String> allArgs = new ArrayList<>();
        allArgs.add("install");
        allArgs.addAll(binary.getFlags());
        allArgs.addAll(binary.getPackages());
        allArgs.add("-y");
        ProcessExec processExec = ProcessExec.sudoExec(terminal, "apt", allArgs.toArray(new String[0]));
        processExec.checkSuccess();
    }
}
