package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.GithubBinary;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;

public class GithubWidget extends AbstractInstallWidget {
    private UrlTargetWidget urlTargetWidget;

    public GithubWidget(GithubBinary binary,
                        UrlTargetWidget urlTargetWidget,
                        Runnable installStarts,
                        Runnable installFinished) {
        super(I18n.get("githubInstaller.title"),
                I18n.get("githubInstaller.description"),
                installStarts,
                installFinished);

        this.urlTargetWidget = urlTargetWidget;
        showInstallButton();
    }

    @Override
    protected void doInstall() {
        // TBD
        /*
          echo "Accessing github release page ${1}/${2}" >>"${logFile}" 2>&1
  releasePage=$(curl -XGET -L -s "https://github.com/${1}/${2}/releases/latest" 2>>"${logFile}")

  echo "Extracting .deb link for ${1}/${2}" >>"${logFile}" 2>&1
  debLink=$(echo "${releasePage}" | grep "\.deb\"" | sed -e 's/.*<a href=["'"'"']//i' | cut -d '"' -f 1 2>>"${logFile}")
  echo "Extracting .deb link for ${1}/${2}: ${debLink}" >>"${logFile}" 2>&1

  echo "Extracting .deb package for ${1}/${2}" >>"${logFile}" 2>&1
  packageName=$(echo "${debLink}" | cut -d '/' -f 7 2>>"${logFile}")
  echo "Extracting .deb package for ${1}/${2}: ${packageName}" >>"${logFile}" 2>&1

         */
    }
}
