package ua.training.contactbook.view;

/**
 * Basic view intended to display messages on standard output.
 */

public class ConsoleView {

    /**
     * Displays string message as separate line using standard output.
     * @param message  object that encapsulates message to be displayed;
     */
    public void displayMessage(Object message) {
        System.out.println(message);
    }

}
