package com.jicjo.apis.utility;

import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;

public class RandomGenerator {
    private static final String CHARACTERS = "3ec5c2d3775729d2923f693211850f0a1d0e974463218501d65c47c3d12e93cc6b59d79a1600ac43680d3d8ab47f88c40ecf2db389cb3c5501633edc513c5d82";
    private static final int KEY_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomKey() {
        StringBuilder key = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            key.append(CHARACTERS.charAt(index));
        }
        return key.toString();
    }
}
