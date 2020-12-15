package com.github.typingtanuki.locomotive.controller.games;

import com.github.typingtanuki.locomotive.controller.common.OverviewController;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

public class GamesOverviewPpaController extends OverviewController {

    public GamesOverviewPpaController() {
        super(new StepOverviewController(CommonSettings.gamesStepController));
        setCenter(getOverview());
        setBottom(getFooter());
    }
}
