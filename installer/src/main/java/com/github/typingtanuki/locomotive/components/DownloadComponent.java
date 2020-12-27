package com.github.typingtanuki.locomotive.components;


import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.LayoutUtils;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * A component used to display download progress
 */
public class DownloadComponent extends VBox {
    private final Label label;
    private final ProgressBar progress;

    public DownloadComponent() {
        setWidth(LayoutUtils.TERMINAL_WIDTH);
        label = new Label(I18n.get("download.start"));
        progress = new ProgressBar();
        getChildren().add(label);
        getChildren().add(progress);
    }

    /**
     * A more explicit way of expressing "no download progress"
     */
    public static DownloadComponent nullDownload() {
        return null;
    }

    /**
     * Convert bytes to human readable SI units
     */
    public static String humanReadableBytes(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }

    /**
     * Update the label and the progess bar
     *
     * @param current The number of bytes downloaded so far
     * @param total   The number of bytes total
     */
    public void updateProgress(int current, int total) {
        Platform.runLater(() -> {
            label.setText(I18n.get(
                    "download.progress",
                    humanReadableBytes(current), humanReadableBytes(total)));
            progress.setProgress(((double) current) / total);
        });
    }
}
