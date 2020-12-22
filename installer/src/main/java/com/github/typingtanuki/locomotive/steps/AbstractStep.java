package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.binary.Monitorable;
import com.github.typingtanuki.locomotive.controller.component.StepState;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

import java.util.Locale;
import java.util.concurrent.Future;

public abstract class AbstractStep extends Monitorable implements Step {
    @Override
    public String description() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".title";
    }

    @Override
    public String title() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".description";
    }

    @Override
    public String[] descriptionArgs() {
        return new String[0];
    }

    @Override
    public String[] titleArgs() {
        return new String[0];
    }

    @Override
    public Future<StepState> execute() {
        return CommonSettings.submitTask(this::doExecute);
    }

    protected abstract StepState doExecute();
}
