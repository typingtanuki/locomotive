package com.github.typingtanuki.locomotive.controller.system;

import com.github.typingtanuki.locomotive.controller.common.StepOverviewController;
import com.github.typingtanuki.locomotive.controller.tools.ToolsOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.common.FooterController;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.controller.common.Navigator;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.stage.Stage;

public class SystemOverviewPpaController extends InstallerPage {
    public SystemOverviewPpaController(Stage stage) {
        super();
        setCenter(new StepOverviewController(CommonSettings.systemStepController));
        setBottom(new FooterController(new Navigator(stage, new ToolsOverviewPpaController(stage))).getView());
    }
}
