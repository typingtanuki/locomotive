package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.steps.Step;

public class BasicStepController extends InstallerPage {
    public BasicStepController(Step step) {
        super();
        setTop(new Header(step.title(), step.description()));
        setCenter(new BasicStepInstallController(step));
        setBottom(getFooter());
    }
}
