package org.ival.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

    public static Boolean isPassword(String input){
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Boolean isAlphabet(String input){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Boolean isEmail(String input){
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Boolean isPhoneNumber(String input){
        Pattern pattern = Pattern.compile("\\+?([ -]?\\d+)+|\\(\\d+\\)([ -]\\d+)");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static Boolean isGender(String input){
        Pattern pattern = Pattern.compile("^[FM]$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    public static Boolean isDateFormat(String input){
        Pattern pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
