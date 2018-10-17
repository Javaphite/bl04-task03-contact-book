package ua.training.contactbook.controllers;

import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.view.ConsoleView;

import java.util.Scanner;

import static ua.training.contactbook.view.Message.*;

public class BaseController {

    ConsoleView view;

    public BaseController(ConsoleView view) {
        this.view = view;
    }

    public void process() {
        view.displayMessage(INTRODUCTION);
        view.displayMessage(NEW_CONTACT);

        Scanner scanner = new Scanner(System.in);
        Contact contact = new ContactController(view, scanner).prepareEntity();

        view.displayMessage(SHOW_CONTACT);
        view.displayMessage(contact);
    }
}
