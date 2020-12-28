package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;
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
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
