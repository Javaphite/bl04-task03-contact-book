package ua.training.contactbook.controller;

import ua.training.contactbook.model.entities.Address;
import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.view.ConsoleView;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Controller class for GameOfNumberModel.
 */
public class ContactController {

    private final Scanner scanner;

    private ConsoleView view;

    public ContactController(ConsoleView view) {
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void processInput() {
        view.displayMessage(Message.INTRODUCTION);
        view.displayMessage(Message.NEW_CONTACT);

        Contact contact = new Contact();
        contact.setLastName(demandInput(Message.LAST_NAME, ValidationGroup.NAMES));
        contact.setFirstName(demandInput(Message.FIRST_NAME, ValidationGroup.NAMES));
        contact.setMiddleName(demandInput(Message.MIDDLE_NAME, ValidationGroup.MIDDLE_NAMES));
        contact.setNickName(demandInput(Message.NICKNAME, ValidationGroup.NICKNAMES));
        contact.setComments(demandInput(Message.COMMENTS, ValidationGroup.SHIELDED_TEXT));
        contact.setGroup(demandInput(Message.GROUP, ValidationGroup.CONTACT_GROUPS));
        contact.setHomeTelephone(demandInput(Message.TEL_HOME, ValidationGroup.TELEPHONE_NUMBERS));
        contact.setMobileTelephone1(demandInput(Message.TEL_MOBILE_1ST, ValidationGroup.TELEPHONE_NUMBERS));
        contact.setMobileTelephone2(demandInput(Message.TEL_MOBILE_2ND, ValidationGroup.OPTIONAL_TELEPHONE_NUMBERS));
        contact.setEmail(demandInput(Message.EMAIL, ValidationGroup.EMAILS));
        contact.setSkype(demandInput(Message.SKYPE, ValidationGroup.SKYPE_CONTACTS));

        Address address = new Address();
        address.setPostCode(demandInput(Message.POST_CODE, ValidationGroup.ZIP_CODES));
        address.setCity(demandInput(Message.CITY, ValidationGroup.CITY_NAMES));
        address.setStreet(demandInput(Message.STREET, ValidationGroup.STREET_NAMES));
        address.setBuildingNumber(demandInput(Message.BUILDING, ValidationGroup.BUILDING_NUMBERS));
        address.setRoomNumber(demandInput(Message.ROOM, ValidationGroup.ROOM_NUMBERS));
        contact.setAddress(address);

        contact.setDateCreated(demandInput(Message.DATE_CREATED, ValidationGroup.DATES));
        contact.setDateUpdated(demandInput(Message.DATE_UPDATED, ValidationGroup.DATES));

        StringBuilder fullname = new StringBuilder(contact.getLastName());
        fullname.append(' ')
                .append(contact.getFirstName().charAt(0))
                .append('.');
        contact.setFullName(fullname.toString());
        contact.setFullAddress(contact.getAddress().asFlatText());

        view.displayMessage(Message.CONTACT_INFO);
        view.displayMessage(contact);
    }

    private String demandInput(Message message, ValidationGroup validationGroup) {
        view.displayMessage(message);
        Pattern pattern = validationGroup.getPattern();

        while(!scanner.hasNext(pattern)) {
            if(scanner.hasNextLine()) {
                view.displayMessage(Message.INCORRECT_INPUT);
                scanner.nextLine();
            }
        }

        return scanner.next(pattern).trim();
    }

    enum Message {
        INTRODUCTION("Welcome to Muhosransk city police department anonymous informants database."),
        NEW_CONTACT("Please enter any information about Your contact You will be asked for."),
        INCORRECT_INPUT("Incorrect input! Check Yourself and try again:"),
        FIRST_NAME("First name:"),
        LAST_NAME("Last name:"),
        MIDDLE_NAME("Middle name:"),
        NICKNAME("Nickname:"),
        COMMENTS("Additional information:"),
        GROUP("Contact group (friend, colleague, relative, insider or other):"),
        TEL_HOME("Home telephone number:"),
        TEL_MOBILE_1ST("Main mobile number:"),
        TEL_MOBILE_2ND("Additional mobile number (optional):"),
        EMAIL("E-mail:"),
        SKYPE("Skype:"),
        POST_CODE("Postal code:"),
        CITY("City:"),
        STREET("Street:"),
        BUILDING("Building number:"),
        ROOM("Room number:"),
        DATE_CREATED("Date of creation (yyyy-mm-dd):"),
        DATE_UPDATED("Date of last information update (yyyy-mm-dd):"),
        CONTACT_INFO("INFORMATION ABOUT CONTACT");

        Message(String text) {
            this.text = text;
        }

        private final String text;

        @Override
        public String toString() {
            return text;
        }
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
