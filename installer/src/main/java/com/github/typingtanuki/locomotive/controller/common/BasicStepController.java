package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.controller.component.Header;
import com.github.typingtanuki.locomotive.steps.Step;

public class BasicStepController extends InstallerPage {
    public BasicStepController(Step step) {
        super();
        setTop(new Header(
                step.title(), step.titleArgs(),
                step.description(), step.descriptionArgs()));
        setCenter(new BasicStepInstallController(step));
        setBottom(getFooter());
    }
}
