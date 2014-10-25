package com.traviswu.clutch;

/**
 * Created by traviswu on 2014-10-23.
 */
public class Contact {
    private String _Name, _Number, _Email;
    public Contact(String Name, String Number, String Email) {
        _Name = Name;
        _Number = Number;
        _Email = Email;

    }
    public String get_Name() {
        return _Name;
    }
    public String get_Number() {
        return _Number;
    }
    public String get_Email() {
        return _Email;
    }
}
