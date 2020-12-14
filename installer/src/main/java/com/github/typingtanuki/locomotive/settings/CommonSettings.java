package com.github.typingtanuki.locomotive.settings;

import javafx.scene.image.Image;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommonSettings {
    private static Locale currentLocale;
    private static ResourceBundle bundle;

    public static Locale locale() {
        if (currentLocale != null) {
            return currentLocale;
        }
        Locale locale = Locale.getDefault();

        switch (locale.getLanguage()) {
            case "en":
                locale = Locale.ENGLISH;
                break;
            default:
                System.out.println("Unsupported language " + locale.getLanguage() + " falling back to english.");
                locale = Locale.ENGLISH;
        }

        currentLocale = locale;
        return currentLocale;
    }

    public static ResourceBundle bundle() {
        bundle = ResourceBundle.getBundle("installer", CommonSettings.locale());
        return bundle;
    }

    public static Image loadImage(String resourcePath) {
        return new Image(CommonSettings.class.getResource(resourcePath).toExternalForm());
    }

    public static String css() {
        return CommonSettings.class.getResource("/style.css").toExternalForm();
    }
}
