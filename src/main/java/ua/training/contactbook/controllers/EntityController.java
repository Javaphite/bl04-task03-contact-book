package ua.training.contactbook.controllers;

import ua.training.contactbook.view.ConsoleView;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import static ua.training.contactbook.view.Message.INCORRECT_INPUT;
import static ua.training.contactbook.view.Message.INPUT;

public abstract class EntityController<T> {

    protected final Map<String, EntityField> fields;
    protected final Scanner scanner;

    protected ConsoleView view;

    public EntityController(ConsoleView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
        this.fields = new TreeMap<>();
    }

    public abstract T prepareEntity();

    protected void addEntityField(String name, String hint, Pattern pattern) {
        fields.put(name, new EntityField(name, hint, pattern));
    }

    protected void resetFieldsValues() {
        fields.values()
                .forEach(entityField -> entityField.setValue(null));
    }

    protected String getFieldValue(String fieldName) {
        return fields.get(fieldName).getValue();
    }

    protected void demandInput(EntityField field) {
        Pattern pattern = field.getPattern();

        view.displayMessage(INPUT, field.getName(), field.getHint());
        while(!scanner.hasNext(pattern)) {
            if(scanner.hasNextLine()) {
                view.displayMessage(INCORRECT_INPUT);
                scanner.nextLine();
            }
        }
        field.setValue(scanner.next(pattern));
    }

    protected static class EntityField {

        private final String name;
        private final String hint;
        private final Pattern pattern;

        private String value;

        EntityField(String name, String hint, Pattern pattern) {
            this.name = name;
            this.hint = hint;
            this.pattern = pattern;
        }

        String getName() {
            return name;
        }

        String getHint() {
            return hint;
        }

        Pattern getPattern() {
            return pattern;
        }

        String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }
    }


}
