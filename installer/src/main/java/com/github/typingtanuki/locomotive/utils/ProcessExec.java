package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ProcessExec {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessExec.class);

    private static final ExecutorService EXECUTORS = new ThreadPoolExecutor(
            5,
            5,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    private final String binary;
    private final StringBuilder stdout;
    private final StringBuilder stderr;
    private final ProcessMonitor monitor;

    private Integer exit = null;
    private boolean isAdmin = false;

    public ProcessExec(String binary, ProcessMonitor monitor) {
        super();
        this.binary = binary;
        this.monitor = monitor;

        stdout = new StringBuilder();
        stderr = new StringBuilder();
    }

    public void exec(String... args) throws IOException {
        exit = -1;

        List<String> allArgs = new ArrayList<>(args.length + 2);
        if (isAdmin) {
            allArgs.add("pkexec");
        }
        allArgs.add(binary);
        allArgs.addAll(Arrays.asList(args));

        LOGGER.info("Running " + String.join(" ", allArgs));
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(allArgs);
        Process process = builder.start();

        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        ReaderReader stdoutReaderReader = new ReaderReader(stdoutReader, stdout, monitor);
        ReaderReader stderrReaderReader = new ReaderReader(stderrReader, stderr, monitor);
        Future<Void> stdoutFuture = EXECUTORS.submit(stdoutReaderReader);
        Future<Void> stderrFuture = EXECUTORS.submit(stderrReaderReader);

        try {
            exit = process.waitFor();
            if (monitor != null) {
                monitor.appendOutput("Exit: " + exit);
            }
            stdoutFuture.get();
            stderrFuture.get();
            LOGGER.info("Process finished with exit " + exit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Process was interrupted", e);
        } catch (ExecutionException e) {
            throw new IOException("Could not read output of process", e);
        }

        if (!stdoutReaderReader.isFinished() || !stderrReaderReader.isFinished()) {
            throw new IOException("Command finished, but stream is not");
        }

        checkSuccess();
    }

    public String getStdout() {
        return stdout.toString();
    }

    public String getStderr() {
        return stderr.toString();
    }

    public int getExit() {
        if (exit == null) {
            throw new IllegalStateException("Process was not started");
        }
        return exit;
    }

    public void checkSuccess() throws IOException {
        if (exit == null) {
            throw new IllegalStateException("Process was not started");
        }
        if (exit == 0) {
            return;
        }

        throw new IOException("Process " + binary + " exited with code: " + exit +
                "\r\nStdout:\r\n" + getStdout() +
                "\r\nStderr:\r\n" + getStderr());
    }

    public void asAdmin() {
        isAdmin = true;
    }
}
