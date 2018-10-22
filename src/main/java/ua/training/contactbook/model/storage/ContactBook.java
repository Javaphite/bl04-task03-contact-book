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

    // TODO: add JavaDoc
    public void add(Contact contact) throws NicknameNotUniqueException {
        if (contacts.containsKey(contact.getNickName())) {
            throw new NicknameNotUniqueException("This nickname already in use! Please try another one.");
        }
       contacts.put(contact.getNickName(), contact);
    }

}
