package com.github.typingtanuki.locomotive.steps.tools;

import com.github.typingtanuki.locomotive.steps.AbstractStepController;
import com.github.typingtanuki.locomotive.steps.Step;

import java.util.Arrays;
import java.util.List;

public class ToolsStepController extends AbstractStepController {
    @Override
    public List<Step> baseSteps() {
        return Arrays.asList(
                new WineStep(),
                new OnBoardStep(),
                new KodiStep(),
                new AntimicroxStep());
    }
}
