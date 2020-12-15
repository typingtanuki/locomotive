package com.github.typingtanuki.locomotive.controller.tools;

import com.github.typingtanuki.locomotive.controller.common.OverviewController;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

public class ToolsOverviewPpaController extends OverviewController {
    public ToolsOverviewPpaController() {
        super(new StepOverviewController(CommonSettings.toolsStepController));
        setCenter(getOverview());
        setBottom(getFooter());
    }
}
