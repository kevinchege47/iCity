package com.example.icity.Database;

public class UserHelperClass {
    String firstname, email, password, username, gender, date, _countryNumber;
    public UserHelperClass(){}

    public UserHelperClass(String firstname, String email, String password, String username, String gender, String date, String _countryNumber) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.date = date;
        this._countryNumber = _countryNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String get_countryNumber() {
        return _countryNumber;
    }

    public void set_countryNumber(String _countryNumber) {
        this._countryNumber = _countryNumber;
    }
}

