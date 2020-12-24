package com.github.typingtanuki.locomotive.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class I18n {
    private static final Logger LOGGER = LoggerFactory.getLogger(I18n.class);

    private static ResourceBundle bundle;

    private I18n() {
        super();
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static void init() {
        if (bundle != null) {
            return;
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

        bundle = ResourceBundle.getBundle("installer", locale);
    }

    public static String get(String key, Object... args) {
        try {
            String pattern = bundle.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (MissingResourceException e) {
            LOGGER.error("Need to ressourcify: {}", key);
            return key;
        }
    }
}
