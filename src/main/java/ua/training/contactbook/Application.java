package ua.training.contactbook;

import ua.training.contactbook.controllers.BaseController;
import ua.training.contactbook.view.ConsoleView;

import java.io.IOException;

/**
 * Enter point for Contact form processing application.
 */
public final class Application {

    public static void main(String[] args) throws IOException {
        ConsoleView view = new ConsoleView();
        BaseController controller = new BaseController(view);
        controller.process();
    }
}
