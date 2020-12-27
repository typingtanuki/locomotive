package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.GithubBinary;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.DownloadUtils;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GithubWidget extends AbstractInstallWidget {
    private final GithubBinary binary;
    private final UrlTargetWidget urlTargetWidget;

    public GithubWidget(GithubBinary binary,
                        UrlTargetWidget urlTargetWidget,
                        Runnable installStarts,
                        Runnable installFinished) {
        super(I18n.get("githubInstaller.title"),
                I18n.get("githubInstaller.description"),
                installStarts,
                installFinished);

        this.binary = binary;
        this.urlTargetWidget = urlTargetWidget;
        showInstallButton();
        setState(WidgetState.MISSING);
    }

    @Override
    protected String actionButtonName() {
        return I18n.get("searchRelease");
    }

    @Override
    protected void doInstall() {
        try {
            // Accessing the page
            String fullPage = DownloadUtils.inString(
                    "https://github.com/" + binary.getRepoPath() + "/releases/latest",
                    getDownload());

            // Searching for the .deb link
            String debPackage = null;
            Pattern DEB_PATTERN = Pattern.compile(".*<a href=\"([^\"]+\\.deb)\".*");
            for (String line : fullPage.split("[\r\n]")) {
                Matcher debMatcher = DEB_PATTERN.matcher(line);
                if (debMatcher.matches()) {
                    debPackage = debMatcher.group(1);
                }
            }
            if (debPackage == null) {
                DialogUtils.showErrorDialog(new IllegalStateException(
                        "Could not find deb package on release page " + binary.getRepoPath()));
            }

            // Pass the link to the downloader
            urlTargetWidget.setUrlTarget("https://www.github.com" + debPackage);
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
