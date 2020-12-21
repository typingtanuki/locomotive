package com.github.typingtanuki.locomotive.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Download {
    private Download() {
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
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream())) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                outputStream.write(dataBuffer, 0, bytesRead);
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
        try (OutputStream outputStream = Files.newOutputStream(target)) {
            inStream(url, outputStream);
        }
    }
}
