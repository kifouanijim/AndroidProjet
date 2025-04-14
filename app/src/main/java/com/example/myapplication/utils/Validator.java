package com.example.myapplication.utils;

import android.text.TextUtils;

public class Validator {

    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value.trim());
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValidPhone(String phone) {
        return phone.length() >= 8 && phone.matches("[0-9+ ]+");
    }
}
