package com.example.healite.Model;

public class Users {

    public String firstName, lastName, email;

    public Users() {

    }
    public Users(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
}
