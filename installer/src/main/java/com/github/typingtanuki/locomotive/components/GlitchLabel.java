package com.github.typingtanuki.locomotive.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Locale;
import java.util.Random;

/**
 * A label with glitchy text
 */
public class GlitchLabel extends Label {
    private static final Random RANDOM = new Random();
    /**
     * Ratio of normal chars/glitch
     * (1: All chars are glitch; 2: 50% of chars; ...)
     */
    private static final int GLITCH_RATIO = 10;

    private final String text;
    private String current;

    public GlitchLabel(String text) {
        super(text);
        setStyle("-fx-font-family: monospace");
        this.text = text;
        this.current = text;
        Platform.runLater(this::animate);
    }

    /**
     * Start the glitch animation
     */
    private void animate() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(200),
                event -> {
                    // Build a string with some glitchy characters
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        if (RANDOM.nextInt(GLITCH_RATIO) == 0) {
                            // Glitch
                            out.append(glitchFor(c));
                        } else {
                            // Normal character
                            out.append(c);
                        }
                    }
                    current = out.toString();
                    setText(current);
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private char glitchFor(char c) {
        switch (RANDOM.nextInt(5)) {
            case 0:
                return String.valueOf(c).toLowerCase(Locale.ENGLISH).charAt(0);
            case 1:
                return String.valueOf(c).toUpperCase(Locale.ENGLISH).charAt(0);
            default:
                return pick(transform(c));
        }
    }

    /**
     * Pick a character from the array
     */
    private char pick(char[] transform) {
        return transform[RANDOM.nextInt(transform.length)];
    }

    /**
     * Find a glitchy character corresponding to the character we want to replace
     */
    private char[] transform(char c) {
        String s = String.valueOf(c).toLowerCase(Locale.ENGLISH);
        switch (s) {
            case "a":
                return new char[]{'▲', '△', '@', '^', 'ª'};
            case "b":
                return new char[]{'3', 'd', 'ß'};
            case "c":
                return new char[]{'○', '❍', '(', '¢', '©'};
            case "d":
                return new char[]{'D', 'b', ')'};
            case "e":
                return new char[]{'3', 'Σ', '&', '€'};
            case "f":
                return new char[]{'F', 'ƒ'};
            case "g":
                return new char[]{'G', 'g', '%'};
            case "h":
                return new char[]{'‖', '‡'};
            case "i":
                return new char[]{'|', ';', ':', '¡', '¦'};
            case "j":
                return new char[]{'|', ';', '¦'};
            case "k":
                return new char[]{'◁', '◀', '‡', '«'};
            case "l":
                return new char[]{'|', '1', '£'};
            case "m":
                return new char[]{'W'};
            case "n":
                return new char[]{'N', 'ñ'};
            case "o":
                return new char[]{'0', '◎', '●', '*', '¤'};
            case "p":
                return new char[]{'`', 'q', 'Q', '%', '&', '¶', 'Þ'};
            case "q":
                return new char[]{'p', '|', 'P', '%', '&', '¶'};
            case "r":
                return new char[]{'K', '®'};
            case "s":
                return new char[]{'$', '2', 'Š', 'š', '§'};
            case "t":
                return new char[]{'|', '7', '+', '†'};
            case "u":
                return new char[]{'v', 'V', 'µ'};
            case "v":
                return new char[]{'U', 'u'};
            case "w":
                return new char[]{'M'};
            case "x":
                return new char[]{'>', '<'};
            case "y":
                return new char[]{'|', 'Ÿ', '¥'};
            case "z":
                return new char[]{'2', 'Ž', 'ž'};
            case " ":
            case "-":
            case "_":
                return new char[]{'_', '-', '~', '¯'};
        }
        return new char[]{c};
    }
}
