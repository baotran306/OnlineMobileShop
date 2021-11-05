package com.example.phonestore.object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffUpload {
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("identity_card")
    private String identityCard;
    @JsonProperty("email")
    private String email;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @JsonProperty("phone_num")
    private String phoneNumber;
    @JsonProperty("role_id")
    private String roleId;
    @JsonProperty("salary")
    private Double salary;
    @JsonProperty("password")
    private String password;
    @JsonProperty("username")
    private String username;

    public StaffUpload(String lastName, String firstName, String address, String identityCard, String email, String gender, String dateOfBirth, String phoneNumber, String roleId, Double salary, String password, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.identityCard = identityCard;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.roleId = roleId;
        this.salary = salary;
        this.password = password;
        this.username = username;
    }

    public StaffUpload() {
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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StaffUpload{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleId='" + roleId + '\'' +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
