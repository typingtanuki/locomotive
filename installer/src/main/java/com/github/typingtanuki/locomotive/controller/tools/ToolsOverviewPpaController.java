package com.github.typingtanuki.locomotive.controller.tools;

import com.github.typingtanuki.locomotive.controller.common.FooterController;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.controller.common.Navigator;
import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.controller.games.GamesOverviewPpaController;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.stage.Stage;

public class ToolsOverviewPpaController extends InstallerPage {
    public ToolsOverviewPpaController(Stage stage) {
        super();
        setCenter(new StepOverviewController(CommonSettings.toolsStepController));
        setBottom(new FooterController(new Navigator(stage, new GamesOverviewPpaController(stage))).getView());
    }
}
