package com.github.typingtanuki.locomotive.controller.common;

import com.github.typingtanuki.locomotive.settings.CommonSettings;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Deque;

public final class Navigator {
    private static final Deque<InstallerPage> pages = new ArrayDeque<>();

    private static InstallerPage nextNode = null;
    private static InstallerPage previousNode = null;

    private Navigator() {
        super();
    }

    public static void addPage(OverviewController page) {
        Navigator.pages.add(page);
        Navigator.pages.addAll(page.listSubPages());
    }

    private static InstallerPage nextPage() {
        if (pages.isEmpty()) {
            return null;
        }
        return pages.pop();
    }

    public static void goToNext() {
        hasNext();

        InstallerPage toBeLoaded = nextNode;
        nextNode = null;
        previousNode = toBeLoaded;
        toBeLoaded.activated();
        Stage stage = CommonSettings.getStage();
        stage.setScene(new Scene(
                toBeLoaded,
                stage.getScene().getWidth(),
                stage.getScene().getHeight()));
        stage.show();
    }

    public static boolean hasNext() {
        if (nextNode == null) {
            nextNode = nextPage();
        }
        return nextNode != null;
    }

    public static boolean hasPrevious() {
        return previousNode != null;
    }
}
