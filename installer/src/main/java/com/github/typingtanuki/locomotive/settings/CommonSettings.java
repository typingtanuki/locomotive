package com.github.typingtanuki.locomotive.settings;

import com.github.typingtanuki.locomotive.steps.games.GamesStepController;
import com.github.typingtanuki.locomotive.steps.system.SystemStepController;
import com.github.typingtanuki.locomotive.steps.tools.ToolsStepController;
import javafx.scene.image.Image;

import java.util.Locale;
import java.util.ResourceBundle;

public final class CommonSettings {
    private static Locale currentLocale;
    private static ResourceBundle bundle;

    public static final SystemStepController systemStepController = new SystemStepController();
    public static final ToolsStepController toolsStepController = new ToolsStepController();
    public static final GamesStepController gamesStepController = new GamesStepController();

    private CommonSettings() {
        super();
    }

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
