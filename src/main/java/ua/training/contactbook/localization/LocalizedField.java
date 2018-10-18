package ua.training.contactbook.localization;

public enum LocalizedField {

    FIRST_NAME("first-name"),
    LAST_NAME("last-name"),
    MIDDLE_NAME("middle-name"),
    NICKNAME("nickname"),
    COMMENTS("comments"),
    GROUP("group"),
    HOME_TEL("home-tel"),
    MOBILE_TEL1("mobile-tel1"),
    MOBILE_TEL2("mobile-tel2"),
    EMAIL("email"),
    SKYPE("skype"),
    POST_CODE("post-code"),
    CITY("city"),
    STREET("street"),
    BUILDING("building"),
    ROOM("room"),
    DATE_CREATED("date-created"),
    DATE_UPDATED("date-updated");

    LocalizedField(String name) {
        this.name = name;
    }

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
