package com.shitikov.task6.util;

import java.util.UUID;

public class IdGenerator {

    private IdGenerator() {
    }

    public static String generateId() {
        UUID newId = UUID.randomUUID();
        return newId.toString();
    }
}
