package com.example.animeverse;

public class HelperClass {

    String name, email, username, password,contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public HelperClass(String name, String email, String username, String password, String contact) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.contact = contact;
    }

    public HelperClass() {
    }
}
