package ua.training.contactbook;

import ua.training.contactbook.controller.ContactController;
import ua.training.contactbook.view.ConsoleView;

import java.io.IOException;

public final class Application {

    public static void main(String[] args) throws IOException {
        ConsoleView view = new ConsoleView();
        ContactController controller = new ContactController(view);
        controller.processInput();

    }
}
