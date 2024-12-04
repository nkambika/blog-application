package com.myprojects.utilities;

public class CommonConstants {
    public static final String PASSWORD_REGEXP = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,20}$";
    public static final String INVALID_PASSWORD_MESSAGE = "password must contain at least one uppercase, one lowercase, one digit, one special symbol [@#$%^&+=!] and 8-20 characters long";
    public static final String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String INVALID_EMAIL_MESSAGE = "";
    public static final String MOBILE_REGEXP = "^[6-9]{1}\\d{9}$";
    public static final String INVALID_MOBILE_MESSAGE = "mobile number must begin with digits [6,7,8,9] and exactly 10 digits ";
    public static final String GENDER_REGEXP = "^(?i)(male|female|other)$";
    public static final String INVALID_GENDER_MESSAGE = "gender must be male, female or other";
    public static final String NAME_REGEXP = "^[a-zA-Z]{3,}(\\s[a-zA-Z]{3,})*$";
    public static final String INVALID_NAME_MESSAGE = "name must contain letters and space";
}
