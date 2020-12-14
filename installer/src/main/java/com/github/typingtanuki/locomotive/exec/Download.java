package com.github.typingtanuki.locomotive.exec;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                buffer.write(dataBuffer, 0, bytesRead);
            }
            return buffer.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error downloading " + url, e);
        }
    }

    public static Path inTempFile(String url) throws IOException {
        Path path = Files.createTempFile("paa_key", "key");
        Files.write(path, inMemory(url));
        return path;
    }
}
