package com.github.typingtanuki.locomotive.steps;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStepController {
    public final List<Step> toExecute() {
        List<Step> out = new ArrayList<>();
        for (Step step : baseSteps()) {
            if (!step.isDone()) {
                out.add(step);
            }
        }
        return out;
    }

    public abstract List<Step> baseSteps();
}
