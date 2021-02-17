package com.github.typingtanuki.locomotive.components;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * A label with glitchy text
 */
public class GlitchLabel extends Label {
    private static final List<Timeline> TIMELINES = new ArrayList<>();

    private static final int MAX_LENGTH = 75;

    protected static final Random RANDOM = new Random();
    /**
     * Percentage of characters with will appear glitchy
     */
    private static final int GLITCH_RATIO = 10;
    /**
     * Percentage of a glitchy character from the base type to clear up
     */
    private static final int UNGLITCH_RATIO = 10;
    private static final char BLANK_CHAR = '+';

    /**
     * The clear text to display
     */
    private final String core;
    private final boolean keepGlitching;

    /**
     * A glitchy version of the text, slowly clearing up
     */
    private String text;
    /**
     * The currently displayed string
     */
    private String current;

    public GlitchLabel(String text) {
        this(text, false);
    }

    public GlitchLabel(String text, boolean keepGlitching) {
        this(text, keepGlitching, keepGlitching ? 2D : 0.5D);
    }

    public GlitchLabel(String text, boolean keepGlitching, double glowLevel) {
        super(text);

        setEffect(new Glow(glowLevel));

        this.keepGlitching = keepGlitching;

        this.core = text;
        this.text = initialString(text, keepGlitching);
        this.current = this.text;
        setText(current);
        Platform.runLater(this::animate);
    }

    private static String initialString(String text, boolean keepGlitching) {
        if (keepGlitching) {
            return text;
        }

        StringBuilder glitch = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            glitch.append(glitchFor(GlitchCharacter.random().getValue()));
        }
        if (!text.contains("\n")) {
            for (int i = text.length(); i < MAX_LENGTH; i++) {
                glitch.append(glitchFor(GlitchCharacter.random().getValue()));
            }
        }
        return glitch.toString();
    }

    public static void cancelAll() {
        synchronized (TIMELINES) {
            for (Timeline timeline : TIMELINES) {
                timeline.stop();
            }
            TIMELINES.clear();
        }
    }

    /**
     * Start the glitch animation
     */
    private void animate() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame;
        if (keepGlitching) {
            keyFrame = glitch();
        } else {
            keyFrame = unglitch(timeline);
        }
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        synchronized (TIMELINES) {
            TIMELINES.add(timeline);
        }
        timeline.play();
    }

    private KeyFrame unglitch(Timeline timeline) {
        return new KeyFrame(
                Duration.millis(40),
                event -> {
                    boolean finished = true;
                    boolean fixed = false;
                    int lastGlitch = -1;

                    // Build a string with some glitchy characters
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < text.length(); i++) {
                        char ca;
                        if (i < core.length()) {
                            ca = core.charAt(i);
                        } else {
                            ca = BLANK_CHAR;
                        }
                        char cb = text.charAt(i);
                        if (ca != cb) {
                            finished = false;
                        } else {
                            lastGlitch = i;
                        }
                        if (RANDOM.nextInt(UNGLITCH_RATIO) == 0) {
                            if (ca != BLANK_CHAR) {
                                out.append(ca);
                            }
                            fixed = true;
                        } else {
                            out.append(cb);
                        }
                    }

                    if (!finished && !fixed && lastGlitch >= 0 && lastGlitch < core.length()) {
                        out.setCharAt(lastGlitch, core.charAt(lastGlitch));
                    }

                    text = out.toString().stripTrailing();
                    setText(text);

                    if (finished) {
                        timeline.stop();
                        synchronized (TIMELINES) {
                            TIMELINES.remove(timeline);
                        }
                    }
                });
    }

    private KeyFrame glitch() {
        return new KeyFrame(
                Duration.millis(150),
                event -> {
                    // Build a string with some glitchy characters
                    StringBuilder out = new StringBuilder();
                    for (int i = 0; i < text.length(); i++) {
                        char ca;
                        if (i < core.length()) {
                            ca = core.charAt(i);
                        } else {
                            ca = BLANK_CHAR;
                        }

                        char cb = text.charAt(i);
                        if (RANDOM.nextInt(UNGLITCH_RATIO) == 0) {
                            if (ca != BLANK_CHAR) {
                                out.append(ca);
                            }
                        } else {
                            out.append(cb);
                        }
                    }
                    text = out.toString().stripTrailing();
                    out.setLength(0);

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
    }

    private static char glitchFor(char c) {
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
    private static char pick(char[] transform) {
        return transform[RANDOM.nextInt(transform.length)];
    }

    /**
     * Find a glitchy character corresponding to the character we want to replace
     */
    private static char[] transform(char c) {
        String s = String.valueOf(c).toLowerCase(Locale.ENGLISH);
        GlitchCharacter g = GlitchCharacter.from(s.charAt(0));

        switch (g) {
            case a:
                return new char[]{'▲', '△', '@', '^', 'ª'};
            case b:
                return new char[]{'3', 'd', 'ß'};
            case c:
                return new char[]{'○', '❍', '(', '¢', '©'};
            case d:
                return new char[]{'D', 'b', ')'};
            case n3:
            case e:
                return new char[]{'3', '&'};
            case f:
                return new char[]{'F', 'ƒ'};
            case n6:
            case n9:
            case g:
                return new char[]{'G', 'g', '%'};
            case h:
                return new char[]{'y', 'Y'};
            case n1:
            case i:
                return new char[]{'|', ';', ':', '¦'};
            case j:
                return new char[]{'|', ';', '¦'};
            case k:
                return new char[]{'«', '<'};
            case n7:
            case l:
                return new char[]{'|', '1'};
            case m:
                return new char[]{'W'};
            case n:
                return new char[]{'N', 'H'};
            case n0:
            case o:
                return new char[]{'0'};
            case p:
                return new char[]{'`', 'q', 'Q', '%'};
            case q:
                return new char[]{'p', '|', 'P', '%', '&'};
            case r:
                return new char[]{'K'};
            case n2:
            case n5:
            case n8:
            case s:
                return new char[]{'$', '2'};
            case t:
                return new char[]{'7', '+'};
            case u:
                return new char[]{'v', 'V'};
            case v:
                return new char[]{'U', 'u'};
            case w:
                return new char[]{'M', '丸'};
            case x:
                return new char[]{'×'};
            case y:
                return new char[]{'|', 'Ÿ', '¥'};
            case z:
                return new char[]{'2', 'Ž', 'ž'};
            case SPACE:
            case DASH:
            case UNDER:
                return new char[]{'_', '-', '~'};
            case DOT:
                return new char[]{'＿', 'ー', '〜'};
            case ONO:
                return new char[]{'斧', '父', '方'};
            case SEKI:
                return new char[]{'間', '門'};
        }
        return new char[]{c};
    }
}
