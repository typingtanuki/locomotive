package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.steps.AbstractStep;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;

public class Enable32BitStep extends AbstractStep {
    @Override
    public boolean execute() {
        ProcessExec exec = new ProcessExec("dpkg");
        try {
            exec.exec("--add-architecture", "i386");
        } catch (IOException e) {
            System.err.println("Could not enable 32bit architecture");
            e.printStackTrace(System.err);
            return false;
        }
        return isDone();
    }

    @Override
    public boolean isDone() {
        ProcessExec exec = new ProcessExec("dpkg");
        try {
            exec.exec("--print-foreign-architectures");
        } catch (IOException e) {
            System.err.println("Could not check for 32bit architecture");
            e.printStackTrace(System.err);
            return false;
        }
        return exec.getStdout().contains("i386");
    }
}
