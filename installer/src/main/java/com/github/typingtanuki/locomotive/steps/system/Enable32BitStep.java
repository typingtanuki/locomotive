package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.steps.Step;

import java.io.IOException;

public class Enable32BitStep implements Step {
    @Override
    public String description() {
        return "Enable 32bit support to be able to run non 64bits software.";
    }

    @Override
    public String title() {
        return "32bit compatibility.";
    }

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
