package com.example.phonestore.object.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostStaff {
    @JsonProperty("id_customer")
    private String idStaff;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_num")
    private String phoneNumber;
    @JsonProperty("day_of_birth")
    private String dateOfBirth;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("identity_card")
    private String identityCard;

    public PostStaff(String idStaff, String lastName, String firstName, String address, String email, String phoneNumber, String dateOfBirth, String gender, String identityCard) {
        this.idStaff = idStaff;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.identityCard = identityCard;
    }

    public PostStaff() {
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    @Override
    public String toString() {
        return "PostStaff{" +
                "idStaff='" + idStaff + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", identityCard='" + identityCard + '\'' +
                '}';
    }
}
