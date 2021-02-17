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
    private static final Pattern DEB_PATTERN = Pattern.compile("<a href=\"([^\"]+\\.deb)\"");
    private static final Pattern VERSION_PATTERN = Pattern.compile("/download/([^/]+)/");

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

        if (!urlTargetWidget.isDownloaded()) {
            showInstallButton();
            setState(WidgetState.MISSING);
        } else {
            setState(WidgetState.INSTALLED);
        }
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
            for (String line : fullPage.split("[\r\n]")) {
                Matcher debMatcher = DEB_PATTERN.matcher(line);
                if (debMatcher.find()) {
                    debPackage = debMatcher.group(1);
                }
            }
            if (debPackage == null) {
                DialogUtils.showErrorDialog(new IllegalStateException(
                        "Could not find deb package on release page " + binary.getRepoPath()));
            }

            // Pass the link to the downloader
            urlTargetWidget.setTarget("https://www.github.com" + debPackage, extractVersion(debPackage));
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    private String extractVersion(String url) {
        Matcher matcher = VERSION_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Could not find version in " + url);
    }
}
