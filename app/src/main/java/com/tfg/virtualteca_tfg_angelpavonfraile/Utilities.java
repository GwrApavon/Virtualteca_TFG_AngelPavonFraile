package com.tfg.virtualteca_tfg_angelpavonfraile;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utilities {

    //validates if the date given is valid
    public static boolean validateDateFormat(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        //more strict parse to ensure no automatic adjustments are made
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    //validates if the dni given is valid
    public static boolean validateDni(String dni) {
        //Defines a regular expression for the valid dni pattern in Spain
        //\\d{8} exactly 8 digits consecutive.
        //[A-HJ-NP-TV-Z] specifies  that an alphabetical letter is waited after the 8 digits unless I, Ñ, O and U

        final String dniPattern = "\\d{8}[A-HJ-NP-TV-Z]";

        if (dni.matches(dniPattern)) {
            char letter = dni.charAt(dni.length() - 1);
            int number = Integer.parseInt(dni.substring(0, dni.length() - 1));
            int remainder = number % 23;
            String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
            char expectedLetter = letters.charAt(remainder);

            return Character.toUpperCase(letter) == expectedLetter;
        } else {
            return false;
        }
    }
}
