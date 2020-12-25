package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.components.DownloadComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public final class DownloadUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadUtils.class);

    private DownloadUtils() {
        super();
    }

    public static byte[] inMemory(String url,
                                  DownloadComponent downloadComponent) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            inStream(url, buffer, downloadComponent);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    private static void inStream(String url,
                                 OutputStream outputStream,
                                 DownloadComponent downloadComponent) throws IOException {
        LOGGER.info("Downloading " + url);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            int length = connection.getContentLength();
            int total = 0;
            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream())) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    total += bytesRead;
                    outputStream.write(dataBuffer, 0, bytesRead);
                    downloadComponent.updateProgress(total, length);
                }
            }
        } catch (IOException e) {
            LOGGER.info("Download failed for " + url, e);
            throw new IOException("Error downloading " + url, e);
        }
        LOGGER.info("Download finished for " + url);
    }

    public static Path inTempFile(String url,
                                  DownloadComponent downloadComponent) throws IOException {
        Path path = Files.createTempFile("paa_key", "key");
        inFile(url, path, downloadComponent);
        return path;
    }

    public static void inFile(String url,
                              Path target,
                              DownloadComponent downloadComponent) throws IOException {
        Files.createDirectories(target.getParent());
        try (OutputStream outputStream = Files.newOutputStream(target)) {
            inStream(url, outputStream, downloadComponent);
        }
    }

    public static void makeExecutable(Path target) throws IOException {
        LOGGER.info("Changing permissions for " + target);
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(target);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(target, permissions);
        LOGGER.info("Changed permissions for " + target);
    }

    public static String inString(String url,
                                  DownloadComponent downloadComponent) throws IOException {
        byte[] bytes = inMemory(url, downloadComponent);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
