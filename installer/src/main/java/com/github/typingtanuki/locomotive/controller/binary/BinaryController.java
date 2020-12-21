package com.github.typingtanuki.locomotive.controller.binary;

import com.github.typingtanuki.locomotive.controller.component.Header;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.steps.AbstractPackageStep;

public class BinaryController extends InstallerPage {
    private final BinaryInstallController controller;

    public BinaryController(AbstractPackageStep packageStep) {
        super();
        setTop(new Header(
                packageStep.title(), packageStep.titleArgs(),
                packageStep.description(), packageStep.descriptionArgs()));
        controller = new BinaryInstallController(packageStep.getBinary(), packageStep.getPpa());
        setCenter(controller);
        setBottom(getFooter());
    }

    @Override
    public void activated() {
        super.activated();
        controller.activated();
    }
}