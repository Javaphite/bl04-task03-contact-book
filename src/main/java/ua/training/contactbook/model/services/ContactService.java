package ua.training.contactbook.model.services;

import ua.training.contactbook.model.storage.ContactBook;
import ua.training.contactbook.model.entities.Contact;

public class ContactService {

    private final ContactBook book;

    public ContactService(ContactBook book) {
        this.book = book;
    }

    // TODO: add JavaDoc
    public void addContact(Contact contact) {
        book.add(contact);
    }
}
