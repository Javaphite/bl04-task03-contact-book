package ua.training.contactbook.view;

import ua.training.contactbook.localization.Message;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Basic view intended to display messages on standard output.
 */
public class ConsoleView {

    private static final String MESSAGES_BUNDLE_NAME = "messages";

    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, Locale.getDefault());

    /**
     * Displays string message as separate line using standard output.
     * @param object object that encapsulates message to be displayed;
     */
    public void displayMessage(Object object) {
        System.out.println(object);
    }

    /**
     * Displays localized message from resource bundle.
     * @param message message instance that encapsulates key value
     *                for localized message text in bundle
     */
    public void displayMessage(Message message) {
        if (BUNDLE.containsKey(message.toString())) {
            displayMessage(BUNDLE.getString(message.toString()));
        }
    }

    /**
     * Displays message builded of template with placeholders and given values.
     * @param messageTemplate template string with placeholders
     * @param field field name to be build in message
     * @param hint hint string to be build in message
     */
    public void displayMessage(Message messageTemplate, String field, String hint) {
        displayMessage(buildMessage(messageTemplate, field, hint));
    }

    private String buildMessage(Message messageTemplate, String field, String hint) {
        String message = BUNDLE.getString(messageTemplate.toString());

        if (BUNDLE.containsKey("placeholders.field")) {
           message = message.replaceAll(BUNDLE.getString("placeholders.field"), field);
        }

        if (BUNDLE.containsKey("placeholders.hint")) {
           message = message.replaceAll(BUNDLE.getString("placeholders.hint"), hint);
        }

        return message;
    }

}
