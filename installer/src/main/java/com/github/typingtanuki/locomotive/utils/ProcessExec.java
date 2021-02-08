package com.github.typingtanuki.locomotive.utils;

import com.github.typingtanuki.locomotive.components.TerminalComponent;
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

    private static int SEQUENCE = 0;

    private final StringBuilder stdout = new StringBuilder();
    private final StringBuilder stderr = new StringBuilder();
    private final TerminalComponent terminal;
    private final String processId;

    private Integer exit = null;
    private boolean isAdmin = false;

    private ProcessExec(TerminalComponent terminal) {
        super();
        this.terminal = terminal;
        this.processId = makeId();
    }

    private static synchronized String makeId() {
        SEQUENCE++;
        return "P-" + SEQUENCE;
    }

    public static ProcessExec exec(TerminalComponent terminal,
                                   String binary,
                                   String... args) throws ProcessFailedException {
        ProcessExec pe = new ProcessExec(terminal);
        try {
            pe.execute(binary, args);
        } catch (ProcessNotAuthorized e) {
            throw new ProcessFailedException("Process failed with exit 127", e);
        }
        return pe;
    }

    public static ProcessExec sudoExec(TerminalComponent terminal,
                                       String binary,
                                       String... args) throws ProcessFailedException, ProcessNotAuthorized {
        ProcessExec pe = new ProcessExec(terminal);
        pe.isAdmin = true;
        pe.execute(binary, args);
        return pe;
    }

    private void execute(String binary, String... args) throws ProcessFailedException, ProcessNotAuthorized {
        exit = -1;

        List<String> allArgs = new ArrayList<>(args.length + 2);
        if (isAdmin) {
            allArgs.add("pkexec");
        }
        allArgs.add(binary);
        allArgs.addAll(Arrays.asList(args));

        LOGGER.info("{} Running {}", processId, String.join(" ", allArgs));
        ProcessBuilder builder = new ProcessBuilder();
        builder.environment().put("LANG", "en_US.UTF-8");
        builder.environment().put("LANGUAGE", "en_US:en");
        builder.command(allArgs);
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            throw new ProcessFailedException("Failed starting process", e);
        }

        if (terminal != null) {
            terminal.setInput(process.getOutputStream());
        }

        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        ReaderReader stdoutReaderReader = new ReaderReader(stdoutReader, stdout, terminal);
        ReaderReader stderrReaderReader = new ReaderReader(stderrReader, stderr, terminal);
        Future<Void> stdoutFuture = EXECUTORS.submit(stdoutReaderReader);
        Future<Void> stderrFuture = EXECUTORS.submit(stderrReaderReader);

        try {
            exit = process.waitFor();
            stdoutFuture.get();
            stderrFuture.get();
            LOGGER.info("{} Process finished with exit {}", processId, exit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ProcessFailedException("Process was interrupted", e);
        } catch (ExecutionException e) {
            throw new ProcessFailedException("Could not read output of process", e);
        }

        try {
            if (!stdoutReaderReader.isFinished() || !stderrReaderReader.isFinished()) {
                throw new ProcessFailedException("Command finished, but stream is not");
            }
        } catch (IOException e) {
            throw new ProcessFailedException("Error Checking process state", e);
        }

        checkSuccess();
    }

    public String getStdout() {
        return stdout.toString();
    }

    public String getStderr() {
        return stderr.toString();
    }

    private void checkSuccess() throws ProcessNotAuthorized, ProcessFailedException {
        if (exit == null) {
            throw new IllegalStateException("Process was not started");
        }
        if (exit == 0) {
            return;
        }
        if (exit == 127) {
            throw new ProcessNotAuthorized();
        }

        throw new ProcessFailedException("Process exited with code: " + exit +
                "\r\nStdout:\r\n" + getStdout() +
                "\r\nStderr:\r\n" + getStderr());
    }
}
