package ua.training.contactbook;

import ua.training.contactbook.controllers.BaseController;
import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.model.services.ContactService;
import ua.training.contactbook.model.storage.ContactBook;
import ua.training.contactbook.view.ConsoleView;

import java.util.Arrays;

/**
 * Enter point for Contact form processing application.
 */
public final class Application {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        ContactBook book = new ContactBook();
        Contact[] initialContacts = new Contact[2];
        initialContacts[0].setNickName("Quizer");
        initialContacts[1].setNickName("JohnyCatsville");
        Arrays.stream(initialContacts).forEach(book::add);

        ContactService service = new ContactService(book);
        BaseController controller = new BaseController(view, service);

        controller.process();
    }
}
