package com.github.typingtanuki.locomotive.settings;

import com.github.typingtanuki.locomotive.steps.games.GamesStepController;
import com.github.typingtanuki.locomotive.steps.system.SystemStepController;
import com.github.typingtanuki.locomotive.steps.tools.ToolsStepController;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class CommonSettings {
    public static final SystemStepController systemStepController = new SystemStepController();
    public static final ToolsStepController toolsStepController = new ToolsStepController();
    public static final GamesStepController gamesStepController = new GamesStepController();
    private static Locale currentLocale;
    private static ResourceBundle bundle;
    private static Stage stage;


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
        if (bundle != null) {
            return bundle;
        }
        bundle = ResourceBundle.getBundle("installer", CommonSettings.locale());
        return bundle;
    }


    public static String bundle(String key, Object... args) {
        ResourceBundle bundle = bundle();
        try {
            String pattern = bundle.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (MissingResourceException e) {
            System.out.println("Need to ressourcify: " + key);
            return key;
        }
    }

    public static Image loadImage(String resourcePath) {
        return new Image(CommonSettings.class.getResource(resourcePath).toExternalForm());
    }

    public static String css() {
        return CommonSettings.class.getResource("/style.css").toExternalForm();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CommonSettings.stage = stage;
    }
}
