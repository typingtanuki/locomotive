package com.github.typingtanuki.locomotive.utils;

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
    private DownloadUtils() {
        super();
    }

    public static byte[] inMemory(String url) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            inStream(url, buffer);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    private static void inStream(String url, OutputStream outputStream) throws IOException {
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
                    //monitor.setProgress(((double) total) / length);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    public static Path inTempFile(String url) throws IOException {
        Path path = Files.createTempFile("paa_key", "key");
        inFile(url, path);
        return path;
    }

    public static void inFile(String url, Path target) throws IOException {
        Files.createDirectories(target.getParent());
        try (OutputStream outputStream = Files.newOutputStream(target)) {
            inStream(url, outputStream);
        }
    }

    public static void makeExecutable(Path target) throws IOException {
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(target);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(target, permissions);
    }

    public static String inString(String url) throws IOException {
        byte[] bytes = inMemory(url);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
