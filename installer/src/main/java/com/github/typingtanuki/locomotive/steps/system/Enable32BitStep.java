package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.controller.component.StepState;
import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import com.github.typingtanuki.locomotive.steps.AbstractStep;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;

public class Enable32BitStep extends AbstractStep {
    @Override
    public StepState doExecute() {
        try {
            ProcessMonitor monitor = monitor(ProcessMonitor.class);
            ProcessExec exec = new ProcessExec("dpkg", monitor);
            exec.exec("--add-architecture", "i386");
            return StepState.success();
        } catch (IOException e) {
            return new StepState(e);
        }
    }

    @Override
    public boolean isDone() throws IOException {
        ProcessExec exec = new ProcessExec("dpkg", null);
        exec.exec("--print-foreign-architectures");
        return exec.getStdout().contains("i386");
    }
}
