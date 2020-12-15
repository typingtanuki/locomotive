package com.github.typingtanuki.locomotive.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ProcessExec {
    private static final ExecutorService EXECUTORS = new ThreadPoolExecutor(
            1,
            5,
            1000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    private final String binary;
    private final StringBuilder stdout;
    private final StringBuilder stderr;

    private int exit = -1;

    public ProcessExec(String binary) {
        super();
        this.binary = binary;

        stdout = new StringBuilder();
        stderr = new StringBuilder();
    }

    public void exec(String... args) throws IOException {
        List<String> allArgs = new ArrayList<>(args.length + 1);
        allArgs.add(binary);
        allArgs.addAll(Arrays.asList(args));

        ProcessBuilder builder = new ProcessBuilder();
        builder.command(allArgs);
        Process process = builder.start();

        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        ReaderReader stdoutReaderReader = new ReaderReader(stdoutReader, stdout);
        ReaderReader stderrReaderReader = new ReaderReader(stderrReader, stdout);
        Future<Void> stdoutFuture = EXECUTORS.submit(stdoutReaderReader);
        Future<Void> stderrFuture = EXECUTORS.submit(stderrReaderReader);

        try {
            exit = process.waitFor();
            stdoutFuture.get();
            stderrFuture.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e){
            throw  new IOException("Could not read output of process",e);
        }

        if (!stdoutReaderReader.isFinished() || !stderrReaderReader.isFinished()) {
            throw new IOException("Command finished, but stream is not");
        }
    }

    public String getStdout() {
        return stdout.toString();
    }

    public String getStderr() {
        return stderr.toString();
    }

    public int getExit() {
        return exit;
    }

    public void checkSuccess() {
        if(exit==0){
            return;
        }
        System.err.println("Process "+binary+" failed:");
        System.err.println(getStderr());
    }
}
