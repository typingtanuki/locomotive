package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.steps.AbstractStep;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;

public class Enable32BitStep extends AbstractStep {
    @Override
    public void execute() throws IOException {
        ProcessExec exec = new ProcessExec("dpkg");
        exec.exec("--add-architecture", "i386");
    }

    @Override
    public boolean isDone() throws IOException {
        ProcessExec exec = new ProcessExec("dpkg");
        exec.exec("--print-foreign-architectures");
        return exec.getStdout().contains("i386");
    }
}
