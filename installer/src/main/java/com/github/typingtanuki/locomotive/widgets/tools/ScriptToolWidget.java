package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.utils.ProcessFailedException;
import com.github.typingtanuki.locomotive.utils.ProcessNotAuthorized;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScriptToolWidget extends AbstractToolWidget {
    private final String script;
    private String fullCommand;

    public ScriptToolWidget(String title,
                            String description,
                            String script,
                            SetupToolsPage setupToolsPage) {
        super(title, description, setupToolsPage);
        this.script = script;
        init();
    }

    @Override
    protected boolean isInstalled() {
        Path target = Paths.get(script).toAbsolutePath();
        if (Files.exists(target)) {
            fullCommand = target.toString();
            return true;
        }
        return false;
    }

    @Override
    protected void start() {
        try {
            ProcessExec.sudoExec(getTerminal(), fullCommand);
            finished();
        } catch (ProcessNotAuthorized | ProcessFailedException e) {
            setState(WidgetState.FAILED);
        }
    }
}
