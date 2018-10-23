package ua.training.contactbook.model.services;

import ua.training.contactbook.model.storage.ContactBook;
import ua.training.contactbook.model.entities.Contact;

public class ContactService {

    private final ContactBook book;

    public ContactService(ContactBook book) {
        this.book = book;
    }

    /**
     * Sends given contact to {@link ContactBook} associated with this ContactService.
     * @param contact {@link Contact} to be added to book
     */
    public void addContact(Contact contact) {
        book.add(contact);
    }
}
