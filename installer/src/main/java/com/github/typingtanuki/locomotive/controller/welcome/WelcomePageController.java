package com.github.typingtanuki.locomotive.controller.welcome;

import com.github.typingtanuki.locomotive.controller.system.SystemOverviewPpaController;
import com.github.typingtanuki.locomotive.controller.common.FooterController;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.controller.common.Navigator;
import javafx.stage.Stage;

public class WelcomePageController extends InstallerPage {

    public WelcomePageController(Stage stage) {
        super();
        setCenter(new WelcomeController());
        setBottom(new FooterController(new Navigator(stage, new SystemOverviewPpaController(stage))).getView());
    }
}
