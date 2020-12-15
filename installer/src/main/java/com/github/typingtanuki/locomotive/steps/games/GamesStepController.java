package com.github.typingtanuki.locomotive.steps.games;

import com.github.typingtanuki.locomotive.steps.AbstractStepController;
import com.github.typingtanuki.locomotive.steps.Step;

import java.util.Arrays;
import java.util.List;

public class GamesStepController extends AbstractStepController {
    @Override
    public List<Step> baseSteps() {
        return Arrays.asList(
                new GamehubStep(),
                new SteamStep(),
                new ItchStep(),
                new RetroarchStep());
    }
}
