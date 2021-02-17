package com.github.typingtanuki.locomotive.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Internationalization library
 */
public final class I18n {
    private static final Logger LOGGER = LoggerFactory.getLogger(I18n.class);

    /**
     * The resource bundle for the given locale
     */
    private static ResourceBundle bundle;
    /**
     * A fallback bundle for partial translations
     */
    private static ResourceBundle fallback;
    private static Locale locale;

    private I18n() {
        super();
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static void init() {
        if (bundle != null) {
            return;
        }

        locale = Locale.getDefault();

        switch (locale.getLanguage()) {
            case "en":
                locale = Locale.ENGLISH;
                break;
            default:
                LOGGER.warn("Unsupported language {} falling back to english.", locale.getLanguage());
                locale = Locale.ENGLISH;
        }

        bundle = ResourceBundle.getBundle("installer", locale);
        fallback = ResourceBundle.getBundle("installer", Locale.ENGLISH);
    }

    /**
     * Get a string in the current locale, or in english for fallback
     *
     * @param key  The bundle key to get the string message from
     * @param args The arguments for placeholders
     * @return The translated string with placeholders replaced
     */
    public static String get(String key, Object... args) {
        String pattern = getFrom(bundle, key);
        if (pattern == null) {
            pattern = getFrom(fallback, key);
            if (pattern == null) {
                LOGGER.error("Need to add to resource: {}", key);
                return key;
            } else {
                LOGGER.error("Missing {} in locale {}, falling back to {}", key, locale, pattern);
            }
        }

        return MessageFormat.format(pattern, args);
    }

    private static String getFrom(ResourceBundle bundle, String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }
}
