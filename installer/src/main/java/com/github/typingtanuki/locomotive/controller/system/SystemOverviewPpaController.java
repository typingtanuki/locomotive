package com.github.typingtanuki.locomotive.controller.system;

import com.github.typingtanuki.locomotive.controller.common.OverviewController;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

public class SystemOverviewPpaController extends OverviewController {
    public SystemOverviewPpaController() {
        super(new StepOverviewController(CommonSettings.systemStepController));
        setCenter(getOverview());
        setBottom(getFooter());
    }
}
