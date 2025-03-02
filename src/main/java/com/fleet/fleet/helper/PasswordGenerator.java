package com.fleet.fleet.helper;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {
    private static final String DIGITS = "0123456789";


    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length, boolean useDigits) {
        StringBuilder characters = new StringBuilder();
        StringBuilder password = new StringBuilder();

        if (useDigits) characters.append(DIGITS);

        if (characters.length() == 0) {
            throw new IllegalArgumentException("elegale argument");
        }


        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }
}