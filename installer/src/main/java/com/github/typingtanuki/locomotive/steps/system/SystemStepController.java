package com.github.typingtanuki.locomotive.steps.system;

import com.github.typingtanuki.locomotive.steps.AbstractStepController;
import com.github.typingtanuki.locomotive.steps.Step;

import java.util.Arrays;
import java.util.List;

public class SystemStepController extends AbstractStepController {
    public SystemStepController() {
        super();
    }

    @Override
    public List<Step> baseSteps() {
        return Arrays.asList(
                new Enable32BitStep(),
                new EnableMultiverseStep(),
                new EnablePpaXSwatStep(),
                new EnablePpaOibafStep(),
                new EnablePpaKubuntuUpdatesStep(),
                new EnablePpaKubuntuBackportStep(),
                new BuildEssentialsStep());
    }
}
