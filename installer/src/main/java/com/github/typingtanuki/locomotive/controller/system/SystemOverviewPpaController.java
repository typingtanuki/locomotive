package com.github.typingtanuki.locomotive.controller.system;

import com.github.typingtanuki.locomotive.controller.component.Header;
import com.github.typingtanuki.locomotive.controller.common.OverviewController;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

public class SystemOverviewPpaController extends OverviewController {
    public SystemOverviewPpaController() {
        super(new StepOverviewController(CommonSettings.systemStepController));
        setTop(new Header(
                "overview.title", new String[0],
                "overview.description", new String[0]));
        setCenter(getOverview());
        setBottom(getFooter());
    }
}
