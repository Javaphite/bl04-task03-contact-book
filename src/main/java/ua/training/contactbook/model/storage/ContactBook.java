package ua.training.contactbook.model.storage;

import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.model.exceptions.NicknameNotUniqueException;

import java.util.HashMap;
import java.util.Map;

public class ContactBook {

    private final Map<String, Contact> contacts;

    public ContactBook() {
        contacts = new HashMap<>();
    }

    /**
     * Adds given contact to this ContactBook.
     * @param contact {@link Contact} to add
     * @throws NicknameNotUniqueException if nickname of {@code contact} already exists in storage.
     */
    public void add(Contact contact) {
        if (contacts.containsKey(contact.getNickName())) {
            throw new NicknameNotUniqueException("This nickname already in use! Please try another one.");
        }
       contacts.put(contact.getNickName(), contact);
    }

}
