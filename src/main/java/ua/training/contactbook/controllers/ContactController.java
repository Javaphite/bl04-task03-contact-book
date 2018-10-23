package ua.training.contactbook.controllers;

import ua.training.contactbook.localization.LocalizedField;
import ua.training.contactbook.model.entities.Address;
import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.model.exceptions.NicknameNotUniqueException;
import ua.training.contactbook.model.services.ContactService;
import ua.training.contactbook.view.ConsoleView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ua.training.contactbook.localization.LocalizedField.*;

/**
 * Controller for reading, validation and building Contact entities of given inputs.
 */
public class ContactController extends EntityController<Contact> {

    private static final String VALUES_SUFFIX = ".value";
    private static final String HINTS_SUFFIX = ".hint";
    private static final String PATTERNS_SUFFIX = ".pattern";

    private static final String FIELDS_BUNDLE_NAME = "fields";
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle(FIELDS_BUNDLE_NAME, Locale.getDefault());

    private final ContactService service;

    public ContactController(ConsoleView view, ContactService service, Scanner scanner) {
        super(view, scanner);
        this.service = service;
        initializeFields();
    }

    @Override
    public Contact prepareEntity() {
        resetFieldsValues();
        fields.values().forEach(this::demandInput);

        return buildContact();
    }

    /**
     * Makes try to add contact to storage using service associated with this controller
     * and initiates required contact changes if any troubles occurred during mentioned operation.
     * @param contact {@link Contact} to process
     */
    public void acceptContact(Contact contact) {
        try {
           service.addContact(contact);
        } catch (NicknameNotUniqueException exception) {
            System.err.println(exception.getMessage());
            System.err.println(exception);

            demandInput(getField(NICKNAME));
            contact.setNickName(getFieldValue(NICKNAME));
            acceptContact(contact);
        }
    }

    private void initializeFields() {
        fields.clear();

        for (String key: collectTopKeys()) {
            addEntityField(
                    BUNDLE.getString(key + VALUES_SUFFIX),
                    BUNDLE.getString(key + HINTS_SUFFIX),
                    Pattern.compile(BUNDLE.getString(key + PATTERNS_SUFFIX))
            );
        }
    }

    private List<String> collectTopKeys() {
        return Arrays.stream(LocalizedField.values())
                     .map(LocalizedField::toString)
                     .collect(Collectors.toList());
    }

    private Contact buildContact() {
        Contact contact = new Contact();
        contact.setLastName(getFieldValue(LAST_NAME));
        contact.setFirstName(getFieldValue(FIRST_NAME));
        contact.setMiddleName(getFieldValue(MIDDLE_NAME));
        contact.setNickName(getFieldValue(NICKNAME));
        contact.setComments(getFieldValue(COMMENTS));
        contact.setGroup(getFieldValue(GROUP));
        contact.setHomeTelephone(getFieldValue(HOME_TEL));
        contact.setMobileTelephone1(getFieldValue(MOBILE_TEL1));
        contact.setMobileTelephone2(getFieldValue(MOBILE_TEL2));
        contact.setEmail(getFieldValue(EMAIL));
        contact.setSkype(getFieldValue(SKYPE));

        Address address = new Address();
        address.setPostCode(getFieldValue(POST_CODE));
        address.setCity(getFieldValue(CITY));
        address.setStreet(getFieldValue(STREET));
        address.setBuildingNumber(getFieldValue(BUILDING));
        address.setRoomNumber(getFieldValue(ROOM));

        contact.setAddress(address);
        contact.setFullAddress(address.asFlatText());

        contact.setDateCreated(getFieldValue(DATE_CREATED));
        contact.setDateUpdated(getFieldValue(DATE_UPDATED));
        contact.setFullName(formFullName(getFieldValue(LAST_NAME), getFieldValue(FIRST_NAME)));

        return contact;
    }

    private String getFieldValue(LocalizedField field) {
        String localizedFieldName = BUNDLE.getString(field + VALUES_SUFFIX);
        return getFieldValue(localizedFieldName);
    }

    private EntityField getField(LocalizedField field) {
        String localizedFieldName = BUNDLE.getString(field + VALUES_SUFFIX);
        return getField(localizedFieldName);
    }

    private String formFullName(String lastName, String firstName) {
        StringBuilder fullname = new StringBuilder(lastName);

        fullname.append(' ')
                .append(firstName.charAt(0))
                .append('.');

        return fullname.toString();
    }
}
