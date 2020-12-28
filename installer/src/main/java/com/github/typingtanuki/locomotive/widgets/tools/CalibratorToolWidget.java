package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibratorToolWidget extends AbstractToolWidget {

    private final List<CalibratorDevice> devices = new ArrayList<>();

    public CalibratorToolWidget(String title,
                                String description,
                                SetupToolsPage setupToolsPage) {
        super(title, description, setupToolsPage);
        init();
    }

    @Override
    protected boolean isInstalled() {
        return PackageTester.isBinaryOnPath(Binaries.xinputCalibrator());
    }

    @Override
    protected void start() {
        String command = Binaries.xinputCalibrator().getBinary();
        devices.clear();

        try {
            ProcessExec processExec = ProcessExec.exec(getTerminal(), command, "--list");
            Pattern pattern = Pattern.compile("$Device\\s\"([^\"]+)\"\\sid=(\\d+)^");
            for (String line : processExec.getStdout().split("[\r\n]]")) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    devices.add(new CalibratorDevice(matcher.group(1), matcher.group(2)));
                }
            }
            chooseDevices();
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    private void chooseDevices() {
        // TBD
        System.out.println(devices);
    }
}
