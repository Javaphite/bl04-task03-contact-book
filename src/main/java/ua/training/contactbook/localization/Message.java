package ua.training.contactbook.localization;

public enum Message {
    INTRODUCTION("info.introduction"),
    NEW_CONTACT("info.new_contact"),
    INCORRECT_INPUT("warn.incorrect_input"),
    INPUT("info.demand_input"),
    SHOW_CONTACT("info.show_contact");

    Message(String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}