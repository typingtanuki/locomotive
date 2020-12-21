package com.github.typingtanuki.locomotive.controller.ppa;

import com.github.typingtanuki.locomotive.controller.component.Header;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.steps.AbstractPpaStep;

public class PpaController extends InstallerPage {
    private final PpaInstallController controller;

    public PpaController(AbstractPpaStep ppaStep) {
        super();
        setTop(new Header(
                ppaStep.title(), ppaStep.titleArgs(),
                ppaStep.description(), ppaStep.descriptionArgs()));
        controller = new PpaInstallController(ppaStep.getPpa());
        setCenter(controller);
        setBottom(getFooter());
    }

    public void activated() {
        super.activated();
        controller.activated();
    }
}
