package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.i18n.I18n;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;

public class Enable32BitPage extends InstallerPage {

    public Enable32BitPage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }

    @Override
    protected Node makeContent() {
        // TBD
        /*
                try {
            ProcessMonitor monitor = monitor(ProcessMonitor.class);
            ProcessExec processExec = new ProcessExec("dpkg", monitor);
            processExec.asAdmin();
            processExec.exec("--add-architecture", "i386");
            return StepState.success();
        } catch (IOException e) {
            return new StepState(e);
        }
         */
        return null;
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("enable32bit.title"),
                I18n.get("enable32bit.description"),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(false);
    }
}
