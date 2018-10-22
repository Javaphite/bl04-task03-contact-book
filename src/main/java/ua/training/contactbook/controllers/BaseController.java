package ua.training.contactbook.controllers;

import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.model.services.ContactService;
import ua.training.contactbook.view.ConsoleView;

import java.util.Scanner;

import static ua.training.contactbook.localization.Message.*;

/**
 * Controller for base input processing logic implementation.
 */
public class BaseController {

    private final ConsoleView view;
    private final ContactService service;

    public BaseController(ConsoleView view, ContactService service) {
        this.view = view;
        this.service = service;
    }

    // TODO: add JavaDoc
    public void process() {
        view.displayMessage(INTRODUCTION);
        view.displayMessage(NEW_CONTACT);

        try (Scanner scanner = new Scanner(System.in)) {
            ContactController contactController = new ContactController(view, service, scanner);
            Contact contact = contactController.prepareEntity();
            contactController.acceptContact(contact);

            view.displayMessage(SHOW_CONTACT);
            view.displayMessage(contact);
        }
    }
}
