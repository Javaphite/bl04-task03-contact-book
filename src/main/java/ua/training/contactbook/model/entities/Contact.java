package ua.training.contactbook.model.entities;

import java.time.LocalDate;

public class Contact {

    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
    private String nickName;
    private String comments;
    private Group group;
    private String homeTelephone;
    private String mobileTelephone1;
    private String mobileTelephone2;
    private String email;
    private String skype;
    private Address address;
    private String fullAddress;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;

    private enum Group {
        DEFAULT,
        FRIEND,
        COLLEAGUE,
        RELATIVE,
        INSIDER;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setGroup(String group) {
        switch (group.toLowerCase()) {
            case "friend": this.group = Group.FRIEND; break;
            case "colleague": this.group = Group.COLLEAGUE; break;
            case "relative": this.group = Group.RELATIVE; break;
            case "insider": this.group = Group.INSIDER; break;
            default: this.group = Group.DEFAULT;
        }
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public void setHomeTelephone(String homeTelephone) {
        this.homeTelephone = homeTelephone;
    }

    public String getMobileTelephone1() {
        return mobileTelephone1;
    }

    public void setMobileTelephone1(String mobileTelephone1) {
        this.mobileTelephone1 = mobileTelephone1;
    }

    public String getMobileTelephone2() {
        return mobileTelephone2;
    }

    public void setMobileTelephone2(String mobileTelephone2) {
        this.mobileTelephone2 = mobileTelephone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateCreated(CharSequence dateCreated) {
        this.dateCreated = LocalDate.parse(dateCreated);
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setDateUpdated(CharSequence dateUpdated) {
        this.dateUpdated = LocalDate.parse(dateUpdated);
    }

    @Override
    public String toString() {
        String separator = System.lineSeparator();
        return new StringBuilder()
                .append("FIRST NAME: ").append(firstName).append(separator)
                .append("LAST NAME: ").append(lastName).append(separator)
                .append("MIDDLE NAME: ").append(middleName).append(separator)
                .append("FULL NAME: ").append(fullName).append(separator)
                .append("NICKNAME: ").append(nickName).append(separator)
                .append("COMMENTS: ").append(comments).append(separator)
                .append("GROUP: ").append(group).append(separator)
                .append("HOME TEL: ").append(homeTelephone).append(separator)
                .append("MOBILE TEL1: ").append(mobileTelephone1).append(separator)
                .append("MOBILE TEL2: ").append(mobileTelephone2).append(separator)
                .append("E-MAIL: ").append(email).append(separator)
                .append("SKYPE: ").append(skype).append(separator)
                .append("ADDRESS: ").append(address).append(separator)
                .append("FULL ADDRESS: ").append(fullAddress).append(separator)
                .append("DATE CREATED: ").append(dateCreated).append(separator)
                .append("DATE UPDATED: ").append(dateUpdated)
                .toString();
    }
}
