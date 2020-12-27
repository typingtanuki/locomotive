package com.github.typingtanuki.locomotive.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Locale;
import java.util.Random;

public class GlitchLabel extends Label {
    private static final Random RANDOM = new Random();

    private final String text;
    private String current;

    public GlitchLabel(String text) {
        super(text);
        setStyle("-fx-font-family: monospace");
        this.text = text;
        this.current = text;
        Platform.runLater(this::animate);
    }

    private void animate() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(200),
                event -> {
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < text.length(); i++) {
                        char c = text.charAt(i);
                        switch (RANDOM.nextInt(5)) {
                            case 0:
                                out.append(c);
                                break;
                            case 1:
                                out.append(glitchFor(c));
                                break;
                            default:
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

    private char pick(char[] transform) {
        return transform[RANDOM.nextInt(transform.length)];
    }

    private char[] transform(char c) {
        String s = String.valueOf(c).toLowerCase(Locale.ENGLISH);
        switch (s) {
            case "a":
                return new char[]{'▲', '△'};
            case "b":
                return new char[]{'3', 'd'};
            case "c":
                return new char[]{'○', '❍'};
            case "d":
                return new char[]{'D', 'b'};
            case "e":
                return new char[]{'3', 'Σ'};
            case "f":
                return new char[]{'F'};
            case "g":
                return new char[]{'G', 'g'};
            case "h":
                return new char[]{'‖'};
            case "i":
                return new char[]{'|'};
            case "j":
                return new char[]{'|'};
            case "k":
                return new char[]{'◁', '◀'};
            case "l":
                return new char[]{'|', '1'};
            case "m":
                return new char[]{'W'};
            case "n":
                return new char[]{'N'};
            case "o":
                return new char[]{'0', '◎', '●'};
            case "p":
                return new char[]{'`', 'q', 'Q'};
            case "q":
                return new char[]{'p', '|', 'P'};
            case "r":
                return new char[]{'K'};
            case "s":
                return new char[]{'$', '2'};
            case "t":
                return new char[]{'|', '7'};
            case "u":
                return new char[]{'v', 'V'};
            case "v":
                return new char[]{'U', 'u'};
            case "w":
                return new char[]{'M'};
            case "x":
                return new char[]{'>', '<'};
            case "y":
                return new char[]{'|'};
            case "z":
                return new char[]{'2'};
            case " ":
            case "-":
            case "_":
                return new char[]{'_', '-'};
        }
        return new char[]{c};
    }
}
