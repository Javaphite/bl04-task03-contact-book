package ua.training.contactbook.model.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.training.contactbook.controllers.ContactController;
import ua.training.contactbook.model.entities.Contact;
import ua.training.contactbook.model.exceptions.NicknameNotUniqueException;
import ua.training.contactbook.model.storage.ContactBook;
import ua.training.contactbook.view.ConsoleView;
import ua.training.logger.TestLifecycleLogger;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@DisplayName("ContactService")
class ContactServiceTest extends TestLifecycleLogger {

    @Test
    @Tag("addContract")
    void throwsNicknameNotUniqueException() {
        String test = getIncorrectDataExample();
        ContactController controller;
        ContactService service = new ContactService(getBookStub());
        ConsoleView view = Mockito.mock(ConsoleView.class);

        try(Scanner scanner = new Scanner(test)) {
           controller = new ContactController(view, service, scanner);
           Assertions.assertThrows(NicknameNotUniqueException.class, () -> service.addContact(controller.prepareEntity()));
        }
    }

    @Test
    @Tag("addContract")
    void notThrowsNicknameNotUniqueException() {
        String test = getCorrectDataExample();
        ContactController controller;
        ContactService service = new ContactService(getBookStub());
        ConsoleView view = Mockito.mock(ConsoleView.class);

        try(Scanner scanner = new Scanner(test)) {
            controller = new ContactController(view, service, scanner);
            Assertions.assertDoesNotThrow(() -> service.addContact(controller.prepareEntity()));
        }
    }

    private String getIncorrectDataExample() {
        String[] incorrectData = {"Сара", "Коннор", "D.",  "Sarah101", "comment", "DEFAULT",
                                    "+123121414", "+123121416", "-", "sarah@gmail.com", "sarah", "02345",
                                    "York", "Main", "3A", "12", "2010-10-10", "2010-10-10"};
        return Arrays.stream(incorrectData).collect(Collectors.joining(System.lineSeparator()));
    }

    private String getCorrectDataExample() {
        String[] correctData = {"Сара", "Коннор", "D.",  "SarahConnor", "comment", "DEFAULT",
                "+123121414", "+123121416", "-", "sarah@gmail.com", "sarah", "02345",
                "York", "Main", "3A", "12", "2010-10-10", "2010-10-10"};
        return Arrays.stream(correctData).collect(Collectors.joining(System.lineSeparator()));
    }

    private ContactBook getBookStub() {
        ContactBook book = new ContactBook();
        Contact mockContact = Mockito.mock(Contact.class);
        Mockito.when(mockContact.getNickName()).thenReturn("Sarah101");
        book.add(mockContact);
        return book;
    }
}
