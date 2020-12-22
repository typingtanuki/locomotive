package com.github.typingtanuki.locomotive.settings;

import com.github.typingtanuki.locomotive.steps.games.GamesStepController;
import com.github.typingtanuki.locomotive.steps.system.SystemStepController;
import com.github.typingtanuki.locomotive.steps.tools.ToolsStepController;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

public final class CommonSettings {
    public static final SystemStepController systemStepController = new SystemStepController();
    public static final ToolsStepController toolsStepController = new ToolsStepController();
    public static final GamesStepController gamesStepController = new GamesStepController();
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonSettings.class);
    private static Locale currentLocale;
    private static ResourceBundle bundle;
    private static Stage stage;
    private static ExecutorService executor;


    private CommonSettings() {
        super();
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
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
                LOGGER.warn("Unsupported language {} falling back to english.", locale.getLanguage());
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
            LOGGER.error("Need to ressourcify: {}", key);
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

    public static void setExecutor(ExecutorService executor) {
        CommonSettings.executor = executor;
    }

    public static <T> CompletableFuture<T> submitTask(Callable<T> callable) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return callable.call();
            } catch (Exception e) {
                DialogUtils.showErrorDialog(e);
                return null;
            }
        }, executor);
    }

    public static void runInGui(Runnable callable) {
        Platform.runLater(callable);
    }

    public static <T, U> CompletableFuture<U> chainTask(CompletableFuture<T> previous, Function<T, U> func) {
        return previous.thenApplyAsync((a) -> {
            try {
                return func.apply(a);
            } catch (Exception e) {
                DialogUtils.showErrorDialog(e);
                return null;
            }
        }, executor);
    }
}
