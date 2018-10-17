package ua.training.contactbook.controllers;

import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.view.ConsoleView;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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
        return BUNDLE.keySet()
                .stream()
                .map(key -> (key.split("\\."))[0])
                .distinct()
                .collect(Collectors.toList());
    }

    private Contact buildContact() {
        Contact contact = new Contact();
        contact.setLastName(getFieldValue(BUNDLE.getString("first_name.value")));
        contact.setFirstName(getFieldValue(BUNDLE.getString("last_name.value")));
        /*contact.setMiddleName(getFieldValue("middle_name.value"));
        contact.setNickName("nickname.value");
        contact.setComments("comments.value");
        contact.setGroup("group.value");
        contact.setHomeTelephone(demandInput(INPUT, ValidationGroup.TELEPHONE_NUMBERS));
        contact.setMobileTelephone1(demandInput(INPUT, ValidationGroup.TELEPHONE_NUMBERS));
        contact.setMobileTelephone2(demandInput(INPUT, ValidationGroup.OPTIONAL_TELEPHONE_NUMBERS));
        contact.setEmail(demandInput(INPUT, ValidationGroup.EMAILS));
        contact.setSkype(demandInput(INPUT, ValidationGroup.SKYPE_CONTACTS));

        Address address = new Address();
        address.setPostCode(demandInput(INPUT, ValidationGroup.ZIP_CODES));
        address.setCity(demandInput(INPUT, ValidationGroup.CITY_NAMES));
        address.setStreet(demandInput(INPUT, ValidationGroup.STREET_NAMES));
        address.setBuildingNumber(demandInput(INPUT, ValidationGroup.BUILDING_NUMBERS));
        address.setRoomNumber(demandInput(INPUT, ValidationGroup.ROOM_NUMBERS));
        contact.setAddress(address);

        contact.setDateCreated(demandInput(INPUT, ValidationGroup.DATES));
        contact.setDateUpdated(demandInput(INPUT, ValidationGroup.DATES));

        StringBuilder fullname = new StringBuilder(contact.getLastName());
        fullname.append(' ')
                .append(contact.getFirstName().charAt(0))
                .append('.');
        contact.setFullName(fullname.toString());
        contact.setFullAddress(contact.getAddress().asFlatText());*/
        resetFieldsValues();
        return contact;
    }

    public enum ValidationGroup {
        NAMES("(?:\\p{Lu}(\\u0027\\p{Lu})?\\p{Ll}+)(?:(?:\\p{L}\\p{Ll}*[-\\u0020]?)+)?"),
        MIDDLE_NAMES("(?:\\p{Lu}+\\.?)|(?:\\p{Lu}\\p{Ll}*)"),
        NICKNAMES("(?:[\\p{L}\\p{S}\\p{P}\\p{N}\\w]+)+"),
        SHIELDED_TEXT("[[-\\p{L}\\p{N}\\u0020\\p{P}]&&[^\\p{Pi}\\p{Pf}]]+"),
        CONTACT_GROUPS("friend|colleague|relative|insider|other"),
        TELEPHONE_NUMBERS("\\+(?:[0-9]\\u0020?){6,14}[0-9]"),
        OPTIONAL_TELEPHONE_NUMBERS("([-]+)|\\+(?:[0-9]\\u0020?){6,14}[0-9]"),
        EMAILS("(?i)(?:[-a-z0-9~!$%^&*_=+}{\\'?])+(?:[-a-z0-9~!$%^&*_=+}{\\'?.])*(?![-\\\\.~!$%^&*_=+}{\\'?]@)@(?:[a-z0-9]+)(?:[-_\\.][a-z0-9]+)*"),
        SKYPE_CONTACTS("(?:[a-z]+[-._]?[a-z]+)+"),
        ZIP_CODES("[0-9][1-9]{4}(?:-[0-9]{4})?"),
        CITY_NAMES("(?m)^(?:\\p{Lu}\\p{Ll}+)(?:(?:[\\u0020\\u2212]\\p{L}\\p{Ll}*)+)?$"),
        STREET_NAMES("(?m)^(?:\\p{N}+\\u0020)?(?:[-\\p{L}\\p{N}.]+\\u0020?)+$"),
        BUILDING_NUMBERS("[0-9\\p{L}]+[-./]?[0-9\\p{L}]+"),
        ROOM_NUMBERS("[0-9\\p{L}]+[-./]?[0-9\\p{L}]+"),
        DATES("[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))");

        ValidationGroup(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        private final Pattern pattern;

        Pattern getPattern() {
            return pattern;
        }
    }

}
