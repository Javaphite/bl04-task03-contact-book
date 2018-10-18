package ua.training.contactbook.controllers;

import ua.training.contactbook.localization.LocalizedField;
import ua.training.contactbook.model.entities.Address;
import ua.training.contactbook.model.entities.Contact;
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

    private static final String FIELDS_BUNDLE_NAME = "fields";
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle(FIELDS_BUNDLE_NAME, Locale.getDefault());

    public ContactController(ConsoleView view, Scanner scanner) {
        super(view, scanner);
        initializeFields();
    }

    @Override
    public Contact prepareEntity() {
        fields.values().forEach(this::demandInput);
        return buildContact();
    }

    private void initializeFields() {
        fields.clear();

        for (String key: collectTopKeys()) {
            addEntityField(
                    BUNDLE.getString(key + ".value"),
                    BUNDLE.getString(key + ".hint"),
                    Pattern.compile(BUNDLE.getString(key + ".pattern"))
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

        StringBuilder fullname = new StringBuilder(getFieldValue(LAST_NAME));
        fullname.append(' ')
                .append(getFieldValue(FIRST_NAME).charAt(0))
                .append('.');
        contact.setFullName(fullname.toString());

        resetFieldsValues();
        return contact;
    }

    private String getFieldValue(LocalizedField field) {
        String localizedFieldName = BUNDLE.getString(field + ".value");
        return getFieldValue(localizedFieldName);
    }
}
