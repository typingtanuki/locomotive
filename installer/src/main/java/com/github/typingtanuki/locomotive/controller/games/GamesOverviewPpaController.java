package com.github.typingtanuki.locomotive.controller.games;

import com.github.typingtanuki.locomotive.controller.common.FooterController;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.controller.common.Navigator;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.stage.Stage;

public class GamesOverviewPpaController extends InstallerPage {
    public GamesOverviewPpaController(Stage stage) {
        super();
        setCenter(new StepOverviewController(CommonSettings.gamesStepController));
        setBottom(new FooterController(new Navigator(stage, null)).getView());
    }
}
