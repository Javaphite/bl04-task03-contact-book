package ua.training.contactbook.model.entities;

public class Address {

    private String postCode;
    private String city;
    private String street;
    private String buildingNumber;
    private String roomNumber;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        String separator = System.lineSeparator();
        return new StringBuilder()
                .append("POSTAL CODE: ").append(postCode).append(separator)
                .append("CITY: ").append(city).append(separator)
                .append("STREET: ").append(street).append(separator)
                .append("BUILDING: ").append(buildingNumber).append(separator)
                .append("ROOM: ").append(roomNumber)
                .toString();
    }

    public String asFlatText() {
        String separator = ", ";
        return new StringBuilder()
                .append(postCode).append(separator)
                .append(city).append(separator)
                .append(street).append(separator)
                .append(buildingNumber).append(separator)
                .append(roomNumber)
                .toString();
    }
}
