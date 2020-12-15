package com.github.typingtanuki.locomotive.controller.ppa;

import com.github.typingtanuki.locomotive.controller.common.Header;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;
import com.github.typingtanuki.locomotive.ppa.Ppa;

public class PpaController extends InstallerPage {
    public PpaController(Ppa ppa) {
        super();
        setTop(new Header(ppa.getTitle(), ppa.getDescription()));
        setCenter(new PpaInstallController(ppa));
        setBottom(getFooter());
    }
}
