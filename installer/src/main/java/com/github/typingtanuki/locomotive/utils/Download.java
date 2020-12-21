package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Download {
    private Download() {
        super();
    }

    public static byte[] inMemory(String url, DownloadMonitor monitor) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            inStream(url, buffer, monitor);
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    private static void inStream(String url, OutputStream outputStream, DownloadMonitor monitor) throws IOException {
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
                    monitor.setProgress(((double) total) / length);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    public static Path inTempFile(String url, DownloadMonitor monitor) throws IOException {
        Path path = Files.createTempFile("paa_key", "key");
        inFile(url, path, monitor);
        return path;
    }

    public static void inFile(String url, Path target, DownloadMonitor monitor) throws IOException {
        Files.createDirectories(target.getParent());
        try (OutputStream outputStream = Files.newOutputStream(target)) {
            inStream(url, outputStream, monitor);
        }
    }
}
