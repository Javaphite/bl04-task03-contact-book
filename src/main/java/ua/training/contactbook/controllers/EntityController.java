package ua.training.contactbook.controllers;

import ua.training.contactbook.view.ConsoleView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ua.training.contactbook.localization.Message.INCORRECT_INPUT;
import static ua.training.contactbook.localization.Message.INPUT;

/**
 * Defines family of entity building controllers.
 * @param <T>
 */

public abstract class EntityController<T> {

    protected final Map<String, EntityField> fields;
    protected final Scanner scanner;

    protected ConsoleView view;

    /**
     * Constructor for encapsulation of base initialization logic.
     * @param view view to display messages on
     * @param scanner scanner instance for input reading
     */
    protected EntityController(ConsoleView view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner;
        this.fields = new LinkedHashMap<>();
    }

    /**
     * Prepares instance of entity of type associated with this Controller.
     * @return instance of entity.
     */
    public abstract T prepareEntity();

    /**
     * Registers field of entity to be processed with this controller.
     * @param name field name
     * @param hint hint for field
     * @param pattern regular expression to validate field value
     */
    protected void addEntityField(String name, String hint, Pattern pattern) {
        fields.put(name, new EntityField(name, hint, pattern));
    }

    /**
     * Clears all values of registered fields,
     * makes this controller ready to be reused for new entity instance.
     */
    protected void resetFieldsValues() {
        fields.values()
                .forEach(entityField -> entityField.setValue(null));
    }

    /**
     * Unified getter for field values by fields names.
     * @param fieldName name of field which value should be returned
     * @return currently stored value of field
     */
    protected String getFieldValue(String fieldName) {
        return fields.get(fieldName).getValue();
    }

    /**
     * Unified getter for fields by fields names.
     * @param fieldName name of field which instance should be returned
     * @return instance of {@link EntityField} for given name
     */
    protected EntityField getField(String fieldName) {
        return fields.get(fieldName);
    }

    /**
     * Asks and awaits input of given field using associated view and input source.
     * @param field instance of entity field where received value will be stored
     */
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

    /**
     * Encapsulates entity field properties and value.
     */
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
