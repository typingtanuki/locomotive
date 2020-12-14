package com.github.typingtanuki.locomotive.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ReaderReader implements Callable<Void> {
    private final BufferedReader reader;
    private final StringBuilder builder;

    private boolean finished = false;
    private Exception failure;

    public ReaderReader(BufferedReader reader, StringBuilder builder) {
        super();

        this.reader = reader;
        this.builder = builder;
    }

    @Override
    public Void call() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
        } catch (IOException | RuntimeException e) {
            failure = e;
        } finally {
            finished = true;
        }
        return null;
    }

    public boolean isFinished() throws IOException {
        if (failure != null) {
            throw new IOException("Failed reading output of command", failure);
        }
        return finished;
    }
}
